package com.mopital.doctor.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mopital.doctor.R;
import com.mopital.doctor.activities.PatientActivity;
import com.mopital.doctor.adapters.PatientListAdapter;
import com.mopital.doctor.core.DefaultServerApi;
import com.mopital.doctor.core.Global;
import com.mopital.doctor.core.ServerApi;
import com.mopital.doctor.core.ServerApiProvider;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.models.Patient;
import com.mopital.doctor.models.wrappers.PatientListWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlperCem on 28.2.2015.
 */
public class PatientFragment extends Fragment implements ListView.OnItemClickListener {

    private static final String TAG= PatientFragment.class.getName();

    @InjectView(R.id.patient_LV)
    ListView patientLV;

    private ServerApi api;
    private Context context;

    private static PatientFragment mFragment;

    public static PatientFragment getInstance(){
      if(mFragment == null)
        mFragment = new PatientFragment();
        return mFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_patient, container, false);
        ButterKnife.inject(this, view);
        patientLV.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        api = ServerApiProvider.serverApi();
        getPatientInformation();
    }

    public void getPatientInformation(){
        api.getAllPatients(context, new Response.Listener<PatientListWrapper>() {
                    @Override
                    public void onResponse(PatientListWrapper response) {
                        PatientListAdapter adapter = new PatientListAdapter(context,
                                R.layout.patient_list_view_item, response.getPatientList());
                        Global.patientList = response.getPatientList();
                        patientLV.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Patient selectedPatient = Global.patientList.get(position);
        Global.activePatient = selectedPatient;
        Intent intent = new Intent(view.getContext(), PatientActivity.class);
        view.getContext().startActivity(intent);
    }
}
