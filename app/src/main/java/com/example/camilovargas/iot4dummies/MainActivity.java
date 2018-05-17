package com.example.camilovargas.iot4dummies;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {

    MqttHelper mqttHelper;
    DataSensor dataSensor;
    TextView dataReceived;
    TextView txtServer;
    TextView txtUser;
    TextView txtPass;
    private String usuario, server, password;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSensor = new DataSensor(this);

        dataReceived = (TextView) findViewById(R.id.dataReceived);
        txtServer = (TextView) findViewById(R.id.edtServidor);
        txtUser = (TextView) findViewById(R.id.edtUsuario);
        txtPass = (TextView) findViewById(R.id.edtPassword);

        startMqtt();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSensor.getSensorManager().unregisterListener(dataSensor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataSensor.getSensorManager().registerListener(dataSensor,dataSensor.getSensorManager().getDefaultSensor(Sensor.TYPE_ACCELEROMETER), dataSensor.getSensorManager().SENSOR_DELAY_NORMAL);
    }

    private void startMqtt(){

        mqttHelper = new MqttHelper(getApplicationContext());

        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.d("Debug", mqttMessage.toString());
                dataReceived.setText(mqttMessage.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }

    public void publicar(View v) {
        usuario = String.valueOf(txtUser.getText());
        server = String.valueOf(txtServer.getText());
        password = String.valueOf(txtPass.getText());
        Log.d("Datos Usuario", usuario+" "+server+" "+password);
        //startMqtt(server, usuario, password);
        Log.d("Datos", dataSensor.getDatosSensor());
        mqttHelper.publicar(dataSensor.getDatosSensor());
    }


}
