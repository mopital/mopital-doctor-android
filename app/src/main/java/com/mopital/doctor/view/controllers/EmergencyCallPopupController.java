package com.mopital.doctor.view.controllers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mopital.doctor.R;
import com.mopital.doctor.core.ServerApi;
import com.mopital.doctor.core.ServerApiProvider;
import com.mopital.doctor.core.gcm.GCMRegistrationService;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.models.MopitalUser;
import com.mopital.doctor.models.wrappers.MopitalUserWrapper;
import com.mopital.doctor.utils.GUIHelperFunctions;

import java.util.ArrayList;
import java.util.List;

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
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);
    }

    public void setUsers(){
        ServerApiProvider.serverApi().getUsers(dialogView.getContext(), new Response.Listener<MopitalUserWrapper>() {
            @Override
            public void onResponse(MopitalUserWrapper response) {
                List<MopitalUser> users = response.getUserList();
                EmergencyAdapter adapter = new EmergencyAdapter(context, R.id.spinnerTV, users);
                warningPopupViewHolder.emailBox.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("EMERGENCY_POPUP", error.getMessage().toString());
            }
        });
    }

    public void showPopup(){
        if (warningPopupViewHolder == null) {
            warningPopupViewHolder = new WarningPopupViewHolder(dialogView);
        }

        warningPopupViewHolder.getSendCallButton().setOnClickListener(onSendCallClickListener);
        dialog.show();

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.9);
        dialog.getWindow().setLayout(screenWidth, WindowManager.LayoutParams.WRAP_CONTENT);

        GUIHelperFunctions.showProgressDialog(context, "Loading users...");
        setUsers();
        GUIHelperFunctions.hideProgressDialog();
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
        private Spinner emailBox;
        private Button sendCallButton;
        private MopitalUser selectedUser;

        public WarningPopupViewHolder(View view) {
            messageBox = (EditText) view.findViewById(R.id.message);
            emailBox = (Spinner) view.findViewById(R.id.userSpinner);
            emailBox.setOnItemSelectedListener(listener);
            sendCallButton = (Button) view.findViewById(R.id.sendCallButton);
        }

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MopitalUser user = (MopitalUser) parent.getItemAtPosition(position);
                selectedUser = user;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        public String getEmail(){
            if(selectedUser != null)
                return selectedUser.getEmail();
            else
                return "";
        }

        public String getMessage() {
            return messageBox.getText().toString();
        }

        public Button getSendCallButton() {
            return sendCallButton;
        }
    }

    static class EmergencyAdapter extends ArrayAdapter<MopitalUser> {
        private Context context;

        public EmergencyAdapter(Context context, int resource, List<MopitalUser> values) {
            super(context, resource, values);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView label = new TextView(context);
            label.setTextColor(Color.BLACK);
            label.setText(this.getItem(position).getName());
            return label;
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            TextView label = new TextView(context);
            label.setTextColor(Color.BLACK);
            label.setText(this.getItem(position).getName());
            return label;
        }
    }
}
