package ru.startandroid.develop.viewbyid;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
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
    Button FreeHint;
    ImageView PhotoPic;
    ImageView Photo1;
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
    Boolean butClick = false;
    int random_defeat1;
    int random_defeat2;
    String FromWhat;
    int idColIndex;
    int idSong;
    int INAROW;
    Boolean isFreeHint;
    String ART;
    String lvl = "1";
    String CurrentCoins;
    TextView coins;
    SharedPreferences sPref;
    Animation anim;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // INITIALIZE
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action);
        progress = (TextView) findViewById(R.id.Progress);
        tried = (TextView) findViewById(R.id.Tried);
        coins =(TextView) findViewById(R.id.coin);
        PhotoHint = (ImageButton) findViewById(R.id.PhotoHint);
        FiftyHint = (ImageButton) findViewById(R.id.fiftyHint);
        RefreshHint = (ImageButton) findViewById(R.id.refreshHint);
        PhotoPic = (ImageView) findViewById(R.id.Photo);
        Photo1 = (ImageView) findViewById(R.id.Photo1);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        FreeHint = (Button) findViewById(R.id.FreeHint);

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
        FreeHint.setOnClickListener(this);

        FromWhat = getIntent().getStringExtra("FromWhat");
       // Photo1.startAnimation(
       //         AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation) );

        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
        Photo1.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                Photo1.setVisibility(View.GONE);

            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationStart(Animation animation) {}
        });

        // ANSWERS
// ...
        if (FromWhat.equals("cat")) {
            lvl = getIntent().getStringExtra("LEVEL");
            c = DbHelper.query("ARTISTS", null, "cat = ?", new String[]{lvl}, null, null, null);
        }
        else{
            lvl = getIntent().getStringExtra("LEVEL");
            c = DbHelper.query("ARTISTS", null, "aut_cat = ?", new String[]{lvl}, null, null, null);
        }
        int DBLENGTH = c.getCount();
        answer = (int) (Math.random() * 4) + 1;

        int r1 = (int) (Math.random() * DBLENGTH);
        c.moveToPosition(r1);
        if (answer == 1){
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
        // FIFTY-FIFTY
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
            coins.setText(extras.getString("COINS"));
            CurrentCoins = extras.getString("COINS");
            isFreeHint = extras.getBoolean("FREEHINT");
            INAROW = extras.getInt("ROW");
        }
        c.close();
        DbHelper.close();

        if (isFreeHint == true){
            FreeHint.setVisibility(View.VISIBLE);
        }
        else{
            FreeHint.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        CORRECT_ANSWER = 0;
        switch (v.getId()) {
            case R.id.PhotoHint:
                isHint = true;
                if (Integer.valueOf(coins.getText().toString()) < 100) {
                    Toast.makeText(this, "Недостаточно монет!", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder bu = new AlertDialog.Builder(ActionActivity.this);
                    bu.setTitle("Открыть фото исполнителя");
                    bu.setMessage("Вы точно хотите использовать подсказку? (100 монет)");
                    bu.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            String PathName = "@drawable/" + datatag;
                            int imageResource = getResources().getIdentifier(PathName, null, getPackageName());
                            Drawable res = getResources().getDrawable(imageResource);
                            Photo1.clearAnimation();
                            //        Photo1.setImageDrawable(res);
                            PhotoPic.setImageDrawable(res);
                            int coins_val_photo = Integer.valueOf(coins.getText().toString());
                            coins_val_photo -= 100;
                            coins.setText(String.valueOf(coins_val_photo));
                            CurrentCoins = String.valueOf(coins_val_photo);
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
                }
                break;

            case R.id.fiftyHint:
                isHint = true;
                if (Integer.valueOf(coins.getText().toString()) < 130) {
                    Toast.makeText(this, "Недостаточно монет!", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder bus = new AlertDialog.Builder(ActionActivity.this);
                    bus.setTitle("Убрать два неверных ответа");
                    bus.setMessage("Вы точно хотите использовать подсказку? (130 монет)");
                    bus.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            if (random_defeat1 == 1 || random_defeat2 == 1) {
                                button1.setVisibility(View.GONE);
                            }
                            if (random_defeat1 == 2 || random_defeat2 == 2) {
                                button2.setVisibility(View.GONE);
                            }
                            if (random_defeat1 == 3 || random_defeat2 == 3) {
                                button3.setVisibility(View.GONE);
                            }
                            if (random_defeat1 == 4 || random_defeat2 == 4) {
                                button4.setVisibility(View.GONE);
                            }
                            FiftyHint.setVisibility(View.GONE);

                            int coins_val_fifty = Integer.valueOf(coins.getText().toString());
                            coins_val_fifty -= 130;
                            coins.setText(String.valueOf(coins_val_fifty));
                            CurrentCoins = String.valueOf(coins_val_fifty);
                        }
                    });
                    bus.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alerts = bus.create();
                    alerts.show();
                }
                break;

            case R.id.refreshHint:
                isHint = true;
                if (Integer.valueOf(coins.getText().toString()) < 50) {
                    Toast.makeText(this, "Недостаточно монет!", Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder buss = new AlertDialog.Builder(ActionActivity.this);
                    buss.setTitle("Сменить вопрос");
                    buss.setMessage("Вы точно хотите использовать подсказку? (50 монет)");
                    buss.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {

                            int coins_val_new = Integer.valueOf(coins.getText().toString());
                            coins_val_new -= 130;
                            coins.setText(String.valueOf(coins_val_new));
                            CurrentCoins = String.valueOf(coins_val_new);

                            intent = new Intent(ActionActivity.this, ActionActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("PROGRESS", progress.getText().toString());
                            bundle.putString("TRIES", tried.getText().toString());
                            bundle.putString("COINS", coins.getText().toString());
                            bundle.putString("FromWhat", FromWhat);
                            bundle.putString("LEVEL", lvl);
                            bundle.putBoolean("FREEHINT", isFreeHint);
                            bundle.putInt("ROW", INAROW);
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
                }
                break;

            case R.id.FreeHint:
                isHint = true;
                isFreeHint = false;
                intent = new Intent(ActionActivity.this, ActionActivity.class);
                Bundle bundle = new Bundle();
                INAROW = 0;
                int pro = Integer.valueOf(progress.getText().toString());
                pro++;
                bundle.putString("PROGRESS", String.valueOf(pro));
                bundle.putString("TRIES", tried.getText().toString());
                bundle.putString("COINS", coins.getText().toString());
                bundle.putString("FromWhat", FromWhat);
                bundle.putString("LEVEL", lvl);
                bundle.putBoolean("FREEHINT", isFreeHint);
                bundle.putInt("ROW", INAROW);
                intent.putExtras(bundle);
                ActionActivity.this.finish();
                startActivity(intent);
                break;

            case R.id.button1:
                ButtonReact(1, button1, v);
                break;

            case R.id.button2:
                ButtonReact(2, button2, v);
                break;

            case R.id.button3:
                ButtonReact(3, button3, v);
                break;

            case R.id.button4:
                ButtonReact(4, button4, v);
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
                if (pw == 7) { // ALL SONGS
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
            bundle.putString("COINS", coins.getText().toString());
            bundle.putString("LEVEL", lvl);
            bundle.putInt("ROW", INAROW);
            bundle.putBoolean("FREEHINT", isFreeHint);
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
                        Bundle bundle = new Bundle();
                        bundle.putString("COINS_AFTER", coins.getText().toString());
                        intent.putExtras(bundle);
                        ActionActivity.this.finish();
                        startActivity(intent);
                    }
                });
                ads.setNegativeButton("Меню", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        intent = new Intent(ActionActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        intent.putExtra("EXIT", true);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        bundle.putString("COINS_AFTER", coins.getText().toString());
                        intent.putExtras(bundle);
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
                                        saveText();
                                        intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("EXIT", true);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("COINS_AFTER", coins.getText().toString());
                                        intent.putExtras(bundle);
                                        ActionActivity.this.finish();
                                        startActivity(intent);
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }, 1200);
}
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder ad = new AlertDialog.Builder(ActionActivity.this);
        ad.setMessage("Вы точно хотите выйти?");
        ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                mediaPlayer.stop();
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle bundle = new Bundle();
                bundle.putString("COINS_AFTER", coins.getText().toString());
                intent.putExtras(bundle);
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
            saveText();
        }
    }

    public void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("saved_coin", CurrentCoins);
        ed.commit();
    }

    public void ButtonReact(int butId, Button bt, View v){
        butClick = true;
        if (answer == butId) {
            CORRECT_ANSWER = 1;
            INAROW += 1;
            int coins_val = Integer.valueOf(coins.getText().toString());
            coins_val += 100;
            coins.setText(String.valueOf(coins_val));
            CurrentCoins = String.valueOf(coins_val);
            bt.setBackgroundResource(R.color.right_answer);
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            if (INAROW == 4){
                builder.setMessage("Потрясающе! Бесплатная подсказка!");
                isFreeHint = true;
            }
            if (INAROW == 2 || INAROW == 3){
                builder.setMessage("Отлично!");
            }
            else {
                builder.setMessage("Верно!");
            }
            builder.setCancelable(true);

            final AlertDialog dlg = builder.create();

            dlg.show();

            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    dlg.dismiss();
                    timer.cancel();
                }
            }, 1000);

        } else {
            CORRECT_ANSWER = 0;
            INAROW = 0;
            bt.setBackgroundResource(R.color.wrong_answer);
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage("Неверно!");
            builder.setCancelable(true);

            final AlertDialog dlg = builder.create();

            dlg.show();

            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    dlg.dismiss();
                    timer.cancel();
                }
            }, 1000);
        }
    }

}