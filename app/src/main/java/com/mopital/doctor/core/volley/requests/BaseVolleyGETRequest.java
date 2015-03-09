package com.mopital.doctor.core.volley.requests;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.util.Map;

import com.mopital.doctor.core.ResultCode;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.core.volley.responses.VolleyFailWrapper;

/**
 * Created by ahmetkucuk on 01/03/15.
 */
public class BaseVolleyGETRequest<T> extends Request<T> {

    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;


    public BaseVolleyGETRequest(String url, Class<T> clazz, Map<String, String> headers,
                                Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;

    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {

            String responseStr = new String(response.data);

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(responseStr).getAsJsonObject();

            Result result = gson.fromJson(jsonObject.get("result"), Result.class);
            T data = gson.fromJson(jsonObject.get("data"), clazz);

            if(ResultCode.fromInt(result.getStatus_code()) == ResultCode.FAIL) {

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
