package com.example.fakefblivestream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CallCommingActivity extends AppCompatActivity {
    private Vibrator vib;
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_call_comming);

        ImageView cambt1 = findViewById(R.id.cambtn);
        ImageView cancle = findViewById(R.id.canclebtn);

        mp = MediaPlayer.create(this, R.raw.fbmsngertone);
        mp.setLooping(true);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(1000);
        mp.start();

        cambt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CallCommingActivity.this,VideoCallActivity.class);
                startActivity(intent);
                mp.stop();
                vib.cancel();
                finish();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CallCommingActivity.this,Main2Activity.class);
                startActivity(intent);
                mp.stop();
                vib.cancel();
                finish();
            }
        });

    }
}
