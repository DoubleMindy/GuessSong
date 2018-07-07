package ru.startandroid.develop.viewbyid;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.database.Cursor;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ActionActivity extends AppCompatActivity implements View.OnClickListener {
    Cursor c = null;
    ImageButton PhotoHint;
    ImageButton FiftyHint;
    ImageButton RefreshHint;
    ImageView PhotoPic;
    TextView progress;
    TextView tried;
    MediaPlayer mediaPlayer;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Boolean isHint = false;
    int answer;
    Intent intent;
    String p;
    String tr;
    int CORRECT_ANSWER;
    String datatag;
    int pw;
    int trw;
    Boolean isOpen = false;
    Boolean butClick = false;
    int random_defeat1;
    int random_defeat2;
    String FromWhat;
    int idColIndex;
    int idSong;
    String ART;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // INITIALIZE
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action);
        progress = (TextView) findViewById(R.id.Progress);
        tried = (TextView) findViewById(R.id.Tried);
        PhotoHint = (ImageButton) findViewById(R.id.PhotoHint);
        FiftyHint = (ImageButton) findViewById(R.id.fiftyHint);
        RefreshHint = (ImageButton) findViewById(R.id.refreshHint);
        PhotoPic = (ImageView) findViewById(R.id.Photo);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        // DATABASE
        datatag = "loboda";
        DatabaseHelper DbHelper = new DatabaseHelper(ActionActivity.this);
        try {
            DbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        DbHelper.openDataBase();

        // BUTTONS
        FiftyHint.setOnClickListener(this);
        PhotoHint.setOnClickListener(this);
        RefreshHint.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        // ANSWERS
// ...
        c = DbHelper.query("ARTISTS",null, "cat = ?", new String[] {"1"}, null, null, null);
        int DBLENGTH = c.getCount();
        answer = (int) (Math.random() * 4) + 1;

        int r1 = (int) (Math.random() * DBLENGTH);
        c.moveToPosition(r1);
        if (answer == 1){
            int idHint = c.getColumnIndex("link");
            datatag = c.getString(idHint);
        }
        FromWhat = getIntent().getStringExtra("FromWhat");
        if (FromWhat.equals("aut")) {
            idColIndex = c.getColumnIndex("name");
            ART = c.getString(idColIndex);
        }
            else{
                idColIndex = c.getColumnIndex("name");
                idSong = c.getColumnIndex("song");
            ART = c.getString(idColIndex) + " - " + c.getString(idSong);
            }
        button1.setText(ART);


        int r2 = (int) (Math.random() * DBLENGTH);
        while (r1 == r2) {
            r2 = (int) (Math.random() * DBLENGTH);
        }
        c.moveToPosition(r2);
        if (answer == 2){
            int idHint = c.getColumnIndex("link");
            datatag = c.getString(idHint);
        }
        if (FromWhat.equals("aut")) {
            idColIndex = c.getColumnIndex("name");
            ART = c.getString(idColIndex);
        }
        else{
            idColIndex = c.getColumnIndex("name");
            idSong = c.getColumnIndex("song");
            ART = c.getString(idColIndex) + " - " + c.getString(idSong);

        }
        button2.setText(ART);

        int r3 = (int) (Math.random() * DBLENGTH);
        while ((r3 == r2) || (r3 == r1)) {
            r3 = (int) (Math.random() * DBLENGTH);
        }
        c.moveToPosition(r3);
        if (answer == 3){
            int idHint = c.getColumnIndex("link");
            datatag = c.getString(idHint);
        }
        if (FromWhat.equals("aut")) {
            idColIndex = c.getColumnIndex("name");
            ART = c.getString(idColIndex);
        }
        else{
            idColIndex = c.getColumnIndex("name");
            idSong = c.getColumnIndex("song");
            ART = c.getString(idColIndex) + " - " + c.getString(idSong);

        }
        button3.setText(ART);

        int r4 = (int) (Math.random() * DBLENGTH);
        while ((r4 == r3) || (r4 == r2) || (r4 == r1)) {
            r4 = (int) (Math.random() * DBLENGTH);
        }
        c.moveToPosition(r4);
        if (answer == 4){
            int idHint = c.getColumnIndex("link");
            datatag = c.getString(idHint);
        }
        if (FromWhat.equals("aut")) {
            idColIndex = c.getColumnIndex("name");
            ART = c.getString(idColIndex);
        }
        else {
            idColIndex = c.getColumnIndex("name");
            idSong = c.getColumnIndex("song");
            ART = c.getString(idColIndex) + " - " + c.getString(idSong);
        }
        button4.setText(ART);
//...
        random_defeat1 = (int) (Math.random() * 4) + 1;
        while (random_defeat1 == answer){
            random_defeat1 = (int) (Math.random() * 4) + 1;
        }
        random_defeat2 = (int) (Math.random() * 4) + 1;
        while (random_defeat2 == answer || random_defeat2 == random_defeat1){
            random_defeat2 = (int) (Math.random() * 4) + 1;
        }


        // MEDIA PLAYER
        int resID=getResources().getIdentifier(datatag, "raw", ActionActivity.this.getPackageName());
        mediaPlayer=MediaPlayer.create(this, resID);
        mediaPlayer.start();

        // EXTRAS
        if(getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            progress.setText(extras.getString("PROGRESS"));
            tried.setText(extras.getString("TRIES"));
        }
        c.close();
        DbHelper.close();

    }


    @Override
    public void onClick(View v) {
        CORRECT_ANSWER = 0;
        switch (v.getId()) {
            case R.id.PhotoHint:
                isHint = true;
                    AlertDialog.Builder bu = new AlertDialog.Builder(ActionActivity.this);
                    bu.setTitle("Открыть фото исполнителя");
                    bu.setMessage("Вы точно хотите использовать подсказку? (100 монет)");
                    bu.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            String PathName = "@drawable/" + datatag;
                            int imageResource = getResources().getIdentifier(PathName, null, getPackageName());
                            Drawable res = getResources().getDrawable(imageResource);
                            PhotoPic.setImageDrawable(res);
                            isOpen = true;
                            PhotoHint.setVisibility(View.GONE);
                        }
                    });
                    bu.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = bu.create();
                    alert.show();
                break;

            case R.id.fiftyHint:
                isHint = true;
                AlertDialog.Builder bus = new AlertDialog.Builder(ActionActivity.this);
                bus.setTitle("Убрать два неверных ответа");
                bus.setMessage("Вы точно хотите использовать подсказку? (130 монет)");
                bus.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        if (random_defeat1 == 1 || random_defeat2 == 1){
                            button1.setVisibility(View.GONE);
                        }
                        if (random_defeat1 == 2 || random_defeat2 == 2){
                            button2.setVisibility(View.GONE);
                        }
                        if (random_defeat1 == 3 || random_defeat2 == 3){
                            button3.setVisibility(View.GONE);
                        }
                        if (random_defeat1 == 4 || random_defeat2 == 4){
                            button4.setVisibility(View.GONE);
                        }
                        FiftyHint.setVisibility(View.GONE);
                    }
                });
                bus.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.cancel();
                    }
                });
                AlertDialog alerts = bus.create();
                alerts.show();
                break;

            case R.id.refreshHint:
                isHint = true;
                AlertDialog.Builder buss = new AlertDialog.Builder(ActionActivity.this);
                buss.setTitle("Сменить вопрос");
                buss.setMessage("Вы точно хотите использовать подсказку? (50 монет)");
                buss.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        intent = new Intent(ActionActivity.this, ActionActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("PROGRESS", progress.getText().toString());
                        bundle.putString("TRIES", tried.getText().toString());
                        intent.putExtras(bundle);
                        ActionActivity.this.finish();
                        startActivity(intent);
                    }
                });
                buss.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertss = buss.create();
                alertss.show();
                break;

            case R.id.button1:
                butClick = true;
                if (answer == 1) {
                    CORRECT_ANSWER = 1;
                    button1.setBackgroundResource(R.color.right_answer);
                } else {
                    CORRECT_ANSWER = 0;
                    button1.setBackgroundResource(R.color.wrong_answer);
                }
                break;


            case R.id.button2:

                butClick = true;
                if (answer == 2) {
                    CORRECT_ANSWER = 1;
                    button2.setBackgroundResource(R.color.right_answer);

                } else {
                    CORRECT_ANSWER = 0;
                    button2.setBackgroundResource(R.color.wrong_answer);
                }
                break;

            case R.id.button3:
                butClick = true;
                if (answer == 3) {
                    CORRECT_ANSWER = 1;
                    button3.setBackgroundResource(R.color.right_answer);
                } else {
                    CORRECT_ANSWER = 0;
                    button3.setBackgroundResource(R.color.wrong_answer);
                }
                break;

            case R.id.button4:
                butClick = true;
                if (answer == 4) {
                    CORRECT_ANSWER = 1;
                    button4.setBackgroundResource(R.color.right_answer);

                } else {
                    CORRECT_ANSWER = 0;
                    button4.setBackgroundResource(R.color.wrong_answer);
                }
                break;
        }
if (isHint == false || butClick == true) {
    mediaPlayer.reset();
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Boolean BL = false;
            Boolean TL = false;
            Boolean SL = false;
            Intent i = new Intent(ActionActivity.this, ActionActivity.class);
            Bundle bundle = new Bundle();
            p = progress.getText().toString();
            tr = tried.getText().toString();
            trw = Integer.valueOf(tr);
            if (p == "") {
                if (CORRECT_ANSWER == 1) {
                    p = "1";
                } else {
                    p = "0";
                }
            } else {
                pw = Integer.valueOf(p);
                if (CORRECT_ANSWER == 1) {
                    pw++;
                }
                if (pw == 7) {
                    BL = true;
                }
                p = String.valueOf(pw);
            }
            if (CORRECT_ANSWER == 0){
                trw--;
                tr = String.valueOf(trw);
                if (trw <= 0){
                    TL = true;
                }
            }
            bundle.putString("PROGRESS", p);
            bundle.putString("TRIES", tr);
            bundle.putString("FromWhat", FromWhat);
            i.putExtras(bundle);
            if (TL == true){
                BL = true;
                SL = true;
                AlertDialog.Builder ads = new AlertDialog.Builder(ActionActivity.this);
                ads.setMessage("Вы проиграли...");
                ads.setPositiveButton("Заново", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        progress.setText("0");
                        tried.setText("3");
                        intent = new Intent(ActionActivity.this, ActionActivity.class);
                        ActionActivity.this.finish();
                        startActivity(intent);
                    }
                });
                ads.setNegativeButton("Меню", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        intent = new Intent(ActionActivity.this, MainActivity.class);
                        ActionActivity.this.finish();
                        startActivity(intent);
                    }
                });
                AlertDialog alert = ads.create();
                alert.show();
            }
            if (BL == false) {
                ActionActivity.this.finish();
                startActivity(i);
            } else if (SL == false) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActionActivity.this);
                builder.setTitle("Ура!")
                        .setMessage("Уровень пройден!")
                        .setCancelable(false)
                        .setNegativeButton("Далее",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        intent = new Intent(getApplicationContext(), RouteActivity_Cat.class);
                                        ActionActivity.this.finish();
                                        startActivity(intent);
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }, 1500);
}
    }

    @Override
    public void onBackPressed(){
        mediaPlayer.stop();
        AlertDialog.Builder ad = new AlertDialog.Builder(ActionActivity.this);
        ad.setMessage("Вы точно хотите выйти?");
        ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}