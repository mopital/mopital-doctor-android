package com.mopital.doctor.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mopital.doctor.R;
import com.mopital.doctor.adapters.PatientActivityPagerAdapter;
import com.mopital.doctor.core.Global;
import com.mopital.doctor.utils.tabs.SlidingTabLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by AlperCem on 21.3.2015.
 */
public class PatientActivity extends ActionBarActivity{

    @InjectView(R.id.app_bar)
    Toolbar toolbar;

    @InjectView(R.id.pager)
    ViewPager mViewPager;

    @InjectView(R.id.patientTabs)
    SlidingTabLayout mTabs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Global.activePatient.getName());

        mViewPager.setAdapter(new PatientActivityPagerAdapter((getSupportFragmentManager())));
        mTabs.setViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

}
