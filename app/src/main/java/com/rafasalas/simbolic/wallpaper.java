package com.rafasalas.simbolic;

/**
 * Created by salas on 01/03/2016.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.Random;

import processing.core.PVector;

public class wallpaper extends WallpaperService {
    private Context context;
    private String opcion;
    private boolean muelle, resistencia;
    private int rojo, verde, azul;
    private String tipocolor;
    //private Intent intent;





    @Override
    public Engine onCreateEngine() {

        return new wallpaperEngine();

    }



    private class wallpaperEngine extends Engine implements SensorEventListener {

        private final int frameDuration = 16;
        private SurfaceHolder holder;

        private boolean visible;
        private Handler handler;

        //Variables acelerometro
        private SensorManager mSensorManager;
        private Sensor mAccelerometer;
        private float mSensorX;
        private float mSensorY;
        private float mSensorZ;

        //private int opcion;

        private expositor expo;

       // final global dataglobal = (global) getApplicationContext();
        //finger
        private wallpaperEngine() {
             context= getApplicationContext();
            //final global dataglobal = (global) getApplicationContext();
            expo=new expositor(context);
            //Log.i("   en wallpaper","rojo "+rojo);
            //Log.i("butt", "hallegado " + opcion);

            mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            //acelerometro


            int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
            int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;



            handler = new Handler();
        }

        //funciones acelerometro
        public void registerSensors() {
            Log.d("sensor", "registerSensors()");
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        }

        public void unregisterSensors() {
            Log.d("sensor", "unregisterSensors()");
            mSensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
                return;
            mSensorX = event.values[0];
            mSensorY = event.values[1];
            mSensorZ = event.values[2];
            if (mSensorZ>10){mSensorZ=10;}
            //gravedad.set(-mSensorX,mSensorY);
            //gravedad.normalize();
            //gravedad.mult((10-mSensorZ)*70);


            //Log.d("sensor", "X: " + mSensorX + ", Y: " + mSensorY + ", Z: " + mSensorZ);
           // Log.d("Vector", "X: " + gravedad.x + ", Y: " + gravedad.y);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        //funciones acelerometro


        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {


            super.onCreate(surfaceHolder);

            this.holder = surfaceHolder;
            //cuidadin
           registerSensors();
            //cuidadin




        }
        //finger
        @Override
        public Bundle onCommand(String action, int x, int y, int z,
                                Bundle extras, boolean resultRequested) {

            if (action.equals("android.wallpaper.tap")) {

            }
            return super.onCommand(action, x, y, z, extras, resultRequested);
        }
        //finger
        private Runnable drawNucleus = new Runnable() {
            @Override
            public void run() {
                draw();

            }
        }
                ;

        private void draw() {


            if (visible) {
                Canvas canvas = holder.lockCanvas();
                canvas.save();
                int width=canvas.getWidth();
                int height=canvas.getHeight();
                //Random rnd=new Random();

                //TEMPORIZADOR DE DISPARO

               expo.Draw(canvas, width, height);
                canvas.restore();
                holder.unlockCanvasAndPost(canvas);

                handler.removeCallbacks(drawNucleus);
                handler.postDelayed(drawNucleus, frameDuration);
            }
        }

        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;

            if (visible) {
                handler.post(drawNucleus);
            } else {
                handler.removeCallbacks(drawNucleus);
            }

        }
        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            // store the center of the surface, so we can draw the cube in the right spot
            // mCenterX = width/2.0f;
            // mCenterY = height/2.0f;
            //drawFrame();
        }


        @Override
        public void onDestroy() {
            //desregistrando acelerometros...
            unregisterSensors();
            super.onDestroy();
            handler.removeCallbacks(drawNucleus);
        }





    }

}
