package com.data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileDataReaderTest {

    private FileDataReader fileDataReader;
    private DataStorage dataStorage;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        fileDataReader = new FileDataReader();
        dataStorage = new DataStorage(new Object()); // Passing a dummy object as required by the constructor
    }

    /**
     * tests reading valid data from a temporary file and verifies the data is read correctly.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    void testReadData() throws IOException {
        File tempFile = tempDir.resolve("test_data.csv").toFile();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("111,1620000000000,HeartRate,80\n");
            writer.write("111,1620003600000,BloodPressure,120\n");
            writer.write("111,1620007200000,HeartRate,75\n");
        }

        fileDataReader.readData(dataStorage, tempDir.toString());

        assertEquals(3, dataStorage.getRecords(111, 0, Long.MAX_VALUE).size());
        assertEquals(80.0, dataStorage.getRecords(111, 0, Long.MAX_VALUE).get(0).getMeasurementValue());
        assertEquals(120.0, dataStorage.getRecords(111, 0, Long.MAX_VALUE).get(1).getMeasurementValue());
        assertEquals(75.0, dataStorage.getRecords(111, 0, Long.MAX_VALUE).get(2).getMeasurementValue());
    }

    /**
     * tests reading invalid data from a temporary file and expects an IOException to be thrown.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    void testInvalidDataFormat() throws IOException {
        File tempFile = tempDir.resolve("invalid_data.csv").toFile();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("111,1620000000000,HeartRate\n"); // missing data value
        }

        IOException exception = assertThrows(IOException.class, () -> {
            fileDataReader.readData(dataStorage, tempDir.toString());
        });

        assertEquals("Invalid data format: too many properties", exception.getMessage());
    }

    /**
     * tests parsing data directly from a reader and verifies the data is parsed correctly.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    void testParseData() throws IOException {
        File tempFile = tempDir.resolve("test_data.csv").toFile();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("111,1620000000000,HeartRate,80\n");
            writer.write("111,1620003600000,BloodPressure,120\n");
            writer.write("111,1620007200000,HeartRate,75\n");
        }

        try (Reader reader = new FileReader(tempFile)) {
            fileDataReader.parseData(reader, dataStorage, 0);
        }

        assertEquals(3, dataStorage.getRecords(111, 0, Long.MAX_VALUE).size());
        assertEquals(80.0, dataStorage.getRecords(111, 0, Long.MAX_VALUE).get(0).getMeasurementValue());
        assertEquals(120.0, dataStorage.getRecords(111, 0, Long.MAX_VALUE).get(1).getMeasurementValue());
        assertEquals(75.0, dataStorage.getRecords(111, 0, Long.MAX_VALUE).get(2).getMeasurementValue());
    }
}
