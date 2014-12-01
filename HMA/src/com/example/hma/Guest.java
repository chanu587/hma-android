package com.example.hma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Guest extends Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest);
        
        Button bmi = (Button) findViewById(R.id.BMI);

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bmi_intent = new Intent(Guest.this, BMI.class);
                startActivity(bmi_intent);
            }
        });
        
        Button bmr = (Button) findViewById(R.id.BMR);

        bmr.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent bmr_intent = new Intent(Guest.this, BMR.class);
                startActivity(bmr_intent);
            }
        });
        
        Button bfs = (Button) findViewById(R.id.BFS);

        bfs.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent bmr_intent = new Intent(Guest.this, BFS.class);
                startActivity(bmr_intent);
            }
        });
        
        Button bfp = (Button) findViewById(R.id.BFP);

        bfp.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent bmr_intent = new Intent(Guest.this, BFP.class);
                startActivity(bmr_intent);
            }
        });
        
        Button whr = (Button) findViewById(R.id.WHR);

        whr.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent bmr_intent = new Intent(Guest.this, WHR.class);
                startActivity(bmr_intent);
            }
        });
	}
}
