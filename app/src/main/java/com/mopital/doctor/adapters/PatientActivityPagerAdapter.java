package com.mopital.doctor.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mopital.doctor.fragments.NurseRecordsFragment;
import com.mopital.doctor.fragments.PatientProfileFragment;
import com.mopital.doctor.fragments.PatientTreatmentFragment;

/**
 * Created by AlperCem on 21.3.2015.
 */
public class PatientActivityPagerAdapter extends FragmentPagerAdapter {

    public PatientActivityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PatientProfileFragment();
            case 1:
                return new PatientTreatmentFragment();
            case 2:
                return new NurseRecordsFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "Profile";
            case 1:
                return "Treatments";
            case 2:
                return "Nurse Records";
        }
        return "";
    }

    @Override
    public int getCount() {
        return 3;
    }
}
