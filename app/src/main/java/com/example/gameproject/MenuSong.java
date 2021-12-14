package com.example.gameproject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MenuSong extends Service {
    public static MediaPlayer mediaPlayer;

    public MenuSong() {}
    public MenuSong(Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.menu_song);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public static void startMenuSong() {
        mediaPlayer.start();
    }

    public static void pauseMenuSong() {
        mediaPlayer.pause();
    }

    public static void setMenuSongVolume(int volume) {
        float setVolume = (float) (volume / 100.00);
        Constants.VOLUME = volume;
        mediaPlayer.setVolume(setVolume, setVolume);
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}