package com.angie.tony.playwithtony;


import android.media.MediaPlayer;

public class BeautifulSoundService extends SoundService {
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.what_makes_you_beautiful);
    }
}
