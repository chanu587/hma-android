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


public class BFS extends Activity {

    EditText height, wrist;
    TextView bfs_disp;
    Button calc_bfs;
    RadioGroup gender;
    RadioButton findButton;
    public String male = "Male", female = "Female";
    public double inches1, inches2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bfs);

        height = (EditText) findViewById(R.id.height);
        wrist = (EditText) findViewById(R.id.wrist);
        bfs_disp = (TextView) findViewById(R.id.bfsDisp);
        calc_bfs = (Button) findViewById(R.id.calc_BFS);

        gender = (RadioGroup) findViewById(R.id.gender);

        calc_bfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectID = gender.getCheckedRadioButtonId();
                findButton = (RadioButton) findViewById(selectID);
                String temp = findButton.getText().toString();

                inches1 = Double.parseDouble(height.getText().toString());
                inches2 = Double.parseDouble(wrist.getText().toString());

                if (temp.equals(female)) {
                    if ((inches1 < 62 && inches2 < 5.5) || ((inches1 >= 62 && inches1 <= 65) && inches2 < 6) || (inches1 > 65 && inches2 < 6.25)) {
                        displaySmall();
                    } else if ((inches1 < 62 && (inches2 >= 5.5 && inches2 <= 5.75)) || ((inches1 >= 62 && inches1 <= 65) && (inches2 >= 6 && inches2 <= 6.25)) || (inches1 > 65 && (inches2 >= 6.25 && inches2 <= 6.5))) {
                        displayMedium();
                    } else if ((inches1 < 62 && inches2 > 5.75) || ((inches1 >= 62 && inches1 <= 65) && inches2 > 6.25) || (inches1 > 65 && inches2 > 6.5)) {
                        displayLarge();
                    }
                } else if (temp.equals(male)) {
                    if (inches1 > 65) {
                        if (inches2 >= 5.5 && inches2 <= 6.5)
                            displaySmall();
                        else if (inches2 > 6.5 && inches2 <= 7.5)
                            displayMedium();
                        else if (inches2 > 7.5)
                            displayLarge();
                    } else
                        displaySmall();
                }
            }
        });
    }

        public void displaySmall()
        {
            bfs_disp.setText("Your Body Frame: Small");
        }

        public void displayMedium()
        {
            bfs_disp.setText("Your Body Frame: Medium");
        }

        public void displayLarge()
        {
            bfs_disp.setText("Your Body Frame: Large");
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

