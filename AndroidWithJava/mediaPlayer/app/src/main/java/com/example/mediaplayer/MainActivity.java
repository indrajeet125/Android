package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mPlay;
        Button stopBtn;
        Button pauseBtn;
        stopBtn = findViewById(R.id.stopbtn);
        mPlay = findViewById(R.id.playbtn);
        pauseBtn = findViewById(R.id.pausebtn);

        MediaPlayer mp = new MediaPlayer();

        mp.setAudioStreamType(AudioManager.STREAM_MUSIC    );
        String
                aPath = "android.resource://" + getPackageName() + "/raw/maanmeri";
        Uri audioUrl = Uri.parse(aPath);
        try {
            mp.setDataSource(this, audioUrl);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp.isPlaying())
                    mp.pause();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mp.pause();
                    mp.seekTo(0);


            }
        });
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()==false)
                mp.start();
            }
        });
    }
}
