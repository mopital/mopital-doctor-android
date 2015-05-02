package com.mopital.doctor.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

/**
 * Created by ahmetkucuk on 02/05/15.
 */
public class GUIHelperFunctions {

    private static ProgressDialog progressDialog = null;
    private static int counter = 0;

    public static void showProgressDialog(Context context, String message) {
        counter++;

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        } else if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public static void hideProgressDialog() {
        if(progressDialog == null)
            return;
        if(progressDialog != null && !progressDialog.isShowing())
            return;

        counter--;
        if (progressDialog != null && counter <= 0) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
