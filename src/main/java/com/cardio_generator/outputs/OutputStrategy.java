package com.cardio_generator.outputs;

/**
 * Interface for handling how to output.
 * Implementations of this interface handle how the generated data
 * should be output: to a file or over TCP network.
 *
 * @author [Luqmaan Sediq]
 * @version 1.0
 */
public interface OutputStrategy {
    /**
     * Outputs the data for the specified patient at the given timestamp with its label.
     *
     * @param patientId the ID of the patient
     * @param timestamp the timestamp of the data
     * @param label the label for the data
     * @param data the data to be output
     */
    void output(int patientId, long timestamp, String label, String data);
}
