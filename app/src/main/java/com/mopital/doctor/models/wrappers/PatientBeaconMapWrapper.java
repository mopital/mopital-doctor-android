package com.mopital.doctor.models.wrappers;

import com.mopital.doctor.models.PatientBeaconMap;

import java.util.List;

/**
 * Created by AlperCem on 5.5.2015.
 */
public class PatientBeaconMapWrapper {
    private List<PatientBeaconMap> patientBeaconMapList;

    public PatientBeaconMapWrapper(List<PatientBeaconMap> patientBeaconMapList) {
        this.patientBeaconMapList = patientBeaconMapList;
    }

    public List<PatientBeaconMap> getPatientBeaconMapList() {
        return patientBeaconMapList;
    }

    public void setPatientBeaconMapList(List<PatientBeaconMap> patientBeaconMapList) {
        this.patientBeaconMapList = patientBeaconMapList;
    }
}
