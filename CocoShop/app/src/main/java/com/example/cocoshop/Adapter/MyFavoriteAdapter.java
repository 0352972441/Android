package com.example.cocoshop.Adapter;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.R;
import com.example.cocoshop.listener.Listener;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MyFavoriteAdapter extends RecyclerView.Adapter<MyFavoriteAdapter.ViewHolder>{
    private List<Map<String,Object>> myFavorite;

    public MyFavoriteAdapter(List<Map<String, Object>> myFavorite) {
        this.myFavorite = myFavorite;
    }



    @NonNull
    @Override
    public MyFavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyFavoriteAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vocabulary,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyFavoriteAdapter.ViewHolder holder, final int position) {
        if (myFavorite != null){
            final Map<String,Object> item = myFavorite.get(position);
            holder.txVocabulary.setText(item.get("vocabulary").toString());
            holder.imgSpeaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer media = new MediaPlayer();
                    try {
                        media.setDataSource(item.get("read").toString());
                        media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {

                            }
                        });
                        media.prepare();
                        media.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgSpeaker;
        private TextView txVocabulary;

        public ViewHolder(@NonNull View view) {
            super(view);
            imgSpeaker = (ImageView) view.findViewById(R.id.img_sound);
            txVocabulary = (TextView)view.findViewById(R.id.tx_vocabulary);
        }
    }
}
