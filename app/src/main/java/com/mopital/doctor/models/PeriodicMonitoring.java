package com.mopital.doctor.models;

/**
 * Created by AlperCem on 22.3.2015.
 */
public class PeriodicMonitoring {
    private long recordedAt;
    private double tension;
    private double fever;
    private int pulse;
    private String respiration;
    private String pain;

    public PeriodicMonitoring(long recordedAt, double tension, double fever, int pulse, String respiration, String pain) {
        this.recordedAt = recordedAt;
        this.tension = tension;
        this.fever = fever;
        this.pulse = pulse;
        this.respiration = respiration;
        this.pain = pain;
    }

    public long getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(long recordedAt) {
        this.recordedAt = recordedAt;
    }

    public double getTension() {
        return tension;
    }

    public void setTension(double tension) {
        this.tension = tension;
    }

    public double getFever() {
        return fever;
    }

    public void setFever(double fever) {
        this.fever = fever;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public String getRespiration() {
        return respiration;
    }

    public void setRespiration(String respiration) {
        this.respiration = respiration;
    }

    public String getPain() {
        return pain;
    }

    public void setPain(String pain) {
        this.pain = pain;
    }
}
