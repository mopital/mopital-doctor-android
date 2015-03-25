package com.mopital.doctor.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.getpoi.beacon.PoiService;
import com.mopital.doctor.R;
import com.mopital.doctor.fragments.NavigationDrawerFragment;
import com.mopital.doctor.utils.Constants;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.app_bar)
    Toolbar toolbar;

    private static final String TAG = "MainActivity";
    private static final String MOPITAL_SECRET_KEY = "684AE112-170A-4BE8-A30B-C2F0BD17109F";
    public static Activity activity;

    final String ID = "userUniqueIdTest1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        activity = this;

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
