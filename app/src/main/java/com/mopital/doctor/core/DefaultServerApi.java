package com.mopital.doctor.core;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.mopital.doctor.core.volley.requests.BaseVolleyGETRequest;
import com.mopital.doctor.core.volley.requests.BaseVolleyPOSTRequest;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.models.EquipmentPosition;
import com.mopital.doctor.models.MopitalUser;
import com.mopital.doctor.models.Patient;
import com.mopital.doctor.models.Statistics;
import com.mopital.doctor.models.wrappers.EquipmentListWrapper;
import com.mopital.doctor.models.wrappers.MopitalUserWrapper;
import com.mopital.doctor.models.wrappers.PatientBeaconMapWrapper;
import com.mopital.doctor.models.wrappers.PatientListWrapper;

import org.json.JSONObject;


/**
 * Created by AlperCem on 18.2.2015.
 * <p/>
 * default implementation of server api
 */
public class DefaultServerApi implements ServerApi {

    private static final String TAG = "DefaultServerApi";

    private static final String BASE_API_URL = "http://mopital.herokuapp.com/api/";
    private static final String GET_USER_URL = BASE_API_URL + "user/";
    private static final String GET_PATIENT_BY_BEACON_UUID_URL = BASE_API_URL + "patient/beacon/get/";
    private static final String SIGN_IN_URL = BASE_API_URL + "user/login";
    private static final String SIGN_UP_URL = BASE_API_URL + "user/register";
    private static final String GET_ALL_PATIENTS_URL = BASE_API_URL + "patient/all";
    private static final String GET_ALL_EQUIPMENTS_URL = BASE_API_URL + "equipment/all";
    private static final String GET_LAST_POSITION_OF_EQUIPMENT_URL = BASE_API_URL + "equipment/last/position/";
    private static final String SAVE_GCM_URL = BASE_API_URL + "user/save/gcm";
    private static final String NOTIFY_USER_URL = BASE_API_URL + "user/notify";
    private static final String STATISTICS_URL = BASE_API_URL + "statistics";
    private static final String GET_USERS_URL = BASE_API_URL + "users";
    private static final String GET_PATIENT_BEACON_MAP_URL = BASE_API_URL + "patient/beacon/map";

    public void getUser(Context context, String userId, Response.Listener<Patient> patientListener, Response.ErrorListener errorListener) {

        BaseVolleyGETRequest<Patient> getUserRequest = new BaseVolleyGETRequest<Patient>(GET_PATIENT_BY_BEACON_UUID_URL + userId, Patient.class, null, patientListener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(getUserRequest);
    }

    public void signUp(Context context, String name, String department, String email, String password, Response.Listener<Result> listener, Response.ErrorListener errorListener) {

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("name", name);
            requestBody.put("department", department);
            requestBody.put("email", email);
            requestBody.put("password", password);
        }catch (Exception e) {
            Log.e(TAG, "exception", e);
        }
        BaseVolleyPOSTRequest<Result> signUpRequest = new BaseVolleyPOSTRequest<Result>(SIGN_UP_URL, requestBody.toString(), Result.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(signUpRequest);
    }

    public void signIn(Context context, String email, String password, Response.Listener<MopitalUser> listener, Response.ErrorListener errorListener) {

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("email", email);
            requestBody.put("password", password);
        }catch (Exception e) {
            Log.e(TAG, "exception", e);
        }

        BaseVolleyPOSTRequest<MopitalUser> signInRequest = new BaseVolleyPOSTRequest<MopitalUser>(SIGN_IN_URL, requestBody.toString(), MopitalUser.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(signInRequest);
    }

    public void getAllPatients(Context context, Response.Listener<PatientListWrapper> listener, Response.ErrorListener errorListener) {

        BaseVolleyGETRequest<PatientListWrapper> getAllPatientsRequest = new BaseVolleyGETRequest<PatientListWrapper>(GET_ALL_PATIENTS_URL, PatientListWrapper.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(getAllPatientsRequest);
    }

    public void getAllEquipments(Context context, Response.Listener<EquipmentListWrapper> equipmentListener, Response.ErrorListener errorListener) {

        BaseVolleyGETRequest<EquipmentListWrapper> getAllEquipmentsRequest = new BaseVolleyGETRequest<EquipmentListWrapper>(GET_ALL_EQUIPMENTS_URL, EquipmentListWrapper.class, null, equipmentListener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(getAllEquipmentsRequest);
    }

    public void getLastLocationOfEquipment(Context context, Response.Listener<EquipmentPosition> listener, Response.ErrorListener errorListener) {

        BaseVolleyGETRequest<EquipmentPosition> getEquipmentPosition = new BaseVolleyGETRequest<EquipmentPosition>(GET_LAST_POSITION_OF_EQUIPMENT_URL, EquipmentPosition.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(getEquipmentPosition);
    }

    @Override
    public void sendGcmId(Context context, String email, String gcmId, Response.Listener<Result> listener, Response.ErrorListener errorListener) {

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("userId", email);
            requestBody.put("gcmId", gcmId);
        }catch (Exception e) {
            Log.e(TAG, "exception", e);
        }

        BaseVolleyPOSTRequest<Result> request = new BaseVolleyPOSTRequest<Result>(SAVE_GCM_URL, requestBody.toString(), Result.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(request);
    }

    @Override
    public void notifyUser(Context context, String email, String message, Response.Listener<Result> listener, Response.ErrorListener errorListener) {

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("userId", email);
            requestBody.put("messageToSend", message);
        }catch (Exception e) {
            Log.e(TAG, "exception", e);
        }

        BaseVolleyPOSTRequest<Result> request = new BaseVolleyPOSTRequest<Result>(NOTIFY_USER_URL, requestBody.toString(), Result.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(request);
    }

    @Override
    public void getStatistics(Context context, Response.Listener<Statistics> listener, Response.ErrorListener errorListener) {

        BaseVolleyGETRequest<Statistics> request = new BaseVolleyGETRequest<Statistics>(STATISTICS_URL, Statistics.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(request);
    }

    public void getUsers(Context context, Response.Listener<MopitalUserWrapper> listener, Response.ErrorListener errorListener){

        BaseVolleyGETRequest<MopitalUserWrapper> request = new BaseVolleyGETRequest<MopitalUserWrapper>(GET_USERS_URL, MopitalUserWrapper.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(request);
    }

    @Override
    public void getPatientBeaconMap(Context context, Response.Listener<PatientBeaconMapWrapper> listener, Response.ErrorListener errorListener) {

        BaseVolleyGETRequest<PatientBeaconMapWrapper> request = new BaseVolleyGETRequest<PatientBeaconMapWrapper>(GET_PATIENT_BEACON_MAP_URL, PatientBeaconMapWrapper.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(request);

    }
}


