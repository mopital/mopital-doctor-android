package com.mopital.doctor.core;

import com.getpoi.beacon.objects.Pois;
import com.mopital.doctor.models.Patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AlperCem on 22.3.2015.
 */
public class Global {
    public static List<Patient> patientList;
    public static Patient activePatient;

    public static Patient[] detectedPatientList;

    public static HashMap<Integer, Pois> poiMap = null;
    public static HashMap<Integer, Integer> expireMap = null;
    public static HashMap<Integer, Patient> patientBeaconMap = null;
}
