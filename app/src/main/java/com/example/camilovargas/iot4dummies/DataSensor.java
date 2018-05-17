package com.example.camilovargas.iot4dummies;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class DataSensor implements SensorEventListener {

    private SensorManager sensorManager = null;
    private Sensor sensorDeTemperatura = null;
    private Sensor sensorDeProximidad = null;
    private Sensor sensorDeLuz = null;
    private Sensor sensorAcelerometro = null;
    private Sensor sensorDeOrientacion = null;
    private final Context context;

    private String DatosSensor;

    public DataSensor(Context context){
        this.context=context;
        sensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        sensorDeProximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorDeTemperatura = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        sensorDeLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorDeOrientacion = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        DatosSensor="Datos: ";
    }

    public String getDatosSensor() {
        return DatosSensor;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        synchronized (this){
            float x;
            float y;
            float z;
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];
            DatosSensor="x: " + x + " y: "+y + " z: "+z;
           }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    public SensorManager getSensorManager() {
        return sensorManager;
    }
}
