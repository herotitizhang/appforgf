package com.angie.tony.playwithtony;

import android.media.MediaPlayer;

public class InMySongSoundService extends SoundService {
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.wo_de_ge_sheng_li);
    }
}