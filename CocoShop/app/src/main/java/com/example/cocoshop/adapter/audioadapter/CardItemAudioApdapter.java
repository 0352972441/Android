package com.example.cocoshop.adapter.audioadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.models.audiomodels.Audio;
import com.example.cocoshop.R;
import com.example.cocoshop.listener.Listener;

import java.util.ArrayList;
import java.util.List;

public class CardItemAudioApdapter extends RecyclerView.Adapter<CardItemAudioApdapter.ViewHolder> {
    private Listener playAudioListener;
    private List<Audio> audios = new ArrayList<>();
    public CardItemAudioApdapter(List<Audio> audios) {
        this.audios = audios;
    }

    public void setPlayAudioListener(Listener playAudioListener) {
        this.playAudioListener = playAudioListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_audio,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(audios != null){
            holder.txTitleAudio.setText(audios.get(position).getSound().getTitle());
            holder.txReaderName.setText(audios.get(position).getReaderName());
            holder.backgroundCardItem.setBackgroundResource(R.drawable.background_card_item);
            holder.bntPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playAudioListener.listener(position);
                }
            });
        }
    }

    public Audio getItemPosition(int position){
        return audios.get(position);
    }

    @Override
    public int getItemCount() {
        return audios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardItemAudio;
        private RelativeLayout backgroundCardItem;
        private TextView txTitleAudio,txReaderName;
        private ImageView imgReader,bntPlay;
        public ViewHolder(@NonNull View view) {
            super(view);
            cardItemAudio = (CardView) view.findViewById(R.id.cardItemAudio);
            txTitleAudio = (TextView)view.findViewById(R.id.txTitleAudio);
            txReaderName = (TextView)view.findViewById(R.id.readerName);
            cardItemAudio = (CardView)view.findViewById(R.id.cardItemAudio);
            backgroundCardItem = (RelativeLayout)view.findViewById(R.id.background_audio);
            imgReader = (ImageView)view.findViewById(R.id.imgReader);
            bntPlay = (ImageView)view.findViewById(R.id.imgPlay);
        }
    }
}
