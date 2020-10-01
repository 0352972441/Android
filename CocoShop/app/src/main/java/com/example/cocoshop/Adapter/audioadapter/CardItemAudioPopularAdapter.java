package com.example.cocoshop.Adapter.audioadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.Models.audiomodels.Audio;
import com.example.cocoshop.R;
import com.example.cocoshop.listener.Listener;

import java.util.ArrayList;
import java.util.List;

public class CardItemAudioPopularAdapter extends RecyclerView.Adapter<CardItemAudioPopularAdapter.ViewHolder> {
    private Listener playAudioListener;
    private List<Audio> audioPopulars = new ArrayList<>();
    public CardItemAudioPopularAdapter(List<Audio> audioPopulars) {
        this.audioPopulars = audioPopulars;
    }

    public void setPlayAudioListener(Listener playAudioListener) {
        this.playAudioListener = playAudioListener;
    }

    @NonNull
    @Override
    public CardItemAudioPopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_audio_popular,parent,false);
        return new CardItemAudioPopularAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardItemAudioPopularAdapter.ViewHolder holder, final int position) {
        if(audioPopulars != null){
            Audio audio = audioPopulars.get(position);
           holder.txTitleAudioPopular.setText(audio.getTitle());
           holder.txReaderNameAudioPopular.setText(audio.getReaderName());
           holder.backgroundCardItemAudioPopular.setBackgroundResource(R.color.grey);
           holder.imgAudioPolular.setImageResource(R.drawable.background_card_item);
           holder.imgPlayAudioPopular.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   playAudioListener.listener(position);
               }
           });
        }
    }

    @Override
    public int getItemCount() {
        return audioPopulars.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardItemAudioPopular;
        private RelativeLayout backgroundCardItemAudioPopular;
        private TextView txTitleAudioPopular,txReaderNameAudioPopular;
        private ImageView imgAudioPolular,imgPlayAudioPopular;
        public ViewHolder(@NonNull View view) {
            super(view);
            cardItemAudioPopular = (CardView) view.findViewById(R.id.card_item_popular);
            txTitleAudioPopular = (TextView)view.findViewById(R.id.title_audio_popular);
            txReaderNameAudioPopular = (TextView)view.findViewById(R.id.reader_name_audio_popular);
            backgroundCardItemAudioPopular = (RelativeLayout) view.findViewById(R.id.background_card_item_audio_popular);
            imgPlayAudioPopular = (ImageView) view.findViewById(R.id.img_play_popular);
            imgAudioPolular = (ImageView)view.findViewById(R.id.img_audio_popular);
        }
    }
}