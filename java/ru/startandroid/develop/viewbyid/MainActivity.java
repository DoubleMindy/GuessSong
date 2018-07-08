package ru.startandroid.develop.viewbyid;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnClickListener, OnLoadCompleteListener {
    Button btnCat;
    Button btnAut;
    Button btnEx;
    Intent intent;
    TextView coins;
    SharedPreferences sPref;
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

        // BUTTONS
        coins = (TextView) findViewById(R.id.coins);
        btnCat = (Button) findViewById(R.id.btnCat);
        btnAut = (Button) findViewById(R.id.btnAut);
        btnEx = (Button) findViewById(R.id.btnEx);

        //BUTTONS SET
        btnCat.setOnClickListener(this);
        btnAut.setOnClickListener(this);
        btnEx.setOnClickListener(this);
        loadText();

        if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish();
        }
        if(getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            coins.setText(extras.getString("COINS_AFTER"));
        }
        saveText();
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnCat:
                intent = new Intent(getApplicationContext(), RouteActivity_Cat.class);
                sp.play(soundIdShort, 1, 1, 0, 0, 1);
                Bundle bundle = new Bundle();
                bundle.putString("COINS", coins.getText().toString());
                bundle.putString("fromMain", "main");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btnAut:
                intent = new Intent(getApplicationContext(), RouteActivity_Aut.class);
                sp.play(soundIdShort, 1, 1, 0, 0, 1);
                Bundle bundle1 = new Bundle();
                bundle1.putString("COINS", coins.getText().toString());
                bundle1.putString("fromMain", "main");
                intent.putExtras(bundle1);
                startActivity(intent);
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
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            ad.setMessage("Вы точно хотите выйти?");
            ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    finish();
                }
            });
            ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = ad.create();
            alert.show();
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, PlayService.class));
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


    public void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("saved_coin", coins.getText().toString());
        ed.commit();
    }

    public void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString("saved_coin", coins.getText().toString());
        coins.setText(savedText);
    }
}