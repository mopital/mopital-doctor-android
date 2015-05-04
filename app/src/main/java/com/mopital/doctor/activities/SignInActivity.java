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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mopital.doctor.R;
import com.mopital.doctor.core.PreferenceService;
import com.mopital.doctor.core.ServerApi;
import com.mopital.doctor.core.ServerApiProvider;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.models.MopitalUser;
import com.mopital.doctor.utils.Constants;
import com.mopital.doctor.utils.GUIHelperFunctions;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignInActivity extends ActionBarActivity {

    private static final String TAG = "SignInActivity";

	@InjectView(R.id.email_sign_in) EditText emailEditText;
    @InjectView(R.id.password_sign_in) EditText passwordEditText;
    @InjectView(R.id.progress_bar) ProgressBar progressBar;
	private ServerApi api;
    private static final int START_SIGN_UP_ACTIVITY = 123;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_in_layout);
        ButterKnife.inject(this);
        progressBar.setVisibility(View.GONE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        progressBar.setVisibility(View.VISIBLE);
        api.signIn(SignInActivity.this, email,
                password, new Response.Listener<MopitalUser>() {
                    @Override
                    public void onResponse(MopitalUser response) {
                        GUIHelperFunctions.hideProgressDialog();
                        progressBar.setVisibility(View.GONE);
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
                        progressBar.setVisibility(View.GONE);
                        GUIHelperFunctions.hideProgressDialog();
                        Toast.makeText(SignInActivity.this, "Login failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        PreferenceService.removeCredentials(SignInActivity.this.getApplicationContext());
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == START_SIGN_UP_ACTIVITY) {
            if(resultCode == RESULT_OK) {
                if(data != null && data.hasExtra(Constants.EMAIL_ADRESS)) {
                    emailEditText.setText(data.getExtras().getString(Constants.EMAIL_ADRESS));
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_sign_up) {
            Intent i = new Intent(this, SignUpActivity.class);
            startActivityForResult(i, START_SIGN_UP_ACTIVITY);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
