package com.mopital.doctor.core;

import android.app.Activity;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


/**
 * Created by AlperCem on 18.2.2015.
 */
public class DefaultServerApi extends JsonObjectRequest {

    private static final String BASE_API_URL = "http://com.mopital.herokuapp.com/";
    private static final String GET_USER_URL = BASE_API_URL + "api/user";

    private final Response.Listener listener;

    public DefaultServerApi(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Response.Listener listener1) {
        super(url, jsonRequest, listener, errorListener);
        this.listener = listener;
    }
    public void getUser(String userId, Activity activity) {
    }
}


