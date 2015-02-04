package client.android.paying.com.mopitaldoctor.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.getpoi.beacon.PoiService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import client.android.paying.com.mopitaldoctor.R;


public class MainActivity extends Activity {


    @InjectView(R.id.startServiceBtn)
    Button startServiceBtn;

    @InjectView(R.id.sendUserInfo)
    Button sendUserInfo;

    @InjectView(R.id.sendNotificationToken)
    Button sendNotificationToken;

    @InjectView(R.id.stopServiceBtn)
    Button stopServiceBtn;

    private static final String TAG = "MainActivity";
    private static final String MOPITAL_SECRET_KEY = "684AE112-170A-4BE8-A30B-C2F0BD17109F";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked start service button");
                Intent service = new Intent(MainActivity.this, PoiService.class);
                service.putExtra(PoiService.ACTION_KEY, PoiService.START_SERVICE);
                service.putExtra(PoiService.SECRET_KEY, MOPITAL_SECRET_KEY);
                service.putExtra(PoiService.UNIQUE_ID_KEY, "userUniqueId");
                startService(service);
            }
        });

        sendUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent service = new Intent(MainActivity.this, PoiService.class);
                service.putExtra(PoiService.ACTION_KEY, PoiService.SEND_USER_DATA);
                service.putExtra(PoiService.SECRET_KEY, MOPITAL_SECRET_KEY);
                service.putExtra(PoiService.UNIQUE_ID_KEY, "userUniqueId");
                service.putExtra(PoiService.NAME, "Name");
                service.putExtra(PoiService.SURNAME, "Surname");
                service.putExtra(PoiService.EMAIL, "Email");
                service.putExtra(PoiService.TELNUMBER, "TelNo");
                startService(service);
            }
        });

        sendNotificationToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service = new Intent(MainActivity.this, PoiService.class);
                service.putExtra(PoiService.ACTION_KEY, PoiService.SEND_NOTIFICATION_TOKEN);
                service.putExtra(PoiService.SECRET_KEY, MOPITAL_SECRET_KEY);
                service.putExtra(PoiService.NOTIFICATION_TOKEN_KEY, "UserRemoteNotificationToken");
                startService(service);

            }
        });

        stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, PoiService.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
