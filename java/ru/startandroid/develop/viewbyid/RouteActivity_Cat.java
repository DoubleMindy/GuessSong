package ru.startandroid.develop.viewbyid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

@RequiresApi(api = Build.VERSION_CODES.FROYO)
public class RouteActivity_Cat extends AppCompatActivity implements View.OnClickListener, SoundPool.OnLoadCompleteListener {
    SoundPool sp;
    int soundIdShort;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    String FROM = "cat";
    String p = "0";
    String tr = "3";
    String coins;
    String from;
    String completed = "0";
    String last_level = "0";
    String completed_check;
    Boolean isFreeHint = false;
    int row = 0;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_act);

        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);

        // SOUND POOL
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);
        soundIdShort = sp.load(this, R.raw.long_sound, 1);


        SharedPreferences sPref = getSharedPreferences("PREF", Context.MODE_PRIVATE);
        coins = sPref.getString("saved_coin", "0");
        completed_check = sPref.getString("saved_prog_cat", "0");
        Toast.makeText(this, completed_check, Toast.LENGTH_SHORT).show();

        CloseOrNot(btn1, "1");
        CloseOrNot(btn2, "2");
        CloseOrNot(btn3, "3");
        CloseOrNot(btn4, "4");
        CloseOrNot(btn5, "5");
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
                if(Integer.valueOf(completed_check) + 1 < Integer.valueOf(level)){
                    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Уровень закрыт!")
                            .setContentText("Пройдите все предыдущие уровни для того, чтобы открыть этот")
                            .setConfirmButton("ОК", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
                else {
                    intent.putExtra("CURRENT", last_level);
                    intent.putExtra("FromWhat", FROM);
                    intent.putExtra("PROGRESS", p);
                    intent.putExtra("TRIES", tr);
                    intent.putExtra("COINS", coins);
                    intent.putExtra("LEVEL", level);
                    intent.putExtra("ROW", row);
                    intent.putExtra("FREEHINT", isFreeHint);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                }
                break;

            case R.id.btn2:
                level = "2";
                intent = new Intent(getApplicationContext(), ActionActivity.class);
                sp.play(soundIdShort, 1, 1, 0, 0, 1);
                if(Integer.valueOf(completed_check) + 1 < Integer.valueOf(level)){
                    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Уровень закрыт!")
                            .setContentText("Пройдите все предыдущие уровни для того, чтобы открыть этот")
                            .setConfirmButton("ОК", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
                else {
                    intent.putExtra("CURRENT", last_level);
                    intent.putExtra("FromWhat", FROM);
                    intent.putExtra("PROGRESS", p);
                    intent.putExtra("TRIES", tr);
                    intent.putExtra("COINS", coins);
                    intent.putExtra("LEVEL", level);
                    intent.putExtra("ROW", row);
                    intent.putExtra("FREEHINT", isFreeHint);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                }
                break;

            case R.id.btn3:
                level = "3";
                intent = new Intent(getApplicationContext(), ActionActivity.class);
                sp.play(soundIdShort, 1, 1, 0, 0, 1);
                if(Integer.valueOf(completed_check) + 1 < Integer.valueOf(level)){
                    new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Уровень закрыт!")
                            .setContentText("Пройдите все предыдущие уровни для того, чтобы открыть этот")
                            .setConfirmButton("ОК", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
                else {
                    intent.putExtra("CURRENT", last_level);
                    intent.putExtra("FromWhat", FROM);
                    intent.putExtra("PROGRESS", p);
                    intent.putExtra("TRIES", tr);
                    intent.putExtra("COINS", coins);
                    intent.putExtra("LEVEL", level);
                    intent.putExtra("ROW", row);
                    intent.putExtra("FREEHINT", isFreeHint);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                }
                break;
        }
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
        //
    }
    public void CloseOrNot(Button btn, String lvl){
        if(Integer.valueOf(completed_check) + 1 < Integer.valueOf(lvl)){
            btn.setText("Закрыто");
        }
        else if (Integer.valueOf(completed_check) + 1 == Integer.valueOf(lvl)){
            btn.setBackgroundResource(R.drawable.what_act_current);
            last_level = lvl;
        }
        else if (Integer.valueOf(completed_check) + 1 > Integer.valueOf(lvl)){
            btn.setBackgroundResource(R.drawable.what_act_done);
        }
    }
}
