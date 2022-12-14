package com.course.msp.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.course.msp.R;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActicity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.position);

        EditText editText = (EditText) findViewById(R.id.inputPosition);
        String position = editText.getText().toString();
        Log.d("Main", position);

        Button btn = (Button) findViewById(R.id.checkPosition);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.inputPosition);
                String position = editText.getText().toString();
                Log.d("main", position);
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // ????????? ???????????? ??????????????? ???????????? ????????????
        LatLng dongguk = new LatLng(37.55827, 126.998425);
        // ????????? ?????? ?????? ??????
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(dongguk);
        markerOptions.title("???????????????");
        markerOptions.snippet("?????? ?????? ???");
        mMap.addMarker(markerOptions);
        // ??? ?????? ?????????
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        // ?????? ????????? ??????
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dongguk));
        //??? ?????? ??????
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}
