package com.example.lab8.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab8.R;
import com.example.lab8.models.Music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Lesson2Activity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private TextView txReamain, txTotal;
    private ImageView imgPlay,imgStop,imgPause;
    private ListView listMusic;
    private List<Music> musicList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);
        mediaPlayer = MediaPlayer.create(this,R.raw.sound);
        txReamain = findViewById(R.id.tx_remain);
        txTotal = findViewById(R.id.tx_total);
        imgPlay = findViewById(R.id.img_play);
        imgStop = findViewById(R.id.img_stop);
        imgPause = findViewById(R.id.img_pause);
        seekBar = findViewById(R.id.seek_bar);
        listMusic = findViewById(R.id.list_item);
        musicList = music();
        Toast.makeText(this, mediaPlayer.getDuration()+"", Toast.LENGTH_SHORT).show();
        seekBar.setMax(mediaPlayer.getDuration());
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                long sencond = mediaPlayer.getCurrentPosition()/1000L;
                long sec = sencond% 60;
                long minute = (sencond/3600)/60;
                String timeTotal = String.format(Locale.getDefault(),"%02d:%02d",minute,sec);
                txReamain.setText(timeTotal);
                sencond = (mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition())/1000L;
                /*hour = sencond / 60;*/
                sec = sencond% 60;
                minute = (sencond/3600)/60;
                txTotal.setText(String.valueOf(String.format(Locale.getDefault(),"%02d:%02d",minute,sec)));
                handler.postDelayed(this,1000);
            }
        });
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
        imgPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
        imgStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                seekBar.setProgress(0);
            }
        });

        ArrayAdapter<Music> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,musicList);
        listMusic.setAdapter(adapter);
        listMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(Lesson2Activity.this,musicList.get(position).getSound());
                mediaPlayer.start();
            }
        });
    }

    private List<Music> music(){
        List<Music> list = new ArrayList<>();
        list.add(new Music("Iphone", R.raw.sound_3));
        list.add(new Music("Đánh mất em", R.raw.sound_2));
        list.add(new Music("Senorita", R.raw.sound_4));
        return list;
    }
}

