package com.mopital.doctor.models;

/**
 * Created by AlperCem on 28.2.2015.
 */
public class Treatment {

    private String date;
    private String time;
    private String tension;
    private String temperature;
    private String pulse;
    private String respiration;
    private String pain;
    private String treatment;

    public Treatment(String date, String time, String tension, String temperature, String pulse, String respiration, String pain, String treatment) {
        this.date = date;
        this.time = time;
        this.tension = tension;
        this.temperature = temperature;
        this.pulse = pulse;
        this.respiration = respiration;
        this.pain = pain;
        this.treatment = treatment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTension() {
        return tension;
    }

    public void setTension(String tension) {
        this.tension = tension;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
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

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
