package com.mopital.doctor.models;

/**
 * Created by AlperCem on 3.5.2015.
 */
public class Statistics {
    private int numberOfBeds;
    private int totalTreatment;
    private int numberOfBeacons;
    private int numberOfDoctor;
    private int numberOfEquipment;
    private int numberOfPatient;
    private int numberOfEmergencyCall;

    public Statistics(int numberOfBeds, int totalTreatment, int numberOfBeacons, int numberOfDoctor, int numberOfEquipment, int numberOfPatient, int numberOfEmergencyCall) {
        this.numberOfBeds = numberOfBeds;
        this.totalTreatment = totalTreatment;
        this.numberOfBeacons = numberOfBeacons;
        this.numberOfDoctor = numberOfDoctor;
        this.numberOfEquipment = numberOfEquipment;
        this.numberOfPatient = numberOfPatient;
        this.numberOfEmergencyCall = numberOfEmergencyCall;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getTotalTreatment() {
        return totalTreatment;
    }

    public void setTotalTreatment(int totalTreatment) {
        this.totalTreatment = totalTreatment;
    }

    public int getNumberOfBeacons() {
        return numberOfBeacons;
    }

    public void setNumberOfBeacons(int numberOfBeacons) {
        this.numberOfBeacons = numberOfBeacons;
    }

    public int getNumberOfDoctor() {
        return numberOfDoctor;
    }

    public void setNumberOfDoctor(int numberOfDoctor) {
        this.numberOfDoctor = numberOfDoctor;
    }

    public int getNumberOfEquipment() {
        return numberOfEquipment;
    }

    public void setNumberOfEquipment(int numberOfEquipment) {
        this.numberOfEquipment = numberOfEquipment;
    }

    public int getNumberOfPatient() {
        return numberOfPatient;
    }

    public void setNumberOfPatient(int numberOfPatient) {
        this.numberOfPatient = numberOfPatient;
    }

    public int getNumberOfEmergencyCall() {
        return numberOfEmergencyCall;
    }

    public void setNumberOfEmergencyCall(int numberOfEmergencyCall) {
        this.numberOfEmergencyCall = numberOfEmergencyCall;
    }
}
