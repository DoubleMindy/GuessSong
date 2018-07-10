package ru.startandroid.develop.viewbyid;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RouteActivity_Aut extends AppCompatActivity implements SoundPool.OnLoadCompleteListener, View.OnClickListener {

    SoundPool sp;
    int soundIdShort;
    Button btn1;
    String FROM = "aut";
    String p = "0";
    String tr = "3";
    String coins;
    String from;
    String level;
    Boolean isFreeHint = false;
    int row = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_act);
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        // SOUND POOL
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);
        soundIdShort = sp.load(this, R.raw.long_sound, 1);

        Bundle extras = getIntent().getExtras();
        coins = extras.getString("COINS");
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn1:
                level = "1";
                intent = new Intent(getApplicationContext(), ActionActivity.class);
                sp.play(soundIdShort, 1, 1, 0, 0, 1);
                intent.putExtra("FromWhat", FROM);
                intent.putExtra("PROGRESS", p);
                intent.putExtra("TRIES", tr);
                intent.putExtra("COINS", coins);
                intent.putExtra("LEVEL", level);
                intent.putExtra("ROW", row);
                intent.putExtra("FREEHINT", isFreeHint);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                break;
        }
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
        //
    }
}