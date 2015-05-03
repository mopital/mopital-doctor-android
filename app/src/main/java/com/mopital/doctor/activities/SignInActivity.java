package com.mopital.doctor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mopital.doctor.R;
import com.mopital.doctor.core.PreferenceService;
import com.mopital.doctor.core.ServerApi;
import com.mopital.doctor.core.ServerApiProvider;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.models.MopitalUser;
import com.mopital.doctor.utils.GUIHelperFunctions;

public class SignInActivity extends ActionBarActivity {

    private static final String TAG = "SignInActivity";

	private EditText emailEditText;
	private EditText passwordEditText;
	private ServerApi api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_in_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		emailEditText = (EditText) findViewById(R.id.email_sign_in);
		passwordEditText = (EditText) findViewById(R.id.password_sign_in);

		api = ServerApiProvider.serverApi();

		if (checkIfCredentialsExist()) {
            GUIHelperFunctions.showProgressDialog(SignInActivity.this, "Logging In");
            signIn(PreferenceService.getEmail(SignInActivity.this.getApplicationContext()), PreferenceService.getPassword(SignInActivity.this.getApplicationContext()));
		}
	}

	private boolean checkIfCredentialsExist() {
		return PreferenceService.hasCredentials(SignInActivity.this);
	}

	public void signInClicked(View v) {

		if (emailEditText.getText().toString().isEmpty()) {
			Toast.makeText(SignInActivity.this,
					getResources().getString(R.string.email_empty),
					Toast.LENGTH_SHORT).show();
		} else if (passwordEditText.getText().toString().isEmpty()) {
			Toast.makeText(SignInActivity.this,
					getResources().getString(R.string.password_empty),
					Toast.LENGTH_SHORT).show();
		} else {
            signIn(emailEditText.getText().toString(), passwordEditText.getText().toString());
		}
	}

    private void signIn(final String email, final String password) {
        api.signIn(SignInActivity.this, email,
                password, new Response.Listener<MopitalUser>() {
                    @Override
                    public void onResponse(MopitalUser response) {
                        GUIHelperFunctions.hideProgressDialog();
                        Log.d(TAG, response.toString());
                        PreferenceService.saveEmail(SignInActivity.this.getApplicationContext(), email);
                        PreferenceService.savePassword(SignInActivity.this.getApplicationContext(), password);
                        PreferenceService.saveName(SignInActivity.this.getApplicationContext(), response.getName());
                        Intent i = new Intent(SignInActivity.this, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        GUIHelperFunctions.hideProgressDialog();
                        Toast.makeText(SignInActivity.this, "Login failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        PreferenceService.removeCredentials(SignInActivity.this.getApplicationContext());
                    }
                }
        );
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
