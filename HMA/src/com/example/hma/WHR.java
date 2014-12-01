package com.example.hma;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class WHR extends Activity {

    TextView whrView, guide;
    Button calc_whr;
    EditText waist_in, hip_in;
    double waist,hip, whr;
    RadioGroup gender;
    RadioButton findButton;
    public String male = "Male", female = "Female";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whr);

        waist_in = (EditText) findViewById(R.id.waist);
        hip_in   = (EditText) findViewById(R.id.hip);
        calc_whr = (Button) findViewById(R.id.calc_WHR);
        whrView  = (TextView)findViewById(R.id.whrDisp);
        guide    = (TextView)findViewById(R.id.guide);
        gender = (RadioGroup) findViewById(R.id.gender);

        calc_whr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                waist = Double.parseDouble(waist_in.getText().toString());
                hip   = Double.parseDouble(hip_in.getText().toString());

                int selectID = gender.getCheckedRadioButtonId();
                findButton = (RadioButton) findViewById(selectID);
                String temp = findButton.getText().toString();

                calculate_whr();
                whrView.setText("Your waist to hip ratio is " + String.format("%.2f",whr));

                if(temp.equals(male))
                guide.setText("Health Risk \n0.95 or below = Low Risk \n0.96 to 1.0 = Moderate Risk \nAbove 1.0 = High risk");

                else if(temp.equals(female))
                guide.setText("Health Risk \n0.80 or below = Low Risk \n0.81 to 0.85 = Moderate Risk \nAbove 0.85 = High risk");

            }

            public void calculate_whr()
            {
                whr   = waist / hip;
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

