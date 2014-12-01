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


public class BFP extends Activity {

    EditText height,weight,age;
    TextView bfp_Disp;
    Button calc_bfp;
    RadioGroup gender;
    RadioButton findButton;
    public String male = "Male", female = "Female";
    public int sex;
    public double pounds,inches,years,bfp, x, y, z, bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bfp);

        height   = (EditText) findViewById(R.id.height);
        weight   = (EditText) findViewById(R.id.weight);
        bfp_Disp = (TextView) findViewById(R.id.bfpDisp);
        age      = (EditText) findViewById(R.id.age);
        calc_bfp = (Button) findViewById(R.id.calc_BFP);

        gender = (RadioGroup) findViewById(R.id.gender);

        calc_bfp.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int selectID = gender.getCheckedRadioButtonId();
                findButton = (RadioButton) findViewById(selectID);
                String temp = findButton.getText().toString();

                y = Double.parseDouble(weight.getText().toString());
                x = Double.parseDouble(height.getText().toString());
                years  = Double.parseDouble(age.getText().toString());

                if(temp.equals(male))
                    sex = 1;
                else if(temp.equals(female))
                    sex = 0;

                calculateBMI();

                if(years<15)
                    bfp_Child();
                else if(years>=15)
                    bfp_Adult();
            }
        });
    }
        public void calculateBMI()
        {
            pounds = y * 0.45;
            inches = (x * 0.25) * (x * 0.25);
            z = pounds / inches;
            bmi = (double)Math.round(z*100);
        }
        public void bfp_Child()
        {
            bfp = (1.51*bmi) - (0.7*years) - (3.6*sex) + 1.4;
            bfp_Disp.setText("Your Body Fat Percentage is " + String.format("%.2f",bfp));
        }
        public void bfp_Adult()
        {
            bfp = (1.2*bmi) + (0.23*years) - (10.8*sex) - 5.4;
            bfp_Disp.setText("Your Body Fat Percentage is " + String.format("%.2f",bfp));
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
