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
import com.mopital.doctor.core.volley.requests.BaseVolleyRequest;
import com.mopital.doctor.core.volley.responses.VolleyFailWrapper;
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

                String url = "http://mopital.herokuapp.com/api/beacon/get/patient/123";

                BaseVolleyRequest<Patient> getUserRequest = new BaseVolleyRequest<Patient>(url, Patient.class, null, new Response.Listener<Patient>() {
                    @Override
                    public void onResponse(Patient patient) {
                        ((PatientFragment)MainActivity.activity.getFragmentManager().findFragmentById(R.id.main_page_patient_fragment)).updateInfo(patient);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //it is possible to cast error to VolleyFailWrapper -> ((VolleyFailWrapper)error).getResult()
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


//                    JsonObjectRequest jsObjRequest = new JsonObjectRequest
//                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                try {
//                                    JSONObject object = (JSONObject) response.getJSONObject("data");
//
//                                    String name = object.getString("name");
//                                    int  age = object.getInt("age");
//                                    double weight = object.getDouble("weight");
//                                    double height = object.getDouble("height");
//                                    String bloodType = object.getString("blood_type");
//                                    String fileNo = object.getString("file_no");
//                                    String admissionDate = object.getString("admission_date");
//
//                                    Patient patient = new Patient(name, bloodType, fileNo, admissionDate, age+"", weight+"", height+"", new ArrayList<Treatment>());
//                                    ((PatientFragment)MainActivity.activity.getFragmentManager().findFragmentById(R.id.main_page_patient_fragment)).updateInfo(patient);
//
//                                }
//                                catch(JSONException ex){
//                                    ex.printStackTrace();
//                                }
//                            }
//                        }, new Response.ErrorListener() {
//
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//
//                            }
//                        });

                VolleyHTTPHandler.getInstance(context).addToRequestQueue(getUserRequest);

            }

        } else {
            BeaconResponse data = intent.getParcelableExtra(PoiService.CAMPAIGN_DATA);
            Log.i("Campaign data received", data.getBody() + " " + data.getTitle());
            //sendNotification(data.getTitle(), data.getBody(), data.getUrl(), context);
        }

    }
}
