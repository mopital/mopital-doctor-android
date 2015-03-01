package client.android.paying.com.mopitaldoctor.core;

import android.app.Activity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.getpoi.beacon.objects.User;

import org.json.JSONObject;


/**
 * Created by AlperCem on 18.2.2015.
 */
public class JsonParser extends JsonObjectRequest {

    private static final String BASE_API_URL = "http://mopital.herokuapp.com/";
    private static final String GET_USER_URL = BASE_API_URL + "api/user";

    private final Response.Listener listener;

    public JsonParser(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Response.Listener listener1) {
        super(url, jsonRequest, listener, errorListener);
        this.listener = listener;
    }
    public void getUser(String userId, Activity activity) {
    }
}


