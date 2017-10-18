package com.example.seniorproject;

import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.location.LocationManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;

public class GpsActivity extends AppCompatActivity implements LocationListener{

    static double  LATITUDE;
    static double LONGITUDE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        ActivityCompat.requestPermissions(GpsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                MY_PERMISSIONS_ACCESS_FINE_LOCATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_track_inspector);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, this);
        //final Location location = new Location(LocationManager.GPS_PROVIDER);

        final Button gpsButton = (Button) findViewById(R.id.setcurrentlocation);
        final EditText ed=(EditText)findViewById(R.id.editText1);
        //mMap.animateCamera(CameraUpdateFactory.zoomIn();
        gpsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                ed.setText("lat: "+Double.toString(LATITUDE)+ "lon:"+Double.toString(LONGITUDE));
            }
        });
        final Button mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(GpsActivity.this,
                        MapsActivity.class);
                startActivity(myIntent);
            }
        });
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
        //TODO Auto-generated method stub

        LATITUDE = location.getLatitude();
        LONGITUDE = location.getLongitude();

    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

}
