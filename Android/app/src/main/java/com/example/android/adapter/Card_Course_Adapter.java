package com.example.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.entity.CourseEntity;

import java.util.List;

public class Card_Course_Adapter extends RecyclerView.Adapter<Card_Course_Adapter.ViewHolder> {
    private List<CourseEntity> course;

    public Card_Course_Adapter(List<CourseEntity> course) {
        this.course = course;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_course,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(course != null){
            holder.txTitle.setText(course.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return course.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txTitle;
        public ViewHolder(@NonNull View view) {
            super(view);
            txTitle = (TextView)view.findViewById(R.id.tx_title);
        }
    }
}
