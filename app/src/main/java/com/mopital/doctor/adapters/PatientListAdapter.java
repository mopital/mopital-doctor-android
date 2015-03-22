package com.mopital.doctor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mopital.doctor.R;
import com.mopital.doctor.models.Patient;

import java.util.List;

/**
 * Created by AlperCem on 21.3.2015.
 */
public class PatientListAdapter extends ArrayAdapter<Patient> {

    private List<Patient> patientList;
    private Context context;
    private int resource;

    public PatientListAdapter(Context context, int resource, List<Patient> patientList) {
        super(context, resource, patientList);
        this.patientList = patientList;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);

            final TextView patientName = (TextView) convertView.findViewById(R.id.patient_name);

            holder = new ViewHolder(patientName);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.patientName.setText(patientList.get(position).getName());

        return convertView;
    }

    static class ViewHolder {
        public final TextView patientName;

        private ViewHolder(TextView patientName) {
            this.patientName = patientName;
        }
    }
}
