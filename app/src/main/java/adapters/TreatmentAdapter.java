package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import client.android.paying.com.mopitaldoctor.R;
import models.Patient;
import models.Treatment;

/**
 * Created by AlperCem on 28.2.2015.
 */
public class TreatmentAdapter extends ArrayAdapter<Patient> {

    private Context context;
    private List<Treatment> values;
    private int resource;

    public TreatmentAdapter(Context context, int resource, List<Treatment> values) {
        super(context, resource);
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

            final TextView dateTV = (TextView) convertView.findViewById(R.id.treatment_date);
            final TextView timeTV = (TextView) convertView.findViewById(R.id.treatment_time);
            final TextView tensionTV = (TextView) convertView.findViewById(R.id.treatment_tension);
            final TextView temperatureTV = (TextView) convertView.findViewById(R.id.treatment_temperature);
            final TextView pulseTV = (TextView) convertView.findViewById(R.id.treatment_pulse);
            final TextView respirationTV = (TextView) convertView.findViewById(R.id.treatment_respiration);
            final TextView painTV = (TextView) convertView.findViewById(R.id.treatment_pain);
            final TextView treatmentTV = (TextView) convertView.findViewById(R.id.treatment_treatment);

            holder = new ViewHolder(dateTV, timeTV, tensionTV, temperatureTV, pulseTV, respirationTV, painTV, treatmentTV);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        holder.dateTV.setText(values.get(position).getDate());
        holder.timeTV.setText(values.get(position).getTime());
        holder.tensionTV.setText(values.get(position).getTension());
        holder.temperatureTV.setText(values.get(position).getTemperature());
        holder.pulseTV.setText(values.get(position).getPulse());
        holder.respirationTV.setText(values.get(position).getRespiration());
        holder.painTV.setText(values.get(position).getPain());
        holder.treatmentTV.setText(values.get(position).getTreatment());

        return convertView;
    }

    private static class ViewHolder {
        public final TextView dateTV;
        public final TextView timeTV;
        public final TextView tensionTV;
        public final TextView temperatureTV;
        public final TextView pulseTV;
        public final TextView respirationTV;
        public final TextView painTV;
        public final TextView treatmentTV;

        private ViewHolder(TextView dateTV, TextView timeTV, TextView tensionTV, TextView temperatureTV, TextView pulseTV,
                           TextView respirationTV, TextView painTV, TextView treatmentTV) {
            this.dateTV = dateTV;
            this.timeTV = timeTV;
            this.tensionTV = tensionTV;
            this.temperatureTV = temperatureTV;
            this.pulseTV = pulseTV;
            this.respirationTV = respirationTV;
            this.painTV = painTV;
            this.treatmentTV = treatmentTV;
        }
    }
}
