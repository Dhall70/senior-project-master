package com.example.seniorproject;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.widget.Button;
import android.view.View;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    boolean isMarker = true;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        final Button mapButton = (Button) findViewById(R.id.deleteMarker);
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                marker.remove();
                isMarker = false;
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        LatLng curLoc = new LatLng(GpsActivity.LATITUDE, GpsActivity.LONGITUDE);

        marker = mMap.addMarker(new MarkerOptions().position(curLoc).title("Marker in curLoc"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(curLoc));


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {

            @Override
            public void onMapClick(LatLng latlng)
            {
                if(!isMarker)
                {
                    if (marker != null) {
                        marker.remove();
                    }
                    marker = mMap.addMarker(new MarkerOptions()
                            .position(latlng)
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                    isMarker = true;

                }
            }
        });
    }
}
