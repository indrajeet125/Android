package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            Sensor accleraotSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (accleraotSensor != null) {
                sensorManager.registerListener(this, accleraotSensor, sensorManager.SENSOR_DELAY_NORMAL);
            }
        } else {
            Toast.makeText(this, "sensor service does  not exist ", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            ((TextView) findViewById(R.id.txtvalue)).setText(
                    "x " + event.values[0] +
                            "\ny: " + event.values[1] +
                            "\nZ: " + event.values[2]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}