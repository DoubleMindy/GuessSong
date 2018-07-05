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
import android.view.Window;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements OnClickListener, OnLoadCompleteListener {
    Button btnCat;
    Button btnAut;
    Button btnEx;
    final int MAX_STREAMS = 5;

    SoundPool sp;
    int soundIdShort;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_main);
        // ориентация:
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        // SOUND POOL
        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);
        soundIdShort = sp.load(this, R.raw.long_sound, 1);

        // MEDIA PLAYER
        Intent serviceIntent = new Intent(MainActivity.this, PlayService.class);
        startService(serviceIntent);

         btnCat = (Button) findViewById(R.id.btnCat);
         btnAut = (Button) findViewById(R.id.btnAut);
         btnEx = (Button) findViewById(R.id.btnEx);

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
    @Override
    public void onPause(){
        super.onPause();
        stopService(new Intent(this, PlayService.class));
    }

    @Override
    public void onResume(){
        super.onResume();
        startService(new Intent(this, PlayService.class));
    }
}