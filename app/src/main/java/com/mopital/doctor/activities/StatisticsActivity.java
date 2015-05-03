package com.mopital.doctor.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mopital.doctor.R;
import com.mopital.doctor.adapters.PatientActivityPagerAdapter;
import com.mopital.doctor.core.Global;
import com.mopital.doctor.core.ServerApi;
import com.mopital.doctor.core.ServerApiProvider;
import com.mopital.doctor.models.Statistics;
import com.mopital.doctor.utils.tabs.SlidingTabLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class StatisticsActivity extends ActionBarActivity {

    @InjectView(R.id.app_bar)
    Toolbar toolbar;

    @InjectView(R.id.bedTV)
    TextView bedTV;

    @InjectView(R.id.treatmentTV)
    TextView treatmentTV;

    @InjectView(R.id.beaconTV)
    TextView beaconTV;

    @InjectView(R.id.doctorTV)
    TextView doctorTV;

    @InjectView(R.id.equipmentTV)
    TextView equipmentTV;

    @InjectView(R.id.patientTV)
    TextView patientTV;

    private ServerApi api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        api = ServerApiProvider.serverApi();
        getAllStatistics();
    }

    public void getAllStatistics(){
        api.getStatistics(this, new Response.Listener<Statistics>() {
                    @Override
                    public void onResponse(Statistics response) {
                        bedTV.setText(response.getNumberOfBeds() +"");
                        treatmentTV.setText(response.getTotalTreatment()+"");
                        beaconTV.setText(response.getNumberOfBeacons()+"");
                        doctorTV.setText(response.getNumberOfDoctor()+"");
                        equipmentTV.setText(response.getNumberOfEquipment()+"");
                        patientTV.setText(response.getNumberOfPatient()+"");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
