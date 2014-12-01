package com.example.hma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Signin_Success extends Activity
{

	protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signinsuccess); 
        
        Button hosp = (Button) findViewById(R.id.hosp);
        
        hosp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent hosp_intent = new Intent(Signin_Success.this, Hospitals.class);
				startActivity(hosp_intent);
				
			}
		});
         
        Button record = (Button) findViewById(R.id.record);
        
        record.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent record_intent = new Intent(Signin_Success.this, Record.class);
				startActivity(record_intent);
			}
		});
        
        Button target = (Button) findViewById(R.id.target);
        
        target.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent record_intent = new Intent(Signin_Success.this, Target.class);
				startActivity(record_intent);
			}
		});
        
        Button track = (Button) findViewById(R.id.track);
        
        track.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent track_intent = new Intent(Signin_Success.this, TrackMap.class);
				startActivity(track_intent);
			};
		});
        
        
        Button footsteps = (Button) findViewById(R.id.foot);
        
        footsteps.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent track_intent = new Intent(Signin_Success.this, MainActivity.class);
				startActivity(track_intent);
			};
		});
        
        
        
        
	}
}