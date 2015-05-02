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
import com.mopital.doctor.core.DefaultServerApi;
import com.mopital.doctor.core.ServerApiProvider;
import com.mopital.doctor.core.volley.responses.Result;
import com.mopital.doctor.core.volley.responses.VolleyFailWrapper;

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
                        .getText().toString(), "doctor", emailEditText.getText().toString(),
            password1EditText.getText().toString(),  new Response.Listener<Result>() {
                @Override
                public void onResponse(Result response) {
                    setResult(RESULT_OK);
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    String errorMessage = "Couldn't sign up, try again";
                    if(error instanceof VolleyFailWrapper) {
                        errorMessage = ((VolleyFailWrapper)error).getResult().getMsg();
                    }
                    Toast.makeText(SignUpActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
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
