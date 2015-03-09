package com.mopital.doctor.core;

import android.content.Context;

import com.android.volley.Response;
import com.mopital.doctor.core.volley.requests.BaseVolleyGETRequest;
import com.mopital.doctor.core.volley.requests.BaseVolleyPOSTRequest;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.models.Patient;


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



    public void getUser(Context context, String userId, Response.Listener<Patient> patientListener, Response.ErrorListener errorListener) {

        BaseVolleyGETRequest<Patient> getUserRequest = new BaseVolleyGETRequest<Patient>(GET_PATIENT_BY_BEACON_UUID_URL + userId, Patient.class, null, patientListener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(getUserRequest);
    }


    public void signUp(Context context, String userName, String password, String email, Response.Listener<Result> listener, Response.ErrorListener errorListener){

        BaseVolleyPOSTRequest<Result> signUpRequest = new BaseVolleyPOSTRequest<Result>(SIGN_UP_URL, Result.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(signUpRequest);
    }

    public void signIn(Context context, String email, String password, Response.Listener<Result> listener, Response.ErrorListener errorListener) {

        BaseVolleyPOSTRequest<Result> signUpRequest = new BaseVolleyPOSTRequest<Result>(SIGN_IN_URL, Result.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(signUpRequest);
    }
}


