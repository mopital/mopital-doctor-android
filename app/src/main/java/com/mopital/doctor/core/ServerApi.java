package com.mopital.doctor.core;

import android.content.Context;

import com.android.volley.Response;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.models.Patient;

/**
 * Created by ahmetkucuk on 01/03/15.
 *
 * Define server methods
 */
public interface ServerApi {

    public void getUser(Context context, String userId, Response.Listener<Patient> patientListener, Response.ErrorListener errorListener);

    public void signUp(Context context, String userName, String password, String email, Response.Listener<Result> listener, Response.ErrorListener errorListener);

    public void signIn(Context context, String email, String password, Response.Listener<Result> listener, Response.ErrorListener errorListener);
}
