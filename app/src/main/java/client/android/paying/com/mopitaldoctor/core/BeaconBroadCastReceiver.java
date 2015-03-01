package client.android.paying.com.mopitaldoctor.core;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.getpoi.beacon.PoiBroadcastReceiver;
import com.getpoi.beacon.PoiService;
import com.getpoi.beacon.objects.BeaconResponse;
import com.getpoi.beacon.objects.Pois;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import client.android.paying.com.mopitaldoctor.R;
import client.android.paying.com.mopitaldoctor.activities.MainActivity;
import fragments.PatientFragment;
import models.Patient;
import models.Treatment;

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

                    JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject object = (JSONObject) response.getJSONObject("data");

                                    String name = object.getString("name");
                                    int  age = object.getInt("age");
                                    double weight = object.getDouble("weight");
                                    double height = object.getDouble("height");
                                    String bloodType = object.getString("blood_type");
                                    String fileNo = object.getString("file_no");
                                    String admissionDate = object.getString("admission_date");

                                    Patient patient = new Patient(name, bloodType, fileNo, admissionDate, age+"", weight+"", height+"", new ArrayList<Treatment>());
                                    ((PatientFragment)MainActivity.activity.getFragmentManager().findFragmentById(R.id.main_page_patient_fragment)).updateInfo(patient);

                                }
                                catch(JSONException ex){
                                    ex.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                VolleyHTTPHandler.getInstance(context).addToRequestQueue(jsObjRequest);

            }

        } else {
            BeaconResponse data = intent.getParcelableExtra(PoiService.CAMPAIGN_DATA);
            Log.i("Campaign data received", data.getBody() + " " + data.getTitle());
            //sendNotification(data.getTitle(), data.getBody(), data.getUrl(), context);
        }

    }
}
