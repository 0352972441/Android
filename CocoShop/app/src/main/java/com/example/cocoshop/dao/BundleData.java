package com.example.cocoshop.dao;

import android.os.Bundle;

import com.example.cocoshop.models.audiomodels.Audio;
import com.example.cocoshop.screen.audioscreen.PlayAudioActivity;

public class BundleData {
    public static Bundle sendData(Audio audio){
        Bundle bundle = new Bundle();
        bundle.putInt(PlayAudioActivity.AUDIOIMAGE,audio.getImageAudio());
        //bundle.putString(PlayAudioActivity.CATEGORY,audio.getCategory().toString());
        bundle.putString(PlayAudioActivity.TITLE,audio.getSound().getTitle());
        bundle.putString(PlayAudioActivity.READER,audio.getReaderName());
        bundle.putString(PlayAudioActivity.URLAUDIO,audio.getSound().getUrlSound().toString());
        bundle.putBoolean(PlayAudioActivity.FAVORITE,audio.isFavorite());
        return  bundle;
    }
}
