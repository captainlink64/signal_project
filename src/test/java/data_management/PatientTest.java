package data_management;

import com.data_management.Patient;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient(111111);
        patient.addRecord(80, "HeartRate", 1620000000000L);
        patient.addRecord(120, "BloodPressure", 1620003600000L);
        patient.addRecord(75, "HeartRate", 1620007200000L);
        patient.addRecord(65, "HeartRate", 1620010800000L);
    }

    @Test
    void testGetRecordsWithinRange() {
        List<PatientRecord> records = patient.getRecords(1620000000000L, 1620007200000L);

        assertEquals(3, records.size());
        assertEquals(80, records.get(0).getMeasurementValue());
        assertEquals(120, records.get(1).getMeasurementValue());
        assertEquals(75, records.get(2).getMeasurementValue());
    }

    @Test
    void testGetRecordsNoMatch() {
        List<PatientRecord> records = patient.getRecords(1620020000000L, 1620023600000L);

        assertTrue(records.isEmpty());
    }

    @Test
    void testGetRecordsEdgeCase() {
        List<PatientRecord> records = patient.getRecords(1620000000000L, 1620000000000L);

        assertEquals(1, records.size());
        assertEquals(80, records.get(0).getMeasurementValue());
    }

    @Test
    void testGetRecordsInvalidRange() {
        List<PatientRecord> records = patient.getRecords(1620007200000L, 1620000000000L);

        assertTrue(records.isEmpty());
    }
}
