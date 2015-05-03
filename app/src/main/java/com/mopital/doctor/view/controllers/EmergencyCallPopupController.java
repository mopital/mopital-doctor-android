package com.mopital.doctor.view.controllers;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mopital.doctor.R;
import com.mopital.doctor.core.ServerApiProvider;
import com.mopital.doctor.core.gcm.GCMRegistrationService;
import com.mopital.doctor.core.volley.responses.Result;

/**
 * Created by AlperCem on 3.5.2015.
 */
public class EmergencyCallPopupController {

    private Context context;

    private Dialog dialog = null;
    private View dialogView = null;
    private WarningPopupViewHolder warningPopupViewHolder;

    public EmergencyCallPopupController(Context context) {
        this.context = context;

        dialog = new Dialog(context);
        dialogView = LayoutInflater.from(context).inflate(R.layout.emergency_call, null);
        dialog.requestWindowFeature(Window.FEATURE_PROGRESS);
        dialog.setContentView(dialogView);
    }

    public void showPopup() {
        if (warningPopupViewHolder == null) {
            warningPopupViewHolder = new WarningPopupViewHolder(dialogView);
        }

        warningPopupViewHolder.getSendCallButton().setOnClickListener(onSendCallClickListener);
        dialog.show();
    }

    private View.OnClickListener onSendCallClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GCMRegistrationService service = new GCMRegistrationService(context.getApplicationContext());
            service.register();

            ServerApiProvider.serverApi().notifyUser(context, warningPopupViewHolder.getEmail(),
                    warningPopupViewHolder.getMessage(), new Response.Listener<Result>() {
                        @Override
                        public void onResponse(Result response) {
                            dialog.dismiss();
                            Toast.makeText(context.getApplicationContext(), "Message has been sent to " + warningPopupViewHolder.getEmail() + " successfully!", Toast.LENGTH_SHORT).show();
                            Log.d("EMERGENCY_CALL_POPUP_CONTROLLER", "send notify successful");
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("EMERGENCY_CALL_POPUP_CONTROLLER", error.getMessage().toString());
                        }
                    });
        }
    };

    static class WarningPopupViewHolder {

        private EditText messageBox;
        private EditText emailBox;
        private Button sendCallButton;

        public WarningPopupViewHolder(View view) {
            messageBox = (EditText) view.findViewById(R.id.message);
            emailBox = (EditText) view.findViewById(R.id.email);
            sendCallButton = (Button) view.findViewById(R.id.sendCallButton);
        }

        public String getEmail() {
            return emailBox.getText().toString();
        }

        public String getMessage() {
            return messageBox.getText().toString();
        }

        public Button getSendCallButton() {
            return sendCallButton;
        }
    }
}
