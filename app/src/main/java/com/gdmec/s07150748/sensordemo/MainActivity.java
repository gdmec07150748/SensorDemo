package com.gdmec.s07150748.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mOrientation;
    private Sensor mLight;
    private TextView tAcceletermeter;
    private TextView tOtientation;
    private TextView tLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tAcceletermeter = (TextView) this.findViewById(R.id.accelerometer);
        tOtientation = (TextView) this.findViewById(R.id.orienttation);
        tLight = (TextView) this.findViewById(R.id.light);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }
    protected  void onResume(){
        super.onResume();
        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mOrientation,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mLight,SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[SensorManager.DATA_X];
        float y = event.values[SensorManager.DATA_Y];
        float z = event.values[SensorManager.DATA_Z];
        if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){
            tOtientation.setText("方位"+x+","+y+","+z);
        }else if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            tAcceletermeter.setText("加速度"+x+","+y+","+z);
        }else if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            tLight.setText("光线"+x+","+y+","+z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
