package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Interface for generating data for a specific patient and passing it to an output strategy.
 *
 * @author [Luqmaan Sediq]
 * @version 1.0
 */

public interface PatientDataGenerator {
    /**
     * Generates patient data for the given patient ID and outputs it using output strategy.
     *
     * @param patientId the identity number of the patient
     * @param outputStrategy the strategy to use for outputting the data
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
