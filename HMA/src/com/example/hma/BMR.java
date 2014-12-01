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


public class BMR extends Activity {

    EditText height,weight,age;
    TextView bmr_Disp;
    Button calc_bmr;
    RadioGroup gender;
    RadioButton findButton;
    public String male = "Male", female = "Female";
    public double pounds,inches,years,bmr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmr);

        height   = (EditText) findViewById(R.id.height);
        weight   = (EditText) findViewById(R.id.weight);
        bmr_Disp = (TextView) findViewById(R.id.bmrDisp);
        age      = (EditText) findViewById(R.id.age);
        calc_bmr = (Button) findViewById(R.id.calc_BMR);

        gender = (RadioGroup) findViewById(R.id.gender);

        calc_bmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
                {

                int selectID = gender.getCheckedRadioButtonId();
                findButton = (RadioButton) findViewById(selectID);
                String temp = findButton.getText().toString();

                pounds = Double.parseDouble(weight.getText().toString());
                inches = Double.parseDouble(height.getText().toString());
                years  = Double.parseDouble(age.getText().toString());

                if (temp.equals(male))
                {
                bmr = 655 + (4.35*pounds) + (4.7*inches) + (4.7*years);
                }
                else if (temp.equals(female))
                {
                bmr = 66 + (6.23*pounds) + (12.7*inches) + (6.8*years);
                }
                bmr_Disp.setText("Your BMR is " +String.format("%.2f",bmr));
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

