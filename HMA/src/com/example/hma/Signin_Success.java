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

        Button metrics = (Button) findViewById(R.id.metrics);
        
        metrics.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Bundle bundle = getIntent().getExtras();
			Intent metrics_intent = new Intent(Signin_Success.this, Metrics.class);
			
			if(bundle!=null)
			{
			metrics_intent.putExtra("height", bundle.getDouble("height"));
			metrics_intent.putExtra("weight", bundle.getDouble("weight"));
			metrics_intent.putExtra("age", bundle.getDouble("age"));
			metrics_intent.putExtra("hip", bundle.getDouble("hip"));
			metrics_intent.putExtra("neck", bundle.getDouble("neck"));
			metrics_intent.putExtra("waist", bundle.getDouble("waist"));
			metrics_intent.putExtra("wrist", bundle.getDouble("wrist"));
			}
			startActivity(metrics_intent);
			};
        });
        
	}
}
        
        
        