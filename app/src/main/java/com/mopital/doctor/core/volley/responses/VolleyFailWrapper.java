package com.mopital.doctor.core.volley.responses;

import com.android.volley.VolleyError;

/**
 * Created by ahmetkucuk on 01/03/15.
 */
public class VolleyFailWrapper extends VolleyError {

    private Result result;

    public VolleyFailWrapper(Result r) {
        this.result = r;
    }

    public Result getResult() {
        return result;
    }
}
