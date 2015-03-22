package com.mopital.doctor.models.wrappers;

import com.mopital.doctor.models.Patient;

import java.util.List;

/**
 * Created by AlperCem on 22.3.2015.
 */
public class PatientListWrapper {
    private List<Patient> patientList;

    public PatientListWrapper(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }
}
