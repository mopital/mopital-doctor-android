package com.mopital.doctor.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.mopital.doctor.R;
import com.mopital.doctor.models.Patient;

/**
 * Created by AlperCem on 28.2.2015.
 */
public class PatientFragment extends Fragment {

    @InjectView(R.id.patient_name)
    TextView patientNameTV;

    @InjectView(R.id.patient_blood_type)
    TextView patientBloodTypeTV;

    @InjectView(R.id.patient_file_no)
    TextView patientFileNoTV;

    @InjectView(R.id.patient_admission_date)
    TextView patientAdmissionDateTV;

    @InjectView(R.id.patient_age)
    TextView patientAgeTV;

    @InjectView(R.id.patient_weight)
    TextView patientWeightTV;

    @InjectView(R.id.patient_height)
    TextView patientHeightTV;

    @InjectView(R.id.treatment_list)
    ListView treatmentListView;

    private Activity activity;
    private static PatientFragment mFragment;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    public static PatientFragment getInstance(){
      if(mFragment ==null)
        mFragment = new PatientFragment();
        return mFragment;
    }

    public void updateInfo(Patient patient){
        ((TextView)getView().findViewById(R.id.patient_name)).setText(patient.getName());
        ((TextView)getView().findViewById(R.id.patient_blood_type)).setText(patient.getBlood_type());
        ((TextView)getView().findViewById(R.id.patient_admission_date)).setText(patient.getAdmission_date());
        ((TextView)getView().findViewById(R.id.patient_file_no)).setText(patient.getFile_no());
        ((TextView)getView().findViewById(R.id.patient_age)).setText(String.valueOf(patient.getAge()));
        ((TextView)getView().findViewById(R.id.patient_weight)).setText(String.valueOf(patient.getWeight()));
        ((TextView)getView().findViewById(R.id.patient_height)).setText(String.valueOf(patient.getHeight()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_patient, container, false);
        ButterKnife.inject(this, view);
        return view;
    }
}
