package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Generates alert data for patients.
 * This generator gives alert states, which can be triggered or resolved,
 * and outputs the alert data using the specified output strategy.
 *
 * @author [Luqmaan Sediq]
 * @version 1.0
 */

public class AlertGenerator implements PatientDataGenerator {
    
    public static final Random randomGenerator = new Random();
    private boolean[] alertStates; // false = resolved, true = pressed 
    // Replaced all instances of "AlertStates" with "alertStates"

    /**
     * Constructs an AlertGenerator for the specified number of patients.
     * Initializes all alerts as resolved.
     *
     * @param patientCount the number of patients
     */
    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1];
    }

    /**
     * Generates alert data for the specified patient.
     * Alerts can be triggered or resolved based on random simulation.
     * The data is output using the provided output strategy.
     *
     * @param patientId the ID of the patient
     * @param outputStrategy the strategy to use for outputting the data
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {
                if (randomGenerator.nextDouble() < 0.9) { // 90% chance to resolve
                    alertStates[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                // Replaced all instances of "Lambda" with "lambda"
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency 
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
