package com.alerts;

import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;
    private Map<String, AlertCondition> alertConditions;
    private Map<String, AlertAction> alertActions;

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
        this.alertConditions = new HashMap<>();
        this.alertActions = new HashMap<>();

        // Initialize alert conditions and alert actions using factory
        alertConditions.put("BloodPressure", AlertFactory.createAlertCondition("BloodPressure"));
        alertActions.put("BloodPressure", AlertFactory.createAlertAction("BloodPressure"));
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the {@link AlertAction#trigger}
     * method.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient, long startTime, long endTime) {
        List<PatientRecord> records = patient.getRecords(startTime, endTime);

        for (PatientRecord record : records) {
            for (Map.Entry<String, AlertCondition> entry : alertConditions.entrySet()) {
                if (entry.getValue().isConditionMet(record)) {
                    alertActions.get(entry.getKey()).trigger(patient);
                }
            }
        }
    }

    /**
     * Evaluates the specified patient's data within a specific time range to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the {@link AlertAction#trigger}
     * method.
     *
     * @param patientId the ID of the patient whose data to evaluate
     * @param startTime the start time in milliseconds
     * @param endTime the end time in milliseconds
     */
    public void evaluateData(int patientId, long startTime, long endTime) {
        List<PatientRecord> records = dataStorage.getRecords(patientId, startTime, endTime);

        for (PatientRecord record : records) {
            for (Map.Entry<String, AlertCondition> entry : alertConditions.entrySet()) {
                if (entry.getValue().isConditionMet(record)) {
                    Patient patient = new Patient(patientId); // Creating a new patient object
                    patient.addRecord(record.getMeasurementValue(), record.getRecordType(), record.getTimestamp());
                    alertActions.get(entry.getKey()).trigger(patient);
                }
            }
        }
    }

    public void evaluateData(Patient patient) {
    }
}
