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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.mopital.doctor.R;
import com.mopital.doctor.activities.MainActivity;
import com.mopital.doctor.activities.PatientActivity;
import com.mopital.doctor.fragments.PatientFragment;
import com.mopital.doctor.models.Patient;

/**
 * Created by ahmetkucuk on 04/02/15.
 */
public class BeaconBroadCastReceiver extends PoiBroadcastReceiver {

    private static final String TAG = "BeaconBroadCastReceiver";

    @Override
    public void onReceive(final Context context, Intent intent) {
        if ( Global.poiMap == null)
            Global.poiMap = new HashMap<>();
        if (Global.expireMap == null)
            Global.expireMap = new HashMap<>();
        if(Global.detectedPatientList == null)
            Global.detectedPatientList = new Patient[5];

        String action = intent.getAction();
        Log.i(TAG, "onReceive action: " + action);
        if (action.equals(PoiService.ACTION_DETECT)) {
            List<Pois> beacons = (List<Pois>) intent.getSerializableExtra("POIS");

            boolean isChanged = false;
            for ( Pois beacon: beacons){
                Log.e(TAG, "beacon: " + beacon.getMinorId());
                if (Global.poiMap.containsKey(beacon.getMinorId())){
                    Global.expireMap.put(beacon.getMinorId(),10);
                }else{
                    Global.poiMap.put(beacon.getMinorId(), beacon);
                    Global.expireMap.put(beacon.getMinorId(), 10);
                    isChanged = true;
                }
            }

            Iterator it = Global.expireMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                int minor = (int) pair.getKey();
                int expiration = (int) pair.getValue();
                if (expiration == 0){
                    Global.expireMap.remove(minor);
                    Global.poiMap.remove(minor);
                    isChanged = true;
                    Log.e(TAG, "Expire: " + minor);
                }else{
                    if(expiration < 9)
                        Log.e(TAG, "Decrease of " + minor + " :" + expiration);
                    Global.expireMap.put(minor,expiration-1);
                }
                it.remove();
            }

            if(Global.patientBeaconMap != null && Global.patientBeaconMap.size() != 0 && isChanged ) {
                int count = 0;
                Global.detectedPatientList = new Patient[5];
                Iterator itt = Global.poiMap.entrySet().iterator();
                while (itt.hasNext()) {
                    Map.Entry pair = (Map.Entry) itt.next();
                    int minor = (int) pair.getKey();
                    Patient patient = null;
                    if(Global.patientBeaconMap.containsKey(minor))
                        patient = Global.patientBeaconMap.get(minor);
                    else
                        continue;

                    boolean isExists = false;
                    if(count < Global.detectedPatientList.length) {
                        for(int i = 0; i<Global.detectedPatientList.length; i++){
                            if(Global.detectedPatientList[i] != null && Global.detectedPatientList[i].getId().equals(patient.getId()))
                                isExists = true;
                        }
                        if(!isExists)
                            Global.detectedPatientList[count] = patient;
                    }
                    else
                        break;
                    count++;
                }

                if(MainActivity.activity != null)
                    MainActivity.activity.update();

                /*
                Intent i = new Intent(context,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                context.startActivity(i);*/
            }
            /*
            for (Pois beacon : beacons) {
                Log.i("Poi found", "major: " + beacon.majorId + " minor: " + beacon.minorId);
                Response.Listener<Patient> patientListener = new Response.Listener<Patient>() {
                    @Override
                    public void onResponse(Patient patient) {
                        Global.detectedBeacons.add(patient);
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //it is possible to cast error to VolleyFailWrapper -> ((VolleyFailWrapper)error).getResult()
                        //Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                        //Log.i(TAG, error.getMessage().toString());
                    }
                };

                ServerApiProvider.serverApi().getUser(context, beacon.getMinorId()+"", patientListener, errorListener);
            }*/

        } else {
            BeaconResponse data = intent.getParcelableExtra(PoiService.CAMPAIGN_DATA);
            Log.i("Campaign data received", data.getBody() + " " + data.getTitle());
            //sendNotification(data.getTitle(), data.getBody(), data.getUrl(), context);
        }
    }
}
