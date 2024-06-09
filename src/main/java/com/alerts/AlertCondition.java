package com.alerts;

import com.data_management.PatientRecord;

/**
 * The {@code AlertCondition} interface defines the contract for evaluating whether
 * a specific alert condition is met based on a {@code PatientRecord}.
 */
public interface AlertCondition {

    /**
     * Evaluates the given {@code PatientRecord} to determine if the alert condition is met.
     *
     * @param record the patient record to evaluate
     * @return {@code true} if the condition is met, {@code false} otherwise
     */
    boolean isConditionMet(PatientRecord record);
}
