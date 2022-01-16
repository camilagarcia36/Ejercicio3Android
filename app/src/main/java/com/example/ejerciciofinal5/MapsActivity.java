package com.example.ejerciciofinal5;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    static MapsActivity instance;
    TextView TVFecha;
    TextView TVtitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        instance = this;
        TVFecha = findViewById(R.id.mapTV);
        TVtitulo = findViewById(R.id.mapTTV);


    }

    public static MapsActivity getInstance() {
        return instance;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        activarLocalizacion();
    }

    public void openMA(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 0);
    }

    public void activarLocalizacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permisos = {Manifest.permission.ACCESS_FINE_LOCATION}; //arraystrings
            ActivityCompat.requestPermissions(this, permisos, 123);
            return;
        }
        map.setMyLocationEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 123 && permissions[0]/*primer permiso*/.equals(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            activarLocalizacion();
        }
    }
}