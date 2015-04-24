package com.mopital.doctor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mopital.doctor.R;
import com.mopital.doctor.models.BloodSugarMonitoring;

import java.util.List;

/**
 * Created by AlperCem on 24.4.2015.
 */
public class BloodSugarMonitoringAdapter extends ArrayAdapter<BloodSugarMonitoring> {
    private Context context;
    private List<BloodSugarMonitoring> values;
    private int resource;

    public BloodSugarMonitoringAdapter(Context context, int resource, List<BloodSugarMonitoring> values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);

            final TextView recordedAtTV = (TextView) convertView.findViewById(R.id.recorded_at);
            final TextView urineTV = (TextView) convertView.findViewById(R.id.urine_glucose);
            final TextView bloodTV = (TextView) convertView.findViewById(R.id.blood_glucose);

            holder = new ViewHolder(recordedAtTV, urineTV, bloodTV);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.recordedAtTV.setText(values.get(position).getRecordedAt() + "");
        holder.urineTV.setText(values.get(position).getUrineGlucose());
        holder.bloodTV.setText(values.get(position).getBloodGlucose());

        return convertView;
    }

    private static class ViewHolder {
        public final TextView recordedAtTV;
        public final TextView urineTV;
        public final TextView bloodTV;

        private ViewHolder(TextView recordedAtTV, TextView urineTV, TextView bloodTV) {
            this.recordedAtTV = recordedAtTV;
            this.urineTV = urineTV;
            this.bloodTV = bloodTV;
        }
    }
}