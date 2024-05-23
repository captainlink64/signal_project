package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of OutputStrategy that outputs data to a file.
 * Data for each label is written to a separate file in the specified base directory.
 *
 * @author [Luqmaan Sediq]
 * @version 1.0
 */

public class FileOutputStrategy implements OutputStrategy {
    private String baseDirectory; // Replaced all instances of "BaseDirectory" var with "baseDirectory"

    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>(); // Replaced all instances of "file_map" with "fileMap"

    /**
     * Constructs a FileOutputStrategy with the specified base directory.
     *
     * @param baseDirectory the base directory where files will be stored
     */
    public FileOutputStrategy(String baseDirectory) { //Replaced all instances of "fileOutputStrategy" with "FileOutputStrategy"
        this.baseDirectory = baseDirectory; // Removed blank line above this line
    }

    /**
     * Outputs the data for the specified patient to a file with its label, in the base directory.
     *
     * @param patientId the ID of the patient
     * @param timestamp the timestamp of the data
     * @param label the label for the data
     * @param data the data to be output
     * @throws IOException if an I/O error occurs when writing to the file
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the filePath variable
        String filePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString()); // Replaced all instances of "FilePath" with "filePath"

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
            Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) { // Removed extra indent
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}