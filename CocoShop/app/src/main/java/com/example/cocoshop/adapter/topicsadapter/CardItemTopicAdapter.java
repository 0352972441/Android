package com.example.cocoshop.adapter.topicsadapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.database.entity.TopicProgressEntity;
import com.example.cocoshop.database.responsive.TopicProgressResponsive;
import com.example.cocoshop.listener.ListenerTopicProgress;
import com.example.cocoshop.models.topicsmodel.Topic;
import com.example.cocoshop.R;
import com.example.cocoshop.animation.Animations;
import com.example.cocoshop.listener.Listener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardItemTopicAdapter extends RecyclerView.Adapter<CardItemTopicAdapter.ViewHolder>{
    private List<Topic> topics = new ArrayList<>();
    private TopicProgressResponsive responsive;
    private TopicProgressEntity progressEntity;

    private int partOnSentences;
    private int currentProgressive;

    public CardItemTopicAdapter(List<Topic> topics) {
        this.topics = topics;
    }
    /*private Listener listenerItem;

    public void setListenerItem(Listener listenerItem) {
        this.listenerItem = listenerItem;
    }*/
    private ListenerTopicProgress listenerItem;

    public void setOnClickTopic(ListenerTopicProgress listenerItem) {
        this.listenerItem = listenerItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_topic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(topics != null){
            int levelTopic = 0;
            responsive = new TopicProgressResponsive(holder.cardItem.getContext());
            final Topic topic = topics.get(position);
            final int topicId = (int)topic.getTopicCode();

            holder.mtxTitle.setText(topic.getName());
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
                    holder.cardItem.startAnimation(new Animations(holder.cardItem.getContext()).zoomOut(500));
                    listenerItem.onClickListener(position,(int)topic.getTopicCode());

                    // Xử lý tiến độ đang học của topic
                    // Nếu Bài học chưa được thêm vào thì thêm vào và vị là bằng 0
                    if(responsive.getProgressive(topicId) == null){
                        TopicProgressEntity entity = new TopicProgressEntity(topicId,0);
                        responsive.insert(entity);
                    }
                }
            });
            if(responsive.getProgressive(topicId)!= null){
                // Nếu đã được thêm vào thì lấy vị trí hiện tại đang học
                progressEntity = responsive.getProgressive((int)topic.getTopicCode());
                partOnSentences = (topic.getVocabularies().size())/10;
                if(partOnSentences != 0) {
                    currentProgressive = progressEntity.getPosition() == 0 ? 0 : (progressEntity.getPosition()+1) / partOnSentences;
                }
                // Tiến độ bài học // Thanh tiến độ chia ra 10 phần
                // Tổng câu chia 10 được bao nhiêu câu trên phần
                // Lấy câu đã học chia cho phần trên câu thì được tiến độ hiện tại
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,currentProgressive);
                holder.mTopicProgress.setLayoutParams(params);
            }else{
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,0);
                holder.mTopicProgress.setLayoutParams(params);
            }
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
        private LinearLayout mTopicProgress;
        ViewHolder(@NonNull View view) {
            super(view);
            mImgBackgroundCardItemTopics = (ImageView)view.findViewById(R.id.img_background_card_item_topics);
            cardItem = (FrameLayout)view.findViewById(R.id.card_item);
            mTopicProgress = view.findViewById(R.id.topic_progress);
            //mCardItemTopicBackground = (RelativeLayout)view.findViewById(R.id.cardItemTopicBackground);
            mtxLevel = (TextView)view.findViewById(R.id.txlevel);
            mtxTitle = (TextView)view.findViewById(R.id.txtitle);

        }
    }
}
