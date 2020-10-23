package com.example.cocoshop.Adapter.topicsadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.Models.topicsmodel.Topic;
import com.example.cocoshop.R;
import com.example.cocoshop.listener.Listener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardItemTopicAdapter extends RecyclerView.Adapter<CardItemTopicAdapter.ViewHolder>{
    private List<Topic> topics = new ArrayList<>();
    public CardItemTopicAdapter(List<Topic> topics) {
        this.topics = topics;
    }
    private Listener listenerItem;

    public void setListenerItem(Listener listenerItem) {
        this.listenerItem = listenerItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_topic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(topics != null){
            Topic topic = topics.get(position);
            holder.mtxTitle.setText(topic.getName());
            int levelTopic = 0;
            switch(topic.getLevel()){
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
            Picasso.with(holder.mtxLevel.getContext()).load(topic.getUrlImage()).error(R.drawable.background_card_item).into(holder.mImgBackgroundCardItemTopics);
            holder.cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerItem.listener(position);
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return topics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mtxTitle,mtxLevel;
        //private RelativeLayout mCardItemTopicBackground;
        private FrameLayout cardItem;
        private ImageView mImgBackgroundCardItemTopics;
        ViewHolder(@NonNull View view) {
            super(view);
            mImgBackgroundCardItemTopics = (ImageView)view.findViewById(R.id.img_background_card_item_topics);
            cardItem = (FrameLayout)view.findViewById(R.id.card_item);
            //mCardItemTopicBackground = (RelativeLayout)view.findViewById(R.id.cardItemTopicBackground);
            mtxLevel = (TextView)view.findViewById(R.id.txlevel);
            mtxTitle = (TextView)view.findViewById(R.id.txtitle);
        }
    }
}
