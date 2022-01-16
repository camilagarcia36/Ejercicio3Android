package com.example.ejerciciofinal5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TimePicker horaTP;
    DatePicker fechaDP;
    TextView mapsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Fecha");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapsTV = findViewById(R.id.mapTV);
        horaTP = findViewById(R.id.horaTP);
        fechaDP = findViewById(R.id.fechaTP);

        obtenerinfo();
    }

    public void obtenerinfo() {
        horaTP.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            String datos = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                datos = fechaDP.getDayOfMonth() + "-"
                        + fechaDP.getMonth() + "-"
                        + fechaDP.getYear() + "//"
                        + horaTP.getHour() + ":"
                        + horaTP.getMinute();
            }
            MapsActivity.getInstance().TVFecha.setText(datos);
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            fechaDP.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
                String datos = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    datos = fechaDP.getDayOfMonth() + "-"
                            + fechaDP.getMonth() + "-"
                            + fechaDP.getYear() + "//"
                            + horaTP.getHour() + ":"
                            + horaTP.getMinute();
                }
                MapsActivity.getInstance().TVFecha.setText(datos);
                Toast.makeText(this, "Fecha indicada " + datos, Toast.LENGTH_LONG).show();
            });
        }
    }

    public void openFA (View view) {
        Intent intent= new Intent(this, FinalActivity.class);
        startActivity(intent);
        this.finish();
    }

}