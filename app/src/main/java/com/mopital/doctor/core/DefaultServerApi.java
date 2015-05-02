package com.mopital.doctor.core;

import android.content.Context;

import com.android.volley.Response;
import com.mopital.doctor.core.volley.requests.BaseVolleyGETRequest;
import com.mopital.doctor.core.volley.requests.BaseVolleyPOSTRequest;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.models.Patient;
import com.mopital.doctor.models.wrappers.PatientListWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by AlperCem on 18.2.2015.
 *
 * default implementation of server api
 */
public class DefaultServerApi implements ServerApi {

    private static final String BASE_API_URL = "http://mopital.herokuapp.com/api/";
    private static final String GET_USER_URL = BASE_API_URL + "user/";
    private static final String GET_PATIENT_BY_BEACON_UUID_URL = BASE_API_URL + "beacon/get/patient/";
    private static final String SIGN_IN_URL = BASE_API_URL + "user/login";
    private static final String SIGN_UP_URL = BASE_API_URL + "user/register";
    private static final String GET_ALL_PATIENTS_URL = BASE_API_URL + "patient/all";

    public void getUser(Context context, String userId, Response.Listener<Patient> patientListener, Response.ErrorListener errorListener) {

        BaseVolleyGETRequest<Patient> getUserRequest = new BaseVolleyGETRequest<Patient>(GET_PATIENT_BY_BEACON_UUID_URL + userId, Patient.class, null, patientListener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(getUserRequest);
    }


    public void signUp(Context context, String userName, String password, String email, Response.Listener<Result> listener, Response.ErrorListener errorListener){

        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("email", email);
        params.put("password", password);

        BaseVolleyPOSTRequest<Result> signUpRequest = new BaseVolleyPOSTRequest<Result>(SIGN_UP_URL, params, Result.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(signUpRequest);
    }

    public void signIn(Context context, String email, String password, Response.Listener<Result> listener, Response.ErrorListener errorListener) {

        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

        BaseVolleyPOSTRequest<Result> signInRequest = new BaseVolleyPOSTRequest<Result>(SIGN_IN_URL, params, Result.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(signInRequest);
    }

    @Override
    public void getAllPatients(Context context, Response.Listener<PatientListWrapper> listener, Response.ErrorListener errorListener) {

        BaseVolleyGETRequest<PatientListWrapper> getAllPatientsRequest = new BaseVolleyGETRequest<PatientListWrapper>(GET_ALL_PATIENTS_URL, PatientListWrapper.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(getAllPatientsRequest);
    }

}


