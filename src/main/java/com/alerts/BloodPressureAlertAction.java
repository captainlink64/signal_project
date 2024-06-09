package com.alerts;

import com.data_management.Patient;

/**
 * The {@code BloodPressureAlertAction} class implements the {@code AlertAction} interface
 * to trigger alerts based on blood pressure data.
 */
public class BloodPressureAlertAction implements AlertAction {

    /**
     * Triggers a blood pressure alert for the specified patient.
     *
     * @param patient the patient for whom the alert is triggered
     */
    @Override
    public void trigger(Patient patient) {
        // Implementation to trigger the alert
        System.out.println("Blood pressure alert for patient: " + patient.getId());
    }
}
