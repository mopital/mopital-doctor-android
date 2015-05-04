package com.mopital.doctor.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

    @InjectView(R.id.fab_menu)
    FloatingActionMenu menu1;

    @InjectView(R.id.app_bar)
    Toolbar toolbar;

    private static final String TAG = "MainActivity";
    private static final String MOPITAL_SECRET_KEY = "684AE112-170A-4BE8-A30B-C2F0BD17109F";

    final String ID = "userUniqueIdTest1";
    private Handler mUiHandler = new Handler();

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

        //menu1.hideMenuButton(true);
        menu1.setClosedOnTouchOutside(true);

        if (Global.detectedBeacons != null && Global.detectedBeacons.size() != 0) {
            int count = 0;
            for (Patient patient : Global.detectedBeacons) {
                if (count == 0) {
                    fab1.setVisibility(View.VISIBLE);
                    fab1.setLabelText(patient.getName());
                }
                if (count == 1) {
                    fab1.setVisibility(View.VISIBLE);
                    fab2.setLabelText(patient.getName());
                }
                if (count == 2) {
                    fab1.setVisibility(View.VISIBLE);
                    fab3.setLabelText(patient.getName());
                }
                if (count == Global.detectedBeacons.size() - 1) {
                    menu1.setVisibility(View.VISIBLE);

                    int delay = 400;
                        mUiHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                menu1.showMenuButton(true);
                            }
                        }, delay);
                }
                count++;
            }
        }

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

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Patient patient = null;
            switch (v.getId()) {
                case R.id.fab1:
                    patient = Global.detectedBeacons.get(0);
                    break;
                case R.id.fab2:
                    patient = Global.detectedBeacons.get(1);
                    break;
                case R.id.fab3:
                    patient = Global.detectedBeacons.get(2);
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
