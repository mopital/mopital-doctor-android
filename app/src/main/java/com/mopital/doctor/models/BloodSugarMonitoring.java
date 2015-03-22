package com.mopital.doctor.models;

/**
 * Created by AlperCem on 22.3.2015.
 */
public class BloodSugarMonitoring {

    private long recordedAt;
    private String urineGlucose;
    private String bloodGlucose;

    public long getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(long recordedAt) {
        this.recordedAt = recordedAt;
    }

    public String getUrineGlucose() {
        return urineGlucose;
    }

    public void setUrineGlucose(String urineGlucose) {
        this.urineGlucose = urineGlucose;
    }

    public String getBloodGlucose() {
        return bloodGlucose;
    }

    public void setBloodGlucose(String bloodGlucose) {
        this.bloodGlucose = bloodGlucose;
    }

    public BloodSugarMonitoring(long recordedAt, String urineGlucose, String bloodGlucose) {

        this.recordedAt = recordedAt;
        this.urineGlucose = urineGlucose;
        this.bloodGlucose = bloodGlucose;
    }
}
