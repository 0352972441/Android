package com.example.cocoshop.dao.audiodao;

import android.os.Bundle;

import com.example.cocoshop.Models.audiomodels.Audio;
import com.example.cocoshop.Screen.audioscreen.PlayAudioActivity;

public class BundleData {
    public static Bundle sendData(Audio audio){
        Bundle bundle = new Bundle();
        bundle.putInt(PlayAudioActivity.AUDIOIMAGE,audio.getImageAudio());
        //bundle.putString(PlayAudioActivity.CATEGORY,audio.getCategory().toString());
        bundle.putString(PlayAudioActivity.TITLE,audio.getTitle());
        bundle.putString(PlayAudioActivity.READER,audio.getReaderName());
        bundle.putString(PlayAudioActivity.URLAUDIO,audio.getUrlAudio());
        bundle.putBoolean(PlayAudioActivity.FAVORITE,audio.isFavorite());
        return  bundle;
    }
}
