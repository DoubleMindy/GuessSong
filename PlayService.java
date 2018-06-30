package ru.startandroid.develop.viewbyid;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
public class PlayService extends Service {
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        player = MediaPlayer.create(this, R.raw.main);
        int maxVolume = 100;
        final float volume = (float) (1 - (Math.log(maxVolume - 40) / Math.log(maxVolume)));
        player.setVolume(volume, volume);
        player.setLooping(true);
    }

    @Override
    public void onDestroy() {
        player.stop();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        player.start();
    }

}