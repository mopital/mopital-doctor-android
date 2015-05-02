package com.mopital.doctor.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.getpoi.beacon.PoiService;
import com.github.clans.fab.FloatingActionMenu;
import com.mopital.doctor.R;
import com.mopital.doctor.core.ServerApi;
import com.mopital.doctor.core.ServerApiProvider;
import com.mopital.doctor.core.gcm.GCMRegistrationService;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.fragments.NavigationDrawerFragment;
import com.mopital.doctor.models.MopitalUser;
import com.mopital.doctor.models.Patient;
import com.mopital.doctor.utils.Constants;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.app_bar)
    Toolbar toolbar;

    private static final String TAG = "MainActivity";
    private static final String MOPITAL_SECRET_KEY = "684AE112-170A-4BE8-A30B-C2F0BD17109F";
    public static Activity activity;

    final String ID = "userUniqueIdTest1";

    private Handler mUiHandler = new Handler();
    private ServerApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        activity = this;

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        final FloatingActionMenu menu1 = (FloatingActionMenu) findViewById(R.id.fab_menu);
        menu1.hideMenuButton(false);

        int delay = 400;
        mUiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                menu1.showMenuButton(true);
            }
        }, delay);
        delay += 150;
        menu1.setClosedOnTouchOutside(true);


        api = ServerApiProvider.serverApi();
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.getMessage() + "");
            }
        };

        Response.Listener<MopitalUser> listener = new Response.Listener<MopitalUser>() {
            @Override
            public void onResponse(MopitalUser result) {
                Log.d(TAG, result.toString());
            }
        };

//        api.signUp(this, "alpercem", "doctor", "alpercempolat@hotmail.com", "Alper@2014", listener, errorListener);
        //api.signIn(this, "alpercempolat@hotmail.com", "Alper@2014", listener, errorListener);
        //Test login
        GCMRegistrationService service = new GCMRegistrationService(this.getApplicationContext());
        service.register();

        api.notifyUser(this, "alpercempolat@hotmail.com", "Benim mesajim", new Response.Listener<Result>() {
            @Override
            public void onResponse(Result response) {
                Log.d(TAG, "send notify successful");
            }
        }, errorListener);
    }

    private void callLoginActivity() {
        Intent i = new Intent(MainActivity.this, SignInActivity.class);
        startActivityForResult(i, Constants.SIGN_IN_REQUEST_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent service = new Intent(MainActivity.this, PoiService.class);
        service.putExtra(PoiService.ACTION_KEY, PoiService.START_SERVICE);
        service.putExtra(PoiService.SECRET_KEY, MOPITAL_SECRET_KEY);
        service.putExtra(PoiService.UNIQUE_ID_KEY, ID);
        startService(service);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(MainActivity.this, PoiService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
