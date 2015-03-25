package com.mopital.doctor.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mopital.doctor.R;
import com.mopital.doctor.adapters.TreatmentAdapter;
import com.mopital.doctor.core.Global;
import com.mopital.doctor.models.Treatment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by AlperCem on 21.3.2015.
 */
public class PatientTreatmentFragment extends Fragment implements ListView.OnItemClickListener {

    @InjectView(R.id.treatmentsLV)
    ListView treatmentLV;

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_treatment, container, false);
        ButterKnife.inject(this, view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTreatmentList();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    private void setTreatmentList() {
        TreatmentAdapter adapter = new TreatmentAdapter(context,
                R.layout.treatment_list_view_item, Global.activePatient.getTreatments());
        treatmentLV.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        List<Treatment> treatmentList = Global.activePatient.getTreatments();
        //something to do with this
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_patient_treatment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_treatment) {
            Toast.makeText(context, "Add Treatment", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}
