package com.mopital.doctor.view.controllers;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mopital.doctor.R;
import com.mopital.doctor.adapters.BloodSugarMonitoringAdapter;
import com.mopital.doctor.models.BloodSugarMonitoring;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmetkucuk on 24/04/15.
 */
public class BloodSugarMonitoringPopupController {

    private Context context;

    private Dialog dialog = null;
    private View dialogView = null;
    private WarningPopupViewHolder warningPopupViewHolder;
    private List<BloodSugarMonitoring> data;

    public BloodSugarMonitoringPopupController(Context context, List<BloodSugarMonitoring> data) {
        this.context = context;
        this.data = data;

        dialog = new Dialog(context);
        dialogView = LayoutInflater.from(context).inflate(R.layout.popup_patient_record_detail, null);
        ((TextView)dialogView.findViewById(R.id.popup_header_textview)).setText("Blood Sugar Monitoring");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        dialog.getWindow().setAttributes(params);
        dialog.setContentView(dialogView);
    }

    public void showPopup() {

        if(data == null || data.size() == 0) {
            if(dialog.isShowing()) {
                dialog.dismiss();
            }
            return;
        }

        if(warningPopupViewHolder == null) {
            warningPopupViewHolder = new WarningPopupViewHolder(dialogView);
        }

        BloodSugarMonitoringAdapter adapter = new BloodSugarMonitoringAdapter(context, R.layout.blood_sugar_monitoring_item, data);
        warningPopupViewHolder.getWarningListView().setAdapter(adapter);
        warningPopupViewHolder.getCloseWarningsButton().setOnClickListener(onCloseWarningClickListener);

        dialog.show();

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.9);
        dialog.getWindow().setLayout(screenWidth, WindowManager.LayoutParams.WRAP_CONTENT);

    }

    private View.OnClickListener onCloseWarningClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(dialog != null)
                dialog.dismiss();
        }
    };

    static class WarningPopupViewHolder {

        private ListView warningListView;
        private Button closeWarningsButton;

        public WarningPopupViewHolder(View view) {
            warningListView = (ListView)view.findViewById(R.id.popup_listview);
            closeWarningsButton = (Button)view.findViewById(R.id.close_dialog_button);
        }

        public ListView getWarningListView() {
            return warningListView;
        }

        public Button getCloseWarningsButton() {
            return closeWarningsButton;
        }
    }

}
