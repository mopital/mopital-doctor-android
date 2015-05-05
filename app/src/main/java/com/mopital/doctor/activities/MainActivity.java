package com.mopital.doctor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.getpoi.beacon.PoiService;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.mopital.doctor.R;
import com.mopital.doctor.core.Global;
import com.mopital.doctor.core.PreferenceService;
import com.mopital.doctor.fragments.NavigationDrawerFragment;
import com.mopital.doctor.models.Patient;
import com.mopital.doctor.utils.Constants;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.fab1)
    FloatingActionButton fab1;

    @InjectView(R.id.fab2)
    FloatingActionButton fab2;

    @InjectView(R.id.fab3)
    FloatingActionButton fab3;

    @InjectView(R.id.fab4)
    FloatingActionButton fab4;

    @InjectView(R.id.fab5)
    FloatingActionButton fab5;

    @InjectView(R.id.fab_menu)
    FloatingActionMenu menu1;

    @InjectView(R.id.app_bar)
    Toolbar toolbar;

    private static final String TAG = "MainActivity";
    private static final String MOPITAL_SECRET_KEY = "684AE112-170A-4BE8-A30B-C2F0BD17109F";

    final String ID = "userUniqueIdTest1";
    private Handler mUiHandler = new Handler();

    public static MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        String user = PreferenceService.getName(MainActivity.this.getApplicationContext());
        drawerFragment.setUserProfile(user);

        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);
        fab4.setOnClickListener(clickListener);
        fab5.setOnClickListener(clickListener);

        //menu1.hideMenuButton(true);
        menu1.setClosedOnTouchOutside(true);
        activity = this;
        update();

        /*
        boolean hasElm = false;
        if(Global.detectedPatientList != null && Global.detectedPatientList.length != 0) {
            for (int i = 0; i < Global.detectedPatientList.length; i++) {
                if (Global.detectedPatientList[i] != null)
                    hasElm = true;
            }
        }

        if (hasElm) {
            int count = 0;
            for (Patient patient : Global.detectedPatientList) {
                if(patient == null)
                    break;
                if (count == 0) {
                    fab1.setVisibility(View.VISIBLE);
                    fab1.setLabelText(patient.getName());
                }
                if (count == 1) {
                    fab2.setVisibility(View.VISIBLE);
                    fab2.setLabelText(patient.getName());
                }
                if (count == 2) {
                    fab3.setVisibility(View.VISIBLE);
                    fab3.setLabelText(patient.getName());
                }
                if (count == 3) {
                    fab4.setVisibility(View.VISIBLE);
                    fab4.setLabelText(patient.getName());
                }
                if (count == 4) {
                    fab5.setVisibility(View.VISIBLE);
                    fab5.setLabelText(patient.getName());
                }
                count++;
            }
            menu1.setVisibility(View.VISIBLE);

            int delay = 400;
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    menu1.showMenuButton(true);
                }
            }, delay);
        }*/

//        api.signUp(this, "alpercem", "doctor", "alpercempolat@hotmail.com", "Alper@2014", listener, errorListener);
        //api.signIn(this, "alpercempolat@hotmail.com", "Alper@2014", listener, errorListener);

        /*
        GCMRegistrationService service = new GCMRegistrationService(this.getApplicationContext());
        service.register();

        api.notifyUser(this, "alpercempolat@hotmail.com", "Benim mesajim", new Response.Listener<Result>() {
            @Override
            public void onResponse(Result response) {
                Log.d(TAG, "send notify successful");
            }
        }, errorListener);*/
    }

    public void update(){
        boolean hasElm = false;
        if(Global.detectedPatientList != null && Global.detectedPatientList.length != 0) {
            for (int i = 0; i < Global.detectedPatientList.length; i++) {
                if (Global.detectedPatientList[i] != null)
                    hasElm = true;
            }
        }
        if (hasElm) {
            int count = 0;
            for (Patient patient : Global.detectedPatientList) {
                if(patient == null)
                    break;
                if (count == 0) {
                    fab1.setVisibility(View.VISIBLE);
                    fab1.setLabelText(patient.getName());
                }
                if (count == 1) {
                    fab2.setVisibility(View.VISIBLE);
                    fab2.setLabelText(patient.getName());
                }
                if (count == 2) {
                    fab3.setVisibility(View.VISIBLE);
                    fab3.setLabelText(patient.getName());
                }
                if (count == 3) {
                    fab4.setVisibility(View.VISIBLE);
                    fab4.setLabelText(patient.getName());
                }
                if (count == 4) {
                    fab5.setVisibility(View.VISIBLE);
                    fab5.setLabelText(patient.getName());
                }
                count++;
            }
            menu1.setVisibility(View.VISIBLE);

            int delay = 400;
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    menu1.showMenuButton(true);
                }
            }, delay);
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Patient patient = null;
            switch (v.getId()) {
                case R.id.fab1:
                    patient = Global.detectedPatientList[0];
                    break;
                case R.id.fab2:
                    patient = Global.detectedPatientList[1];
                    break;
                case R.id.fab3:
                    patient = Global.detectedPatientList[2];
                    break;
                case R.id.fab4:
                    patient = Global.detectedPatientList[3];
                    break;
                case R.id.fab5:
                    patient = Global.detectedPatientList[4];
                    break;
            }
            Global.activePatient = patient;
            Intent intent = new Intent(MainActivity.this, PatientActivity.class);
            startActivity(intent);
        }
    };

    private void callLoginActivity() {
        Intent i = new Intent(MainActivity.this, SignInActivity.class);
        startActivityForResult(i, Constants.SIGN_IN_REQUEST_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent service = new Intent(MainActivity.this, PoiService.class);
        service.putExtra(PoiService.ACTION_KEY, PoiService.START_SERVICE);
        service.putExtra(PoiService.SECRET_KEY, MOPITAL_SECRET_KEY);
        service.putExtra(PoiService.UNIQUE_ID_KEY, ID);
        startService(service);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(MainActivity.this, PoiService.class));
    }
}
