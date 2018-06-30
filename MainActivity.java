package ru.startandroid.develop.viewbyid;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements OnClickListener, OnLoadCompleteListener {
    Button btnCat;
    Button btnAut;
    Button btnEx;
    final int MAX_STREAMS = 5;

    SoundPool sp;
    int soundIdShort;
    int soundIdLong;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_main);
        // ориентация:
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        // SOUND POOL
        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);
        soundIdShort = sp.load(this, R.raw.short_sound, 1);
        soundIdShort = sp.load(this, R.raw.long_sound, 1);

        // MEDIA PLAYER
        startService(new Intent(this, PlayService.class));
        Button btnCat = (Button) findViewById(R.id.btnCat);
        Button btnAut = (Button) findViewById(R.id.btnAut);
        Button btnEx = (Button) findViewById(R.id.btnEx);

        btnCat.setOnClickListener(this);
        btnAut.setOnClickListener(this);
        btnEx.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnCat:
                intent = new Intent(getApplicationContext(), RouteActivity_Cat.class);
                sp.play(soundIdShort, 1, 1, 0, 0, 1);
                startActivity(intent);
                break;
            case R.id.btnAut:
                intent = new Intent(getApplicationContext(), RouteActivity_Aut.class);
                sp.play(soundIdShort, 1, 1, 0, 0, 1);
                startActivity(intent);
                break;
            case R.id.btnEx:
                stopService(new Intent(this, PlayService.class));
                sp.play(soundIdShort, 1, 1, 0, 0, 1);
                finish();
        }
    }
    @Override
    public void onBackPressed() {
        stopService(new Intent(this, PlayService.class));
        finish();
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
        //
    }
}