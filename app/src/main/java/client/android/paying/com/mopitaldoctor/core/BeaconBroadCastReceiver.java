package client.android.paying.com.mopitaldoctor.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.getpoi.beacon.PoiService;
import com.getpoi.beacon.objects.BeaconResponse;
import com.getpoi.beacon.objects.Pois;

import java.util.List;

/**
 * Created by ahmetkucuk on 04/02/15.
 */
public class BeaconBroadCastReceiver extends BroadcastReceiver {

    private static final String TAG = "BeaconBroadCastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "onReceive action: " + action);
        if (action.equals(PoiService.ACTION_DETECT)) {
            List<Pois> beacons = (List<Pois>) intent.getSerializableExtra("POIS");
            for (Pois beacon : beacons) {
                Log.i("Poi found", "major: " + beacon.majorId + " minor: " + beacon.minorId);

            }

        } else {
            BeaconResponse data = intent.getParcelableExtra(PoiService.CAMPAIGN_DATA);
            Log.i("Campaign data received", data.getBody() + " " + data.getTitle());
            //sendNotification(data.getTitle(), data.getBody(), data.getUrl(), context);
        }

    }
}
