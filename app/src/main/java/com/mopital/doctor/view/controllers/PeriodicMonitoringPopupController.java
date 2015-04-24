package com.mopital.doctor.view.controllers;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mopital.doctor.R;
import com.mopital.doctor.adapters.BloodSugarMonitoringAdapter;
import com.mopital.doctor.adapters.PeriodicMonitoringAdapter;
import com.mopital.doctor.models.PeriodicMonitoring;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmetkucuk on 24/04/15.
 */
public class PeriodicMonitoringPopupController {

    private Context context;

    private Dialog dialog = null;
    private View dialogView = null;
    private WarningPopupViewHolder warningPopupViewHolder;
    private List<PeriodicMonitoring> data;

    public PeriodicMonitoringPopupController(Context context, List<PeriodicMonitoring> data) {
        this.context = context;
        this.data = data;

        dialog = new Dialog(context);
        dialogView = LayoutInflater.from(context).inflate(R.layout.popup_patient_record_detail, null);
        ((TextView)dialogView.findViewById(R.id.popup_header_textview)).setText("Periodic Monitoring");
        dialog.requestWindowFeature(Window.FEATURE_PROGRESS);
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

        PeriodicMonitoringAdapter adapter = new PeriodicMonitoringAdapter(context, R.layout.periodic_monitoring_item, data);
        warningPopupViewHolder.getWarningListView().setAdapter(adapter);
        warningPopupViewHolder.getCloseWarningsButton().setOnClickListener(onCloseWarningClickListener);

        dialog.show();


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
