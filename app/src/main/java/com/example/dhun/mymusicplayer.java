package com.example.dhun;

import android.media.MediaPlayer;

public class mymusicplayer {

    static MediaPlayer instance;

    public static MediaPlayer getInstance(){
        if(instance == null){
            instance = new MediaPlayer();
        }
        return instance;
    }

    public static int currentIndex = -1;
}
