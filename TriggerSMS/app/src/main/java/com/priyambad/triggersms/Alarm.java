package com.priyambad.triggersms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.media.AudioManager;
import android.widget.TextView;
import android.content.Intent;
import android.widget.ImageView;

public class Alarm extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        AudioManager am=(AudioManager)getSystemService(this.AUDIO_SERVICE);
        am.setStreamVolume(AudioManager.STREAM_MUSIC,am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
        mp=MediaPlayer.create(Alarm.this,R.raw.danger);
        mp.start();
        ImageView iv=(ImageView)findViewById(R.id.imageView3);
        iv.setImageResource(R.drawable.speaker);
    }
    public void stop(View view){
        mp.stop();
        finish();
    }
}
