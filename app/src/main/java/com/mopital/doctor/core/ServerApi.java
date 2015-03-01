package com.mopital.doctor.core;

import android.content.Context;

import com.android.volley.Response;
import com.mopital.doctor.models.Patient;

/**
 * Created by ahmetkucuk on 01/03/15.
 *
 * Define server methods
 */
public interface ServerApi {


    public void getUser(Context context, String userId, Response.Listener<Patient> patientListener, Response.ErrorListener errorListener);
}
