package com.mopital.doctor.models;

import java.util.List;

/**
 * Created by AlperCem on 22.3.2015.
 */
public class NurseRecords {
    private String id;
    private long recordedAt;
    private String diagnoses;
    private String allergy;
    private String bloodType;
    private String nurse;
    private PatientPain patientPain;
    private List<BloodSugarMonitoring> bloodSugarMonitoringRecords;
    private List<PeriodicMonitoring> periodicMonitoringRecords;

    public NurseRecords(String id, long recordedAt, String diagnoses, String allergy, String bloodType, String nurse, PatientPain patientPain, List<BloodSugarMonitoring> bloodSugarMonitoringRecords, List<PeriodicMonitoring> periodicMonitoringRecords) {
        this.id = id;
        this.recordedAt = recordedAt;
        this.diagnoses = diagnoses;
        this.allergy = allergy;
        this.bloodType = bloodType;
        this.nurse = nurse;
        this.patientPain = patientPain;
        this.bloodSugarMonitoringRecords = bloodSugarMonitoringRecords;
        this.periodicMonitoringRecords = periodicMonitoringRecords;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(long recordedAt) {
        this.recordedAt = recordedAt;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public PatientPain getPatientPain() {
        return patientPain;
    }

    public void setPatientPain(PatientPain patientPain) {
        this.patientPain = patientPain;
    }

    public List<BloodSugarMonitoring> getBloodSugarMonitoringRecords() {
        return bloodSugarMonitoringRecords;
    }

    public void setBloodSugarMonitoringRecords(List<BloodSugarMonitoring> bloodSugarMonitoringRecords) {
        this.bloodSugarMonitoringRecords = bloodSugarMonitoringRecords;
    }

    public List<PeriodicMonitoring> getPeriodicMonitoringRecords() {
        return periodicMonitoringRecords;
    }

    public void setPeriodicMonitoringRecords(List<PeriodicMonitoring> periodicMonitoringRecords) {
        this.periodicMonitoringRecords = periodicMonitoringRecords;
    }
}
