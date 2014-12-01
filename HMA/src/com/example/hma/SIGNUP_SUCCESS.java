package com.example.hma;

	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.view.Menu;
	import android.view.MenuItem;
	import android.view.View;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.TextView;


	public class SIGNUP_SUCCESS extends Activity {

	    Button call_login;
	    @Override
	    protected void onCreate(Bundle savedInstanceState)
	    {

	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.signup_success);

	        call_login = (Button) findViewById(R.id.call_login);
	        call_login.setOnClickListener(new View.OnClickListener()
	        {
	            public void onClick(View v)
	            {
	                Intent signin_intent = new Intent(SIGNUP_SUCCESS.this, Main.class);
	                startActivity(signin_intent);
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


