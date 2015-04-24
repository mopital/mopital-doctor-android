package com.mopital.doctor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mopital.doctor.R;
import com.mopital.doctor.models.PeriodicMonitoring;
import com.mopital.doctor.models.Treatment;

import java.util.List;

/**
 * Created by AlperCem on 24.4.2015.
 */
public class PeriodicMonitoringAdapter extends ArrayAdapter<PeriodicMonitoring> {
    private Context context;
    private List<PeriodicMonitoring> values;
    private int resource;

    public PeriodicMonitoringAdapter(Context context, int resource, List<PeriodicMonitoring> values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);

            final TextView recordedAtTV = (TextView) convertView.findViewById(R.id.recorded_at);
            final TextView tensionTV = (TextView) convertView.findViewById(R.id.tension);
            final TextView feverTV = (TextView) convertView.findViewById(R.id.fever);
            final TextView pulseTV = (TextView) convertView.findViewById(R.id.pulse);
            final TextView respirationTV = (TextView) convertView.findViewById(R.id.respiration);
            final TextView painTV = (TextView) convertView.findViewById(R.id.pain);

            holder = new ViewHolder(recordedAtTV, tensionTV, feverTV, pulseTV, respirationTV, painTV);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        holder.recordedAtTV.setText(values.get(position).getRecordedAt() + "");
        holder.tensionTV.setText(values.get(position).getTension() + "");
        holder.feverTV.setText(values.get(position).getFever() + "");
        holder.pulseTV.setText(values.get(position).getPulse() + "");
        holder.respirationTV.setText(values.get(position).getRespiration());
        holder.painTV.setText(values.get(position).getPain());

        return convertView;
    }

    private static class ViewHolder {
        public final TextView recordedAtTV;
        public final TextView tensionTV;
        public final TextView feverTV;
        public final TextView pulseTV;
        public final TextView respirationTV;
        public final TextView painTV;

        private ViewHolder(TextView recordedAtTV, TextView tensionTV, TextView feverTV, TextView pulseTV,
                           TextView respirationTV, TextView painTV) {
            this.recordedAtTV = recordedAtTV;
            this.tensionTV = tensionTV;
            this.feverTV = feverTV;
            this.pulseTV = pulseTV;
            this.respirationTV = respirationTV;
            this.painTV = painTV;
        }
    }
}
