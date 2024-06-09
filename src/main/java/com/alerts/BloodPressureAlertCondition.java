package com.alerts;

import com.data_management.PatientRecord;

/**
 * The {@code BloodPressureAlertCondition} class implements the {@code AlertCondition}
 * interface to evaluate blood pressure data and determine if it meets specific alert criteria.
 */
public class BloodPressureAlertCondition implements AlertCondition {

    /**
     * Evaluates the given {@code PatientRecord} to determine if the blood pressure
     * alert condition is met.
     *
     * @param record the patient record to evaluate
     * @return {@code true} if the condition is met, {@code false} otherwise
     */
    @Override
    public boolean isConditionMet(PatientRecord record) {
//        return record.getBloodPressureSystolic() > 180 || record.getBloodPressureSystolic() < 90 ||
//                record.getBloodPressureDiastolic() > 120 || record.getBloodPressureDiastolic() < 60;
        return false;
    } //TODO: fix this boolean condition check
}