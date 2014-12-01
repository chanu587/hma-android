package com.example.hma;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class TrackMap extends ActionBarActivity {
	
	GoogleMap gMap;
	// LocationManager locManager;
	// Location loc;
	LatLng prevLoc = null;
	LatLng currLoc = null;
	boolean startTracking = false;
	float[] dist = new float[1];
	PolylineOptions line;
	
	LocationListener locListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			currLoc = new LatLng(location.getLatitude(),
					location.getLongitude());

			if (prevLoc == null) {
				prevLoc = currLoc;
				gMap.addMarker(new MarkerOptions().position(prevLoc)
						.title("Start Location"));
			}
				
				gMap.addPolyline(new PolylineOptions().add(prevLoc, currLoc)
						.width(5).color(Color.BLUE));
				
				/*
				 * Polyline line = gMap.addPolyline(new PolylineOptions()
				 * .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
				 * .width(5) .color(Color.RED));
				 */
				
					/*	line.add(prevLoc, currLoc).width(5).color(Color.RED);
				gMap.addPolyline(line);*/
				gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currLoc,
						13));
				prevLoc = currLoc;

			}

		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trackmap);
		//final boolean startTracking = false;
		final LocationManager locationMngr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		line = new PolylineOptions();

		Location location = locationMngr
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		/*gMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();*/
		gMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		gMap.setMyLocationEnabled(true);

		gMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

			@Override
			public void onMapLongClick(LatLng arg0) {
				if (!startTracking) {
					startTracking = true;
					//currLoc = new LatLng(arg0.latitude, arg0.longitude);
					//currLoc = new LatLng(35.2270869, -80.8431267);
					//gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currLoc, 13));
					Toast.makeText(TrackMap.this,"Start Location Tracking", Toast.LENGTH_LONG).show();
					//gMap.addMarker(new MarkerOptions().position(currLoc).title("Start Location"));
					locationMngr.requestLocationUpdates(
							LocationManager.GPS_PROVIDER, 0,
							100, locListener);
				}
				else{
					startTracking = false;
					Toast.makeText(TrackMap.this,"Stop Location Tracking", Toast.LENGTH_LONG).show();
					locationMngr.removeUpdates(locListener);
					gMap.addMarker(new MarkerOptions().position(currLoc)
							.title("End Location"));
					
					Location.distanceBetween(prevLoc.latitude, prevLoc.longitude, currLoc.latitude, currLoc.longitude, dist );
					Toast.makeText(TrackMap.this, "Distance Walked "+dist[0], Toast.LENGTH_LONG).show();
					prevLoc = null;
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
