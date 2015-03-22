package com.mopital.doctor.models;

/**
 * Created by AlperCem on 22.3.2015.
 */
public class PatientPain {
    private String region;
    private String typeOfPain;
    private String duration;

    public PatientPain(String region, String typeOfPain, String duration) {
        this.region = region;
        this.typeOfPain = typeOfPain;
        this.duration = duration;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTypeOfPain() {
        return typeOfPain;
    }

    public void setTypeOfPain(String typeOfPain) {
        this.typeOfPain = typeOfPain;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
