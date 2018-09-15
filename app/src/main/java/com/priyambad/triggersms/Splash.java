package com.priyambad.triggersms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.os.Handler;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;

public class Splash extends AppCompatActivity {

    private static final int time=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView iv=(ImageView)findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.launcher);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sp=getSharedPreferences("Shared",getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                boolean firstTime=sp.getBoolean("first",true);
                if(firstTime){
                    editor.putBoolean("first",false);
                    editor.apply();
                    Intent i=new Intent(Splash.this,SetPassword.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent i=new Intent(Splash.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        },time);
    }
}
