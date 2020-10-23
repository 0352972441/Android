package com.example.cocoshop.screen.audioscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocoshop.models.audiomodels.ModelPlay;
import com.example.cocoshop.R;
import com.example.cocoshop.animation.Animations;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Locale;
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
    public  String urlAudio;
    private MediaPlayer player  = new MediaPlayer();
    private Animations animations;
    private Animation rotate;
    private ImageView img_Audio,img_Favorite,img_Play_Audio,img_Bnt_Next_Audio,img_Bnt_Priveous_Audio,img_Bnt_content_Audio;
    private TextView txTitleAudio,txReaderNameAudio,txRemainTimeAudio,txTotalTimeAudio;
    private CardView cardImgCD;
    private SeekBar seekBarAudio;
    private ModelPlay modelPlay = ModelPlay.PLAY;
    private Bundle audio;
    private Handler handler;
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
        txRemainTimeAudio = (TextView)findViewById(R.id.remain_time_of_audio);
        txTotalTimeAudio = (TextView)findViewById(R.id.total_time_of_audio);
        cardImgCD = (CardView)findViewById(R.id.card_img_audio);
        animations = new Animations(this);
        handler = new Handler();
        setSeekBarAudio();
    }

    private void onClickPlay(){
        try {
            player.setDataSource(String.valueOf(urlAudio));
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(final MediaPlayer mp) {
                    mp.start();
                    rotate = animations.rotate(mp.getDuration());
                    cardImgCD.startAnimation(rotate);
                    seekBarAudio.setMax(mp.getDuration());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            long sencond = mp.getCurrentPosition()/1000L;
                           /* long hour = sencond / 60;*/
                            long sec = sencond% 60;
                            long minute = (sencond/3600)/60;
                            String timeTotal = String.format(Locale.getDefault(),"%02d:%02d",minute,sec);
                            txRemainTimeAudio.setText(timeTotal);
                            sencond = (mp.getDuration() - mp.getCurrentPosition())/1000L;
                            /*hour = sencond / 60;*/
                            sec = sencond% 60;
                            minute = (sencond/3600)/60;
                            txTotalTimeAudio.setText(String.valueOf(String.format(Locale.getDefault(),"%02d:%02d",minute,sec)));
                            seekBarAudio.setProgress(mp.getCurrentPosition());
                            handler.postDelayed(this,1000);
                        }
                    });
                }
            });
            player.prepare();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.d("Duration:",mp.getCurrentPosition()+" \n PostionDuration:"+mp.getCurrentPosition());
                    img_Play_Audio.setImageResource(R.drawable.ic_play_circle_audio_bluesky_24dp);
                    seekBarAudio.setProgress(0);
                    cardImgCD.clearAnimation();
                    player.reset();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        img_Play_Audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modelPlay == ModelPlay.PLAY){
                    if(!player.isPlaying()){
                        player.start();
                        return;
                    }
                    cardImgCD.clearAnimation();
                    player.pause();
                    img_Play_Audio.setImageResource(R.drawable.ic_play_circle_audio_bluesky_24dp);
                    modelPlay = ModelPlay.PAUSE;
                }else{
                    rotate = animations.rotate(player.getDuration()-player.getCurrentPosition());
                    cardImgCD.startAnimation(rotate);
                    player.start();
                    img_Play_Audio.setImageResource(R.drawable.ic_pause_circle_filled_pause_audio_24dp);
                    modelPlay = ModelPlay.PLAY;
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        audio = Objects.requireNonNull(getIntent().getExtras()).getBundle(KEYAUDIO);
        txTitleAudio.setText(audio.getString(TITLE).toString());
        txReaderNameAudio.setText(audio.getString(READER).toString());
        Picasso.with(this).load(R.drawable.background_card_item).placeholder(R.drawable.background_card_item).error(R.drawable.background_card_item).into(img_Audio);
        urlAudio = audio.getString(URLAUDIO);
        onClickPlay();
        onClickFavoriteAudio();
        onClickNextAudio();
    }

    private void onClickNextAudio(){
       img_Bnt_Next_Audio.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               /*if(index > CardItemAudioApdapter.audios.size()-1){
                   Audio audio = CardItemAudioApdapter.audios.get(0);
                   txTitleAudio.setText(audio.getTitle());
                   txReaderNameAudio.setText(audio.getReaderName());
                   img_Audio.setImageResource(R.drawable.background_card_item);
                   index = 0;
               }else{
                   Audio audio = CardItemAudioApdapter.audios.get(index++);
                   txTitleAudio.setText(audio.getTitle());
                   txReaderNameAudio.setText(audio.getReaderName());
                   img_Audio.setImageResource(R.drawable.background_card_item);
                   index = index;
               }*/
           }
       });
    }
    private void onClickPreviousAudio(){

    }
    private void onClickShowContextAudio(){
        img_Bnt_content_Audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlayAudioActivity.this, "Show context", Toast.LENGTH_SHORT).show();
            }
        });
    }
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

    public void setSeekBarAudio(){
        seekBarAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                player.seekTo(progress);
                rotate.setDuration(player.getDuration() - progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
