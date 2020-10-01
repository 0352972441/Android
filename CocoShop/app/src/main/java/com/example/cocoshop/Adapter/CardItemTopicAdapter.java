package com.example.cocoshop.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.Models.topicsmodel.Levels;
import com.example.cocoshop.Models.topicsmodel.Topic;
import com.example.cocoshop.R;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CardItemTopicAdapter extends RecyclerView.Adapter<CardItemTopicAdapter.ViewHolder>{
    private List<Topic> topics = new ArrayList<>();
    public CardItemTopicAdapter(List<Topic> topics) {
        this.topics = topics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_topic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(topics != null){
            holder.mtxTitle.setText(topics.get(position).getName());
            int levelTopic = 0;
            switch(topics.get(position).getLevel()){
                case BEGINER:
                    levelTopic = R.string.label_level_beginer;
                    break;
                case HIGHTBEGINER:levelTopic = R.string.label_level_beginer;break;
                case LOWINTERMADIATE:levelTopic = R.string.label_level_low_intermadiate;break;
                case INTERMADIATE:levelTopic = R.string.label_level_intermadicate;break;
                case HIGHINTERMADIATE:levelTopic = R.string.label_level_high_intermadiate;break;
                case LOWADVANCED:levelTopic = R.string.label_level_low_advanced;break;
                case ADVANCED:levelTopic = R.string.label_level_advanced;break;
                case HIGHADVANCED:levelTopic = R.string.label_level_high_advanced;break;
            }
            holder.mtxLevel.setText(levelTopic);
            holder.mCardItemTopicBackground.setBackgroundColor(Color.CYAN);
        }
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mtxTitle,mtxLevel;
        private RelativeLayout mCardItemTopicBackground;
        ViewHolder(@NonNull View view) {
            super(view);
            mCardItemTopicBackground = (RelativeLayout)view.findViewById(R.id.cardItemTopicBackground);
            mtxLevel = (TextView)view.findViewById(R.id.txlevel);
            mtxTitle = (TextView)view.findViewById(R.id.txtitle);
        }
    }
}
