package com.mopital.doctor.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
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

public class SignInActivity extends ActionBarActivity {

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
            signIn();
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
            signIn();
		}
	}

    private void signIn() {
        api.signIn(SignInActivity.this, emailEditText.getText().toString(),
                passwordEditText.getText().toString(), new Response.Listener<Result>() {
                    @Override
                    public void onResponse(Result response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return true;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
