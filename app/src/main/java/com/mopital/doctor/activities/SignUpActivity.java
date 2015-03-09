package com.mopital.doctor.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mopital.doctor.R;
import com.mopital.doctor.core.DefaultServerApi;
import com.mopital.doctor.core.ServerApiProvider;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.models.Patient;

public class SignUpActivity extends ActionBarActivity {

	private EditText nameEditText;
	private EditText emailEditText;
	private EditText password1EditText;
	private EditText password2EditText;
	private DefaultServerApi api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_layout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		nameEditText = (EditText) findViewById(R.id.name_edittext);
		emailEditText = (EditText) findViewById(R.id.email_edittext);
		password1EditText = (EditText) findViewById(R.id.password_1);
		password2EditText = (EditText) findViewById(R.id.password_2);
		api = (DefaultServerApi) ServerApiProvider.serverApi();
	}

	public void signUpClicked(View v) {

		if (nameEditText.getText().toString().isEmpty()) {
			Toast.makeText(SignUpActivity.this,
					getResources().getString(R.string.username_empty),
					Toast.LENGTH_SHORT).show();
		} else if (emailEditText.getText().toString().isEmpty()) {
			Toast.makeText(SignUpActivity.this,
					getResources().getString(R.string.email_empty),
					Toast.LENGTH_SHORT).show();
		} else if (password1EditText.getText().toString().isEmpty()) {
			Toast.makeText(SignUpActivity.this,
					getResources().getString(R.string.password_empty),
					Toast.LENGTH_SHORT).show();
		} else if (password2EditText.getText().toString().isEmpty()) {
			Toast.makeText(SignUpActivity.this,
					getResources().getString(R.string.password_empty),
					Toast.LENGTH_SHORT).show();
		} else if (!password1EditText.getText().toString()
				.equals(password2EditText.getText().toString())) {
			Toast.makeText(SignUpActivity.this,
					getResources().getString(R.string.password_not_match),
					Toast.LENGTH_SHORT).show();
		} else {
            signUp();
		}

	}

    private void signUp() {
        api.signUp(SignUpActivity.this, nameEditText
                        .getText().toString(), emailEditText.getText().toString(),
            password1EditText.getText().toString(),  new Response.Listener<Result>() {
                @Override
                public void onResponse(Result response) {
                    setResult(RESULT_OK);
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
        );
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			setResult(RESULT_CANCELED);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
