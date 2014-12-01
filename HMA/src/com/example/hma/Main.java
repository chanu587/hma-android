package com.example.hma;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uncc.sem1.ssdi.hma.android.json.GsonDateDeSerializer;
import com.uncc.sem1.ssdi.hma.monitoring.domain.User;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.UserResponse;

public class Main extends Activity {

	private Gson gson; // = new
	// GsonBuilder().setDateFormat("yyyy'-'MM'-'dd").create();;
	{
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new GsonDateDeSerializer());
		gson = builder.create();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button met = (Button) findViewById(R.id.guest);

		met.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent metric_intent = new Intent(Main.this, Guest.class);
				startActivity(metric_intent);
			}
		});

		Button signup = (Button) findViewById(R.id.signup);

		signup.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent signup_intent = new Intent(Main.this, Signup.class);
				startActivity(signup_intent);
			}
		});

		Button signin;

		signin = (Button) findViewById(R.id.signin);
		signin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText uname = (EditText) findViewById(R.id.uname);
				String uname_s = uname.getText().toString();
				String url = "http://10.0.3.2:8080/hma-monitoring/rest/login/"
						+ uname_s;

				GsonRequest<UserResponse> req = new GsonRequest<UserResponse>(
						Request.Method.GET, url, UserResponse.class, null,
						new Response.Listener<UserResponse>() {

							@Override
							public void onResponse(UserResponse response) {

								try {
									EditText uname = (EditText) findViewById(R.id.uname);
									String uname_s = uname.getText().toString();
									EditText pwd = (EditText) findViewById(R.id.pwd);
									String pwd_s = pwd.getText().toString();

									User user_check = response.getUser();
									String pwd_check = user_check.getPassword();
									String uname_check = user_check
											.getUsername();
									// txtView.setText("Response: " +
									// response.getResponseMsg() + " :: " +
									// response.getStatus());
									System.out.println(response);

									if (pwd_check.equals(pwd_s)) {
										Intent signin_intent = new Intent(
												Main.this, Signin_Success.class);
										startActivity(signin_intent);
									} else {
										Toast.makeText(getApplicationContext(),
												"Wrng Pwd "+pwd_s + pwd_check + uname_s,
												Toast.LENGTH_LONG).show();
									}
								} catch (Throwable e) {
									System.out.println(e);// TODO: handle
															// exception
								}
							}
						}, new Response.ErrorListener() {

							@Override
							public void onErrorResponse(VolleyError error) {
								Toast.makeText(getApplicationContext(),
										"Wrong Password" + error.getMessage(),
										Toast.LENGTH_LONG).show();
								// txtView.setText("Error: " +
								// error.getMessage());

							}
						}) {
					@Override
					public Map<String, String> getHeaders()
							throws AuthFailureError {
						HashMap<String, String> headers = new HashMap<String, String>();
						headers.put("Content-Type",
								"application/json; charset=utf-8");
						// headers.put("User-agent", "My useragent");
						return headers;
					}

				};

				req.setmGson(gson);
				NetworkRequestHelper.getInstance(Main.this.getApplicationContext()).addToRequestQueue(req);
				Intent signin_intent = new Intent(Main.this,
						Signin_Success.class);
				startActivity(signin_intent);

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