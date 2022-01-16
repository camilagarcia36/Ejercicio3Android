package com.example.ejerciciofinal5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.example.ejerciciofinal5.R.id.mapTV;
import static com.example.ejerciciofinal5.R.id.start;

public class FinalActivity extends AppCompatActivity {

    EditText titulo;
    EditText lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Lugar y titulo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        titulo = findViewById(R.id.ed1);
        lugar = findViewById(R.id.ed2);
    }

    public void finalizar(View view) {
        if (titulo.getText().toString().equals("") || lugar.getText().toString().equals("")) {
            Toast.makeText(this, "Añade titulo y lugar del evento", Toast.LENGTH_SHORT).show();
        } else {
            LatLng latLng = geocode(lugar.getText().toString());
            Marker marcador = MapsActivity.getInstance().map.addMarker(new MarkerOptions() //recibe unas opciones de marcador, y devuelve el marcador dentro del mapa
                    .position(latLng)
                    .title(titulo.getText().toString())
                    .snippet(MapsActivity.getInstance().TVFecha.getText().toString())
                    .alpha(0.7f)
            );
            MapsActivity.getInstance().map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            MapsActivity.getInstance().TVtitulo.setText(titulo.getText().toString());
            Intent intent = new Intent(this, MapsActivity.class);
            this.finish();
        }
    }

    public LatLng geocode(String lugar) {
        Geocoder geocoder = new Geocoder(this); //sacar resultados a partir de un string
        try {
            List<Address> direcciones = geocoder.getFromLocationName(lugar, 1);
            //devuelve una direccion
            if (direcciones.size() != 0) {
                Address direccion1 = direcciones.get(0); //guardamos la direccion aqui, para utilizarla despues
                LatLng sitio = new LatLng(direccion1.getLatitude(), direccion1.getLongitude());
                return sitio;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new LatLng(0, 0);
    }
}

//Cuando creo un evento, no se me borra el marcador. A no ser que reinicie la app.


