package com.mopital.doctor.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mopital.doctor.R;
import com.mopital.doctor.adapters.BloodSugarMonitoringAdapter;
import com.mopital.doctor.adapters.PeriodicMonitoringAdapter;
import com.mopital.doctor.core.Global;
import com.mopital.doctor.models.NurseRecords;
import com.mopital.doctor.models.Patient;
import com.mopital.doctor.models.PatientPain;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by AlperCem on 21.3.2015.
 */
public class NurseRecordsFragment extends Fragment {
    private Context context;

    @InjectView(R.id.id)
    TextView id;

    @InjectView(R.id.recorded_at)
    TextView recordedAt;

    @InjectView(R.id.diagnosis)
    TextView diagnosis;

    @InjectView(R.id.allergy)
    TextView allergy;

    @InjectView(R.id.blood_type)
    TextView bloodType;

    @InjectView(R.id.nurse)
    TextView nurse;

    @InjectView(R.id.region)
    TextView region;

    @InjectView(R.id.type_of_pain)
    TextView typeOfPain;

    @InjectView(R.id.duration)
    TextView duration;

    @InjectView(R.id.blood_sugar_monitoring_Button)
    Button bloodSugarLV;

    @InjectView(R.id.periodic_monitoring_Button)
    Button periodicMonitoringLV;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nurse_records, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindData(Global.activePatient);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    private void bindData(Patient patient) {
        NurseRecords records = patient.getNurseRecords();
        PatientPain patientPain = records.getPatientPain();

        this.id.setText(records.getId());
        this.recordedAt.setText(records.getRecordedAt() + "");
        this.diagnosis.setText(records.getDiagnoses());
        this.allergy.setText(records.getAllergy());
        this.bloodType.setText(records.getBloodType());
        this.nurse.setText(records.getNurse());
        this.region.setText(patientPain.getRegion());
        this.typeOfPain.setText(patientPain.getTypeOfPain());
        this.duration.setText(patientPain.getDuration());

        /*
        BloodSugarMonitoringAdapter adapter = new BloodSugarMonitoringAdapter(context,
                R.layout.blood_sugar_monitoring_item, records.getBloodSugarMonitoringRecords());
        bloodSugarLV.setAdapter(adapter);

        PeriodicMonitoringAdapter adapter2 = new PeriodicMonitoringAdapter(context,
                R.layout.periodic_monitoring_item, records.getPeriodicMonitoringRecords());
        periodicMonitoringLV.setAdapter(adapter2);
       */
    }

    public void buttonClicked(View v){
        switch(v.getId()){
            case R.id.blood_sugar_monitoring_Button:
                break;
            case R.id.periodic_monitoring_Button:
                break;
        }
    }
}
