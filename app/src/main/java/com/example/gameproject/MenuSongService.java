package com.example.gameproject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * MenuSongService used to interact with menu music between multiple activities
 *
 */
public class MenuSongService extends Service {
    public static MediaPlayer mediaPlayer;
    public static float volume;

    public MenuSongService() {}

    /**
     * @param context Context used to add music to
     */
    public MenuSongService(Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.menu_song);
        mediaPlayer.setLooping(true);
        volume = 1.00F;
        mediaPlayer.setVolume(volume, volume);
        mediaPlayer.start();
    }

    /**
     * Starts the menu music
     */
    public static void startMenuSong() {
        mediaPlayer.start();
    }

    /**
     * Pauses the menu music
     */
    public static void pauseMenuSong() {
        mediaPlayer.pause();
    }

    /**
     * Used to adjust menu song volume
     * @param newVolume int to set menu song volume
     */
    public static void setMenuSongVolume(int newVolume) {
        volume = (float) (newVolume / 100.00);
        mediaPlayer.setVolume(volume, volume);
    }

    /**
     * @return mediaPlayer
     */
    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}