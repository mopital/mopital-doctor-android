package com.mopital.doctor.core;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.getpoi.beacon.PoiBroadcastReceiver;
import com.getpoi.beacon.PoiService;
import com.getpoi.beacon.objects.BeaconResponse;
import com.getpoi.beacon.objects.Pois;

import java.util.List;

import com.mopital.doctor.R;
import com.mopital.doctor.activities.MainActivity;
import com.mopital.doctor.fragments.PatientFragment;
import com.mopital.doctor.models.Patient;

/**
 * Created by ahmetkucuk on 04/02/15.
 */
public class BeaconBroadCastReceiver extends PoiBroadcastReceiver {

    private static final String TAG = "BeaconBroadCastReceiver";

    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "onReceive action: " + action);
        if (action.equals(PoiService.ACTION_DETECT)) {
            List<Pois> beacons = (List<Pois>) intent.getSerializableExtra("POIS");
            for (Pois beacon : beacons) {
                Log.i("Poi found", "major: " + beacon.majorId + " minor: " + beacon.minorId);

                Response.Listener<Patient> patientListener = new Response.Listener<Patient>() {
                    @Override
                    public void onResponse(Patient patient) {
                        ((PatientFragment)MainActivity.activity.getFragmentManager().findFragmentById(R.id.main_page_patient_fragment)).updateInfo(patient);
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //it is possible to cast error to VolleyFailWrapper -> ((VolleyFailWrapper)error).getResult()
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };

                ServerApiProvider.serverApi().getUser(context, "123", patientListener, errorListener);

            }

        } else {
            BeaconResponse data = intent.getParcelableExtra(PoiService.CAMPAIGN_DATA);
            Log.i("Campaign data received", data.getBody() + " " + data.getTitle());
            //sendNotification(data.getTitle(), data.getBody(), data.getUrl(), context);
        }

    }
}