package client.android.paying.com.mopitaldoctor.activities;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.getpoi.beacon.PoiService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import client.android.paying.com.mopitaldoctor.R;
import client.android.paying.com.mopitaldoctor.core.JsonParser;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    private static final String MOPITAL_SECRET_KEY = "684AE112-170A-4BE8-A30B-C2F0BD17109F";
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        activity = this;

        final String ID = "userUniqueIdTest1";

        Intent service = new Intent(MainActivity.this, PoiService.class);
        service.putExtra(PoiService.ACTION_KEY, PoiService.START_SERVICE);
        service.putExtra(PoiService.SECRET_KEY, MOPITAL_SECRET_KEY);
        service.putExtra(PoiService.UNIQUE_ID_KEY, ID);
        startService(service);
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
