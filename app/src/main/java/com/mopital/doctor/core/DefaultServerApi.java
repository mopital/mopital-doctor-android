package com.mopital.doctor.core;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mopital.doctor.R;
import com.mopital.doctor.activities.MainActivity;
import com.mopital.doctor.core.volley.requests.BaseVolleyRequest;
import com.mopital.doctor.fragments.PatientFragment;
import com.mopital.doctor.models.Patient;

import org.json.JSONObject;


/**
 * Created by AlperCem on 18.2.2015.
 *
 * default implementation of server api
 */
public class DefaultServerApi implements ServerApi {

    private static final String BASE_API_URL = "http://mopital.herokuapp.com/";
    private static final String GET_USER_URL = BASE_API_URL + "api/user/";
    private static final String GET_PATIENT_BY_BEACON_UUID_URL = BASE_API_URL + "api/beacon/get/patient/";



    public void getUser(Context context, String userId, Response.Listener<Patient> patientListener, Response.ErrorListener errorListener) {

        BaseVolleyRequest<Patient> getUserRequest = new BaseVolleyRequest<Patient>(GET_PATIENT_BY_BEACON_UUID_URL + userId, Patient.class, null, patientListener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(getUserRequest);
    }
}


