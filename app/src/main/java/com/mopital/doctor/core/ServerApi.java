package com.mopital.doctor.core;

import android.content.Context;

import com.android.volley.Response;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.models.EquipmentPosition;
import com.mopital.doctor.models.MopitalUser;
import com.mopital.doctor.models.Patient;
import com.mopital.doctor.models.Statistics;
import com.mopital.doctor.models.wrappers.EquipmentListWrapper;
import com.mopital.doctor.models.wrappers.MopitalUserWrapper;
import com.mopital.doctor.models.wrappers.PatientBeaconMapWrapper;
import com.mopital.doctor.models.wrappers.PatientListWrapper;

/**
 * Created by ahmetkucuk on 01/03/15.
 * <p/>
 * Define server methods
 */
public interface ServerApi {

    public void getUser(Context context, String userId, Response.Listener<Patient> patientListener, Response.ErrorListener errorListener);

    public void signUp(Context context, String name, String department, String email, String password, Response.Listener<Result> listener, Response.ErrorListener errorListener);

    public void signIn(Context context, String email, String password, Response.Listener<MopitalUser> listener, Response.ErrorListener errorListener);

    public void getAllPatients(Context context, Response.Listener<PatientListWrapper> patientListener, Response.ErrorListener errorListener);

    public void getAllEquipments(Context context, Response.Listener<EquipmentListWrapper> equipmentListener, Response.ErrorListener errorListener);

    public void getLastLocationOfEquipment(Context context, Response.Listener<EquipmentPosition> listener, Response.ErrorListener errorListener);

    public void sendGcmId(Context context, String email, String gcmId, Response.Listener<Result> listener, Response.ErrorListener errorListener);

    public void notifyUser(Context context, String email, String message, Response.Listener<Result> listener, Response.ErrorListener errorListener);

    public void getStatistics(Context context, Response.Listener<Statistics> statisticsListener, Response.ErrorListener errorListener);

    public void getUsers(Context context, Response.Listener<MopitalUserWrapper> listener, Response.ErrorListener errorListener);

    public void getPatientBeaconMap(Context context, Response.Listener<PatientBeaconMapWrapper> listener, Response.ErrorListener errorListener);
}
