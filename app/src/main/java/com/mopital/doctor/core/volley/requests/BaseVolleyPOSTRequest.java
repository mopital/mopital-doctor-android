package com.mopital.doctor.core.volley.requests;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mopital.doctor.core.ResultCode;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.core.volley.responses.VolleyFailWrapper;

import java.util.Map;

/**
 * Created by ahmetkucuk on 09/03/15.
 */
public class BaseVolleyPOSTRequest<T> extends JsonRequest<T> {


    private static final String TAG = "BaseVolleyPOST";

    private final Gson gson = new Gson();
    private final Map<String, String> headers;
    private final Class<T> clazz;
    private final Response.Listener<T> listener;


    public BaseVolleyPOSTRequest(String url, String requestBody, Class<T> clazz, Map<String, String> headers,
                                 Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, requestBody, listener, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {

            String responseStr = new String(response.data);

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(responseStr).getAsJsonObject();

            Result result = gson.fromJson(jsonObject.get("result"), Result.class);
            T data = gson.fromJson(jsonObject.get("data"), clazz);

            if(result instanceof  Result && ResultCode.fromInt(((Result)result).getStatus_code()) != ResultCode.SUCCESS) {

                return Response.error(new VolleyFailWrapper(result));
            }

            return Response.success(
                    data,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

}

