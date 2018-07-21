package ru.startandroid.develop.viewbyid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RouteActivity_Aut extends AppCompatActivity implements SoundPool.OnLoadCompleteListener, View.OnClickListener {

    SoundPool sp;
    int soundIdShort;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    String FROM = "aut";
    String p = "0";
    String tr = "3";
    String coins;
    String from;
    String completed = "0";
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


        if(getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            coins = extras.getString("COINS");
            completed = extras.getString("COMPLETED_AFTER");
            completed_check = extras.getString("COMPLETED");
        }
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
                    intent.putExtra("FromWhat", FROM);
                    intent.putExtra("PROGRESS", p);
                    intent.putExtra("TRIES", tr);
                    intent.putExtra("COINS", coins);
                    intent.putExtra("LEVEL", level);
                    intent.putExtra("ROW", row);
                    intent.putExtra("COMPLETED", completed);
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
                    intent.putExtra("FromWhat", FROM);
                    intent.putExtra("PROGRESS", p);
                    intent.putExtra("TRIES", tr);
                    intent.putExtra("COINS", coins);
                    intent.putExtra("LEVEL", level);
                    intent.putExtra("ROW", row);
                    intent.putExtra("COMPLETED", completed);
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
                    intent.putExtra("FromWhat", FROM);
                    intent.putExtra("PROGRESS", p);
                    intent.putExtra("TRIES", tr);
                    intent.putExtra("COINS", coins);
                    intent.putExtra("LEVEL", level);
                    intent.putExtra("ROW", row);
                    intent.putExtra("COMPLETED", completed);
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
    //    btn.setEnabled(false);
    }
    else if (Integer.valueOf(completed_check) + 1 == Integer.valueOf(lvl)){
        btn.setBackgroundResource(R.drawable.what_act_current);
    }
    else if (Integer.valueOf(completed_check) + 1 > Integer.valueOf(lvl)){
        btn.setBackgroundResource(R.drawable.what_act_done);
    }
}
}