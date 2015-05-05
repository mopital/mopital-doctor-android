package com.mopital.doctor.models;

/**
 * Created by AlperCem on 5.5.2015.
 */
public class PatientBeaconMap {
    public String patientId;
    public int beaconMinor;

    public PatientBeaconMap(String patientId, int beaconMinor) {
        this.patientId = patientId;
        this.beaconMinor = beaconMinor;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public int getBeaconMinor() {
        return beaconMinor;
    }

    public void setBeaconMinor(int beaconMinor) {
        this.beaconMinor = beaconMinor;
    }
}
