package com.example.cocoshop.adapter;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.R;
import com.example.cocoshop.animation.Animations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MyFavoriteAdapter extends RecyclerView.Adapter<MyFavoriteAdapter.ViewHolder>{
    private ArrayList<Map<String,Object>> myFavorite;

    public MyFavoriteAdapter(ArrayList<Map<String, Object>> myFavorite) {
        this.myFavorite = myFavorite;
    }



    @NonNull
    @Override
    public MyFavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyFavoriteAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vocabulary,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyFavoriteAdapter.ViewHolder holder, final int position) {
        if (myFavorite != null){
            final Map<String,Object> item = myFavorite.get(position);
            holder.txVocabulary.setText(item.get("vocabulary").toString());
            holder.txSpelling.setText(item.get("spelling").toString());
            holder.txMean.setText(item.get("mean").toString());
            holder.cardVocabulary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.cardVocabulary.startAnimation(new Animations(holder.cardVocabulary.getContext()).zoonIn(100));
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
        return myFavorite.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //private ImageView imgSpeaker;
        private CardView cardVocabulary;
        private TextView txVocabulary,txMean,txSpelling;

        public ViewHolder(@NonNull View view) {
            super(view);
            cardVocabulary = (CardView)view.findViewById(R.id.card_vocabulary);
            //imgSpeaker = (ImageView) view.findViewById(R.id.img_sound);
            txVocabulary = (TextView)view.findViewById(R.id.tx_vocabulary);
            txMean = (TextView)view.findViewById(R.id.tx_mean);
            txSpelling = (TextView) view.findViewById(R.id.tx_spelling);
        }
    }
}
