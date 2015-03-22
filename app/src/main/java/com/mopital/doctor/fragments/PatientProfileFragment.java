package com.mopital.doctor.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mopital.doctor.R;
import com.mopital.doctor.core.Global;
import com.mopital.doctor.models.Patient;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by AlperCem on 21.3.2015.
 */
public class PatientProfileFragment extends Fragment {

    @InjectView(R.id.patient_name)
    TextView patientName;

    @InjectView(R.id.patient_blood_type)
    TextView patientBloodType;

    @InjectView(R.id.patient_file_no)
    TextView patientFileNo;

    @InjectView(R.id.patient_admission_date)
    TextView patientAdmissionDate;

    @InjectView(R.id.patient_age)
    TextView patientAge;

    @InjectView(R.id.patient_weight)
    TextView patientWeight;

    @InjectView(R.id.patient_height)
    TextView patientHeight;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_profile, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateInfo(Global.activePatient);
    }

    public void updateInfo(Patient patient){
        this.patientName.setText(patient.getName());
        this.patientBloodType.setText(patient.getBlood_type());
        this.patientAdmissionDate.setText(patient.getAdmission_date());
        this.patientFileNo.setText(patient.getFile_no());
        this.patientAge.setText(Integer.toString(patient.getAge()));
        this.patientWeight.setText(Double.toString(patient.getWeight()));
        this.patientHeight.setText(Double.toString(patient.getHeight()));
    }
}
