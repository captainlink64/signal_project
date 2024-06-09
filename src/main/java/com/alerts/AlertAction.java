package com.alerts;

import com.data_management.Patient;

/**
 * The {@code AlertAction} interface defines the contract for triggering alerts
 * based on patient data.
 */
public interface AlertAction {

    /**
     * Triggers an alert for the specified patient.
     *
     * @param patient the patient for whom the alert is triggered
     */
    void trigger(Patient patient);
}
