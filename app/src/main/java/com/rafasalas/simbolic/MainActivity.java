package com.rafasalas.simbolic;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;


public class MainActivity extends Activity {
    private Button vamos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        addlistenerOnButton();
    }

    public void addlistenerOnButton(){

        vamos = (Button) findViewById(R.id.Vamos);

        vamos.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                WallpaperManager wallcachas=WallpaperManager.getInstance(getApplicationContext());
                try{



                    Intent intent = new Intent(wallcachas.ACTION_CHANGE_LIVE_WALLPAPER);

                    intent.putExtra(wallcachas.EXTRA_LIVE_WALLPAPER_COMPONENT,
                            new ComponentName(MainActivity.this, wallpaper.class));
                    //stopService(intent);
                    startActivity(intent);
                    finish();
                }
                catch(Exception e){

                    Intent intent = new Intent();
                    intent.setAction(wallcachas.ACTION_LIVE_WALLPAPER_CHOOSER);
                    startActivity(intent);
                    finish();
                }



            }

        });
    }




}
