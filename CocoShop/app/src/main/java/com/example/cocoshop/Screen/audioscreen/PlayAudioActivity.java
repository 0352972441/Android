package com.example.cocoshop.Screen.audioscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.cocoshop.Models.audiomodels.ModelPlay;
import com.example.cocoshop.R;

import java.util.Objects;

public class PlayAudioActivity extends AppCompatActivity {
    // Key get Bunder
    public static final String KEYAUDIO = "AUDIO";
    public static final String CATEGORY = "CATEGORY";
    public static final String READER = "READER";
    public static final String TITLE = "TITLE";
    public static final String URLAUDIO = "URLAUDIO";
    public static final String URLREADER = "URLREADER";
    public static final String AUDIOIMAGE = "AUDIOIMAGE";
    public static final String FAVORITE = "FAVORITE";

    private ImageView img_Audio,img_Favorite,img_Play_Audio,img_Bnt_Next_Audio,img_Bnt_Priveous_Audio,img_Bnt_content_Audio;
    private TextView txTitleAudio,txReaderNameAudio;
    private SeekBar seekBarAudio;
    private ModelPlay modelPlay = ModelPlay.PLAY;
    private Bundle audio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);
        seekBarAudio = (SeekBar)findViewById(R.id.seek_bar);
        txTitleAudio = (TextView)findViewById(R.id.title_audio);
        txReaderNameAudio = (TextView)findViewById(R.id.reader_name_audio);
        img_Audio = (ImageView)findViewById(R.id.img_audio);
        img_Favorite = (ImageView)findViewById(R.id.favorite);
        img_Play_Audio = (ImageView)findViewById(R.id.img_play_audio);
        img_Bnt_Next_Audio = (ImageView)findViewById(R.id.img_bnt_next_audio);
        img_Bnt_Priveous_Audio = (ImageView)findViewById(R.id.img_bnt_previous_audio);
        img_Bnt_content_Audio = (ImageView)findViewById(R.id.img_content_audio);
        onClickPlay();
        onClickFavoriteAudio();
    }

    private void onClickPlay(){
        img_Play_Audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modelPlay == ModelPlay.PLAY){
                    img_Play_Audio.setImageResource(R.drawable.ic_play_circle_audio_bluesky_24dp);
                    modelPlay = ModelPlay.PAUSE;
                }else{
                    img_Play_Audio.setImageResource(R.drawable.ic_pause_circle_filled_pause_audio_24dp);
                    modelPlay = ModelPlay.PLAY;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        audio = Objects.requireNonNull(getIntent().getExtras()).getBundle(KEYAUDIO);
        txTitleAudio.setText(audio.getString(TITLE).toString());
        txReaderNameAudio.setText(audio.getString(READER).toString());
        img_Audio.setImageResource(R.drawable.background_card_item);
    }

    private void onClickNextAudio(){

    }
    private void onClickPreviousAudio(){}
    private void onClickShowContextAudio(){}
    private void onClickFavoriteAudio(){
        img_Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isFavorite = !getIntent().getExtras().getBoolean(FAVORITE);
                if(isFavorite){
                    img_Favorite.setImageResource(R.drawable.ic_on_favorite_audio_24dp);
                }else{
                    img_Favorite.setImageResource(R.drawable.ic_un_favorite_audio_24dp);
                }
            }
        });
    }

}
