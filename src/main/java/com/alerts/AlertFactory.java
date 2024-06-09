package com.alerts;

/**
 * The {@code AlertFactory} class is responsible for creating instances of alerts
 * and alert conditions based on specified types.
 */
public class AlertFactory {

    /**
     * Creates an alert action based on the specified type.
     *
     * @param alertType the type of alert action to create
     * @return an instance of the specified alert action type
     * @throws IllegalArgumentException if the alert type is unknown
     */
    public static AlertAction createAlertAction(String alertType) {
        switch (alertType) {
            case "BloodPressure":
                return new BloodPressureAlertAction();
            // Add more cases for other alert types
            default:
                throw new IllegalArgumentException("Unknown alert type");
        }
    }

    /**
     * Creates an alert condition based on the specified type.
     *
     * @param conditionType the type of alert condition to create
     * @return an instance of the specified alert condition type
     * @throws IllegalArgumentException if the condition type is unknown
     */
    public static AlertCondition createAlertCondition(String conditionType) {
        switch (conditionType) {
            case "BloodPressure":
                return new BloodPressureAlertCondition();
            // Add more cases for other alert conditions
            default:
                throw new IllegalArgumentException("Unknown condition type");
        }
    }
}
