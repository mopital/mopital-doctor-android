package com.mopital.doctor.core;

import android.content.Context;

import com.android.volley.Response;
import com.mopital.doctor.core.volley.requests.BaseVolleyGETRequest;
import com.mopital.doctor.core.volley.requests.BaseVolleyPOSTRequest;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.models.EquipmentPosition;
import com.mopital.doctor.models.Patient;
import com.mopital.doctor.models.wrappers.EquipmentListWrapper;
import com.mopital.doctor.models.wrappers.PatientListWrapper;


/**
 * Created by AlperCem on 18.2.2015.
 * <p/>
 * default implementation of server api
 */
public class DefaultServerApi implements ServerApi {

    private static final String BASE_API_URL = "http://mopital.herokuapp.com/api/";
    private static final String GET_USER_URL = BASE_API_URL + "user/";
    private static final String GET_PATIENT_BY_BEACON_UUID_URL = BASE_API_URL + "patient/beacon/get/";
    private static final String SIGN_IN_URL = BASE_API_URL + "user/login";
    private static final String SIGN_UP_URL = BASE_API_URL + "user/register";
    private static final String GET_ALL_PATIENTS_URL = BASE_API_URL + "patient/all";
    private static final String GET_ALL_EQUIPMENTS_URL = BASE_API_URL + "equipment/all";
    private static final String GET_LAST_POSITION_OF_EQUIPMENT_URL = BASE_API_URL + "equipment/last/position/";

    public void getUser(Context context, String userId, Response.Listener<Patient> patientListener, Response.ErrorListener errorListener) {

        BaseVolleyGETRequest<Patient> getUserRequest = new BaseVolleyGETRequest<Patient>(GET_PATIENT_BY_BEACON_UUID_URL + userId, Patient.class, null, patientListener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(getUserRequest);
    }

    public void signUp(Context context, String userName, String password, String email, Response.Listener<Result> listener, Response.ErrorListener errorListener) {

        BaseVolleyPOSTRequest<Result> signUpRequest = new BaseVolleyPOSTRequest<Result>(SIGN_UP_URL, Result.class, null, listener, errorListener);

        VolleyHTTPHandler.getInstance(context).addToRequestQueue(signUpRequest);
    }

    public void signIn(Context context, String email, String password, Response.Listener<Result> listener, Response.ErrorListener errorListener) {

        BaseVolleyPOSTRequest<Result> signInRequest = new BaseVolleyPOSTRequest<Result>(SIGN_IN_URL, Result.class, null, listener, errorListener);

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

}


