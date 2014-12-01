package com.example.hma;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.TextView;


public class BMI extends Activity 
{

    TextView bmiView, guide;
    Button calc_bmi;
    EditText height, weight;
    double x,y,z, pounds, inches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);

        calc_bmi = (Button) findViewById(R.id.calcbmi);
        height   = (EditText) findViewById(R.id.height);
        weight   = (EditText) findViewById(R.id.weight);
        bmiView  = (TextView)findViewById(R.id.bmiView);
        guide    = (TextView)findViewById(R.id.guide);

        guide.setText("Less than 19 = Underweight \n19-25 = Normal \nAbove 25 = Overweight");
        calc_bmi.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                calculate_bmi();
            }
            public void calculate_bmi()
            {
                x=Double.parseDouble(height.getText().toString());
                y=Double.parseDouble(weight.getText().toString());
                pounds = y * 0.45;
                inches = (x * 0.025)*(x * 0.025);
                z = pounds / inches;
                if(z<18.5)
                {
                    bmiView.setTextColor(Color.parseColor("#ff0000"));
                }
                if(z>18.5 && z<25)
                {
                    bmiView.setTextColor(Color.parseColor("#00ff00"));
                }
                if(z>25)
                {
                    bmiView.setTextColor(Color.parseColor("#ff0000"));
                }

                bmiView.setText("Your BMI is " + String.format("%.2f",z));

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