package com.example.hma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uncc.sem1.ssdi.hma.monitoring.domain.BiologicalProfile;
import com.uncc.sem1.ssdi.hma.monitoring.domain.Gender;
import com.uncc.sem1.ssdi.hma.monitoring.domain.User;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.HMAResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Signup extends Activity {

    private Gson gson = new GsonBuilder().setDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss zzz").create();;

    String first_s, last_s, user_s, pwd_s, confirm_s, gender_s;
    double height_d, weight_d, wrist_d, waist_d, hip_d, age_d;
    EditText first, last, user, pwd, confirm;
    EditText height, weight, age, wrist, waist, hip;
    RadioGroup gender;
    RadioButton findButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Button signup = (Button) findViewById(R.id.signup2);

        signup.setOnClickListener(new View.OnClickListener()
        {
           public void onClick(View v)
            {
                first     = (EditText) findViewById(R.id.firstname);
                first_s   = first.getText().toString();
                last      = (EditText) findViewById(R.id.lastname);
                last_s    = last.getText().toString();
                user      = (EditText) findViewById(R.id.username);
                user_s    = user.getText().toString();
                pwd       = (EditText) findViewById(R.id.pwd);
                pwd_s     = pwd.getText().toString();
                confirm   = (EditText) findViewById(R.id.confirmpwd);
                confirm_s = confirm.getText().toString();
                height    = (EditText) findViewById(R.id.height);
                height_d  = Double.parseDouble(height.getText().toString());
                weight    = (EditText) findViewById(R.id.weight);
                weight_d  = Double.parseDouble(weight.getText().toString());
                age       = (EditText) findViewById(R.id.age);
                age_d     = Double.parseDouble(age.getText().toString());
                wrist     = (EditText) findViewById(R.id.wrist);
                wrist_d   = Double.parseDouble(wrist.getText().toString());
                waist     = (EditText) findViewById(R.id.waist);
                waist_d   = Double.parseDouble(waist.getText().toString());
                hip       = (EditText) findViewById(R.id.hip);
                hip_d     = Double.parseDouble(hip.getText().toString());

                gender = (RadioGroup) findViewById(R.id.gender);
                int selectId = gender.getCheckedRadioButtonId();
                findButton = (RadioButton) findViewById(selectId);
                gender_s = findButton.getText().toString();

                User user = new User();
                user.setFirstName(first_s);
                user.setLastName(last_s);
                user.setUsername(user_s);
                user.setPassword(pwd_s);
                BiologicalProfile biologicalProfile = new BiologicalProfile();
                if(gender_s.equals("Male")) {
                    biologicalProfile.setGender(Gender.MALE);
                }
                else {
                    biologicalProfile.setGender(Gender.FEMALE);
                }
                biologicalProfile.setWrist(wrist_d);
                biologicalProfile.setHip(hip_d);
                biologicalProfile.setWrist(wrist_d);
                biologicalProfile.setWaist(waist_d);
                biologicalProfile.setHeight(height_d);
                biologicalProfile.setWeight(weight_d);
             
                user.setBiologicalProfile(biologicalProfile);

                String url = "http://10.0.3.2:8080/hma-monitoring/rest/login/set";

                String userString = gson.toJson(user);
                JSONObject jsonRequest = null;
                try {
                    jsonRequest = new JSONObject(userString);
                }catch(JSONException e){
                    System.out.println(e);
                }
                GsonRequest<HMAResponse> req =
                        new GsonRequest<HMAResponse>(Request.Method.POST, url, HMAResponse.class, jsonRequest,
                                new Response.Listener<HMAResponse>() {

                                    @Override
                                    public void onResponse(HMAResponse response) {

                                        try {
                                            //txtView.setText("Response: " + response.getResponseMsg() + " :: " + response.getStatus());
                                            System.out.println(response);
                                        } catch (Throwable e) {
                                            System.out.println(e);// TODO: handle exception
                                        }
                                    }
                                }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //txtView.setText("Error: " + error.getMessage());

                            }
                        }) {
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                HashMap<String, String> headers = new HashMap<String, String>();
                                headers.put("Content-Type", "application/json; charset=utf-8");
                                // headers.put("User-agent", "My useragent");
                                return headers;
                            }
                        };

                RequestQueue queue = Volley.newRequestQueue(Signup.this);
                queue.add(req);


                Intent main_intent = new Intent(Signup.this, SIGNUP_SUCCESS.class);
                main_intent.putExtra("Name", first_s);
                startActivity(main_intent);

            }
        });
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
