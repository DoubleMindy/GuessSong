package ru.startandroid.develop.viewbyid;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.database.Cursor;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import java.io.IOException;
public class ActionActivity extends AppCompatActivity implements View.OnClickListener {
    Cursor c = null;
    ImageButton PhotoHint;
    ImageView PhotoPic;
    MediaPlayer mediaPlayer;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    AssetFileDescriptor afd;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action);

        PhotoHint = (ImageButton) findViewById(R.id.PhotoHint);
        PhotoPic = (ImageView) findViewById(R.id.Photo);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        String datatag = "loboda";

        DatabaseHelper DbHelper = new DatabaseHelper(ActionActivity.this);
        try {
            DbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        DbHelper.openDataBase();

        PhotoPic.setVisibility(View.INVISIBLE);
        PhotoHint.setOnClickListener(this);

        c = DbHelper.query("ARTISTS", null, null, null, null, null, null);
        int DBLENGTH = c.getCount();
        int answer = (int) (Math.random() * DBLENGTH + 1);

        int r1 = (int) (Math.random() * DBLENGTH);
        c.moveToPosition(r1);
        if (answer == 1){
            int idColIndex = c.getColumnIndex("link");
            datatag = c.getString(idColIndex);
        }
        int idColIndex = c.getColumnIndex("name");
        button1.setText(c.getString(idColIndex));


        int r2 = (int) (Math.random() * DBLENGTH);
        while (r1 == r2) {
            r2 = (int) (Math.random() * DBLENGTH);
        }
        c.moveToPosition(r2);
        if (answer == 2){
            idColIndex = c.getColumnIndex("link");
            datatag = c.getString(idColIndex);
        }
        idColIndex = c.getColumnIndex("name");
        button2.setText(c.getString(idColIndex));

        int r3 = (int) (Math.random() * DBLENGTH);
        while ((r3 == r2) || (r3 == r1)) {
            r3 = (int) (Math.random() * DBLENGTH);
        }
        c.moveToPosition(r3);
        if (answer == 3){
            idColIndex = c.getColumnIndex("link");
            datatag = c.getString(idColIndex);
        }
        idColIndex = c.getColumnIndex("name");
        button3.setText(c.getString(idColIndex));

        int r4 = (int) (Math.random() * DBLENGTH);
        while ((r4 == r3) || (r4 == r2) || (r4 == r1)) {
            r4 = (int) (Math.random() * DBLENGTH);
        }
        c.moveToPosition(r4);
        if (answer == 4){
            idColIndex = c.getColumnIndex("link");
            datatag = c.getString(idColIndex);
        }
        idColIndex = c.getColumnIndex("name");
        button4.setText(c.getString(idColIndex));

        int resID=getResources().getIdentifier(datatag, "raw", ActionActivity.this.getPackageName());
        MediaPlayer mediaPlayer=MediaPlayer.create(this, resID);
        mediaPlayer.start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.PhotoHint:
                PhotoPic.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBackPressed(){
        mediaPlayer.stop();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

}