package com.data_management;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.util.Arrays;

public class FileDataReader implements DataReader {

    @Override
    public void readData(DataStorage dataStorage, String directoryPath) throws IOException {
        try {
            Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try (Reader reader = new FileReader(filePath.toFile())) {
                            parseData(reader, dataStorage, 0);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (RuntimeException e) {
            if (e.getCause() instanceof IOException) {
                throw (IOException) e.getCause();
            } else {
                throw e;
            }
        }
    }

    @Override
    public int parseData(Reader reader, DataStorage dataStorage, int lineNumber) throws IOException {
        int charVal;
        StringBuilder dataString = new StringBuilder();
        while ((charVal = reader.read()) != -1) {
            dataString.append((char) charVal);
        }

        String[] lines = dataString.toString().split("\n");
        lines = Arrays.copyOfRange(lines, lineNumber, lines.length);

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != 4) {
                throw new IOException("Invalid data format: too many properties");
            }
            int patientId = Integer.parseInt(parts[0]);
            long timestamp = Long.parseLong(parts[1]);
            String label = parts[2];
            double data = Double.parseDouble(parts[3].replace("%", ""));
            dataStorage.addPatientData(patientId, data, label, timestamp);
        }
        return lines.length;
    }
}