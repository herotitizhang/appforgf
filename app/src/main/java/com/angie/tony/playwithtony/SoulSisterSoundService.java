package com.angie.tony.playwithtony;

import android.media.MediaPlayer;

public class SoulSisterSoundService extends SoundService {
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.soul_sister);
    }
}