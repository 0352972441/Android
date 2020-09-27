package com.example.cocoshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cocoshop.Models.Topic;
import com.example.cocoshop.R;

import java.util.ArrayList;
import java.util.List;

public class HomeNewApdapter extends RecyclerView.Adapter<HomeNewApdapter.ViewHolder> {

    private List<Topic> topics = new ArrayList<>();
    private static View view;
    public HomeNewApdapter(List<Topic> topics) {
        this.topics = topics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_new_home,parent,false);
        this.view = view;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(topics != null){
            holder.mtxTitle.setText(topics.get(position).getTitle());
            //Glide.with(view.getContext()).load(topics.get(position).getImgUrl()).into(holder.ca);
        }
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardViewBackground;
        private TextView mtxTitle;
        public ViewHolder(@NonNull View view) {
            super(view);
            cardViewBackground = (CardView) view.findViewById(R.id.imgBackground);
            mtxTitle = (TextView)view.findViewById(R.id.txTitleTopic);
        }
    }
}
