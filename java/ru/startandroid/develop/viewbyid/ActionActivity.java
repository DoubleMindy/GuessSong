package ru.startandroid.develop.viewbyid;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.database.Cursor;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.view.animation.Animation;

import cn.pedant.SweetAlert.SweetAlertDialog;


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
    String another_prog;
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
    String last_level;
    Animation anim;
Boolean is_all_complete = false;

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
        last_level = getIntent().getStringExtra("CURRENT");

        SharedPreferences sPref = getSharedPreferences("PREF", Context.MODE_PRIVATE);
        if (FromWhat.equals("cat")) {
            another_prog = sPref.getString("saved_prog", "0");
        }else{
            another_prog = sPref.getString("saved_prog_cat", "0");
        }


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

        if (FromWhat.equals("cat")) {
            lvl = getIntent().getStringExtra("LEVEL");
            c = DbHelper.query("ARTISTS", null, "cat LIKE ?", new String[]{"%" + lvl + "%"}, null, null, null);
        }
        else{
            lvl = getIntent().getStringExtra("LEVEL");
            c = DbHelper.query("ARTISTS", null, "aut_cat LIKE ?", new String[]{"%" + lvl + "%"}, null, null, null);
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
            manageBlinkEffect();
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
                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Открыть фото исполнителя?")
                            .setContentText("Вы точно хотите использовать подсказку? (100 монет)")
                            .setConfirmText("Да")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    String PathName = "@drawable/" + datatag;
                                    int imageResource = getResources().getIdentifier(PathName, null, getPackageName());
                                    Drawable res = getResources().getDrawable(imageResource);
                                    Photo1.clearAnimation();
                                    PhotoPic.setImageDrawable(res);
                                    int coins_val_photo = Integer.valueOf(coins.getText().toString());
                                    coins_val_photo -= 100;
                                    coins.setText(String.valueOf(coins_val_photo));
                                    CurrentCoins = String.valueOf(coins_val_photo);
                                    PhotoHint.setVisibility(View.GONE);
                                    sDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .setCancelButton("Нет", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
                break;

            case R.id.fiftyHint:
                isHint = true;
                if (Integer.valueOf(coins.getText().toString()) < 130) {
                    Toast.makeText(this, "Недостаточно монет!", Toast.LENGTH_SHORT).show();
                } else {
                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Убрать два неверных ответа?")
                            .setContentText("Вы точно хотите использовать подсказку? (130 монет)")
                            .setConfirmText("Да")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
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
                                    sDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .setCancelButton("Нет", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
                break;

            case R.id.refreshHint:
                isHint = true;
                if (Integer.valueOf(coins.getText().toString()) < 50) {
                    Toast.makeText(this, "Недостаточно монет!", Toast.LENGTH_SHORT).show();
                }
                else {
                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Сменить вопрос?")
                            .setContentText("Вы точно хотите использовать подсказку? (50 монет)")
                            .setConfirmText("Да")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    mediaPlayer.stop();
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
                                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                                    sDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .setCancelButton("Нет", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
                break;

            case R.id.FreeHint:
                isHint = true;
                isFreeHint = false;
                int pro = Integer.valueOf(progress.getText().toString());
                pro++;
                if (pro == 7) {
                    LevelComplete();
                }
                else {

                    intent = new Intent(ActionActivity.this, ActionActivity.class);
                    Bundle bundle = new Bundle();
                    INAROW = 0;
                    bundle.putString("PROGRESS", String.valueOf(pro));
                    bundle.putString("TRIES", tried.getText().toString());
                    bundle.putString("COINS", coins.getText().toString());
                    bundle.putString("FromWhat", FromWhat);
                    bundle.putString("LEVEL", lvl);
                    bundle.putString("CURRENT", last_level);
                    bundle.putBoolean("FREEHINT", isFreeHint);
                    bundle.putInt("ROW", INAROW);
                    intent.putExtras(bundle);
                    ActionActivity.this.finish();
                    mediaPlayer.stop();
                    startActivity(intent);
                }
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
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
                if (pw == 2) { // ALL SONGS
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
            bundle.putString("CURRENT", last_level);
            bundle.putString("COINS", coins.getText().toString());
            bundle.putString("LEVEL", lvl);
            bundle.putInt("ROW", INAROW);
            bundle.putBoolean("FREEHINT", isFreeHint);
            i.putExtras(bundle);

            if (TL == true){
                BL = true;
                SL = true;
                new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Вы проиграли...")
                        .setConfirmButton("Меню", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {

                                SharedPreferences sPref = getSharedPreferences("PREF", Context.MODE_PRIVATE);
                                SharedPreferences.Editor ed = sPref.edit();
                                ed.putString("saved_coin", coins.getText().toString());

                                if (FromWhat.equals("cat")){
                                    intent = new Intent(ActionActivity.this, RouteActivity_Cat.class);
                                    ed.putString("saved_prog_cat", String.valueOf(Integer.valueOf(last_level) - 1));
                                    ed.putString("saved_prog", another_prog);
                                }
                                else {
                                    intent = new Intent(ActionActivity.this, RouteActivity_Aut.class);
                                    ed.putString("saved_prog", String.valueOf(Integer.valueOf(last_level) - 1));
                                    ed.putString("saved_prog_cat", another_prog);
                                }
                                ed.commit();
                                Bundle bundle = new Bundle();
                                intent.putExtra("EXIT", true);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtras(bundle);
                                ActionActivity.this.finish();
                                startActivity(intent);
                                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            }
            if (BL == false) {
                ActionActivity.this.finish();
                startActivity(i);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            } else if (SL == false) {
                is_all_complete = true;
                LevelComplete();
            }
        }
    }


    // БАГ ВЫЛЕТА В ГЛАВНОЕ МЕНЮ
    @Override
    public void onBackPressed(){
        AlertDialog.Builder ad = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        ad.setMessage("Вы точно хотите выйти?");
        ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                mediaPlayer.stop();
                SharedPreferences sPref = getSharedPreferences("PREF", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString("saved_coin", coins.getText().toString());
                if (FromWhat.equals("cat")){
                    intent = new Intent(ActionActivity.this, RouteActivity_Cat.class);
                    ed.putString("saved_prog_cat", String.valueOf(Integer.valueOf(last_level) - 1));
                    ed.putString("saved_prog", another_prog);
                }
                else {
                    intent = new Intent(ActionActivity.this, RouteActivity_Aut.class);
                    ed.putString("saved_prog", String.valueOf(Integer.valueOf(last_level) - 1));
                    ed.putString("saved_prog_cat", another_prog);
                }
                ed.commit();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                ActionActivity.this.finish();
                startActivity(intent);
                overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
            }
        });
        ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.cancel();
            }
        });
        AlertDialog alert = ad.create();
        alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnim;
        alert.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void saveText() {
        SharedPreferences sPref = getSharedPreferences("PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("saved_coin", coins.getText().toString());
        String WHERE_STORE;
        if (FromWhat.equals("cat")) {
            if (last_level.equals(lvl)) {
                ed.putString("saved_prog_cat", lvl);
                ed.putString("saved_prog", another_prog);
            } else {
                ed.putString("saved_prog_cat", String.valueOf(Integer.valueOf(last_level) - 1));
                ed.putString("saved_prog", another_prog);
            }
        }else{
            if (last_level.equals(lvl)) {
                ed.putString("saved_prog", lvl);
                ed.putString("saved_prog_cat", another_prog);
            } else {
                ed.putString("saved_prog", String.valueOf(Integer.valueOf(last_level) - 1));
                ed.putString("saved_prog_cat", another_prog);
            }
        }
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
            bt.setBackgroundResource(R.drawable.button_right);
            if (INAROW == 4){
                isFreeHint = true;
            }

        } else {
            CORRECT_ANSWER = 0;
            INAROW = 0;
            bt.setBackgroundResource(R.drawable.button_wrong);
        }
    }

    public void LevelComplete(){
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Уровень пройден!")
                .setConfirmButton("Далее", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        saveText();
                        if (FromWhat.equals("cat")){
                            intent = new Intent(ActionActivity.this, RouteActivity_Cat.class);
                        }
                        else {
                            intent = new Intent(ActionActivity.this, RouteActivity_Aut.class);
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("EXIT", true);
                        intent.putExtras(bundle);
                        ActionActivity.this.finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }


    private void manageBlinkEffect() {
        ObjectAnimator anim = ObjectAnimator.ofInt(FreeHint, "backgroundColor", Color.TRANSPARENT, Color.GREEN,
                Color.TRANSPARENT);
        anim.setDuration(1500);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.start();
    }

}