package com.example.game;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaOnline,mediaSingle,mediaDouble;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mediaOnline = MediaPlayer.create(this,R.raw.sound_btn);
        mediaSingle = MediaPlayer.create(this,R.raw.sound_btn);
        mediaDouble = MediaPlayer.create(this,R.raw.sound_btn);
            }

    public void clickOnline(View view) {
    }

    public void clickSingle(View view) {
        Animation fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade);
        mediaOnline.start();
        view.startAnimation(fadeAnimation);
        Intent intent = new Intent(MainActivity.this, Krenol.class);
        startActivity(intent);
    }

    public void clickDouble(View view) {
    }
}
