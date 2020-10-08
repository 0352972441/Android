package com.example.cocoshop.Adapter.topicsadapter;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cocoshop.Models.vocabularysmodel.Vocabulary;
import com.example.cocoshop.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPagerVocabulary extends RecyclerView.Adapter<ViewPagerVocabulary.ViewHolder> {
    private ArrayList<Vocabulary> vocabularies = new ArrayList<>();
    public ViewPagerVocabulary(ArrayList<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vocabulary,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(vocabularies!= null){
            Map<String,Object> item = (Map<String, Object>) vocabularies.get(position);
            holder.txSpelling.setText(item.get("spelling").toString());
            holder.txVocabulary.setText(item.get("vocabulary").toString());
            final MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(item.get("read").toString());
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
                mediaPlayer.prepare();
                holder.imgSpeaker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        Log.d("Size:",""+vocabularies.size());
        return vocabularies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgSpeaker,imgDescription;
        private TextView txVocabulary,txSpelling;
        public ViewHolder(@NonNull View view) {
            super(view);
            txSpelling = (TextView)view.findViewById(R.id.spelling);
            txVocabulary = (TextView)view.findViewById(R.id.vocabulary);
            imgDescription = (ImageView)view.findViewById(R.id.img_description);
            imgSpeaker = (ImageView)view.findViewById(R.id.speaker);
        }
    }
}
