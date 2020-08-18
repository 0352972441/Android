package com.example.revenuemanagement.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revenuemanagement.Models.Colors;
import com.example.revenuemanagement.R;


import java.util.ArrayList;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {
    private ArrayList<Colors> listColor;
    private ItemOnListener listener;

    public ColorAdapter(ArrayList<Colors> listColor) {
        this.listColor = listColor;
    }

    public void setListener(ItemOnListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_color,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(listColor != null){
            holder.mcolor.setBackgroundColor(listColor.get(position).getColor());
            holder.mcolor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.listener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listColor.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mcolor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mcolor = (TextView)itemView.findViewById(R.id.mcolor);
        }
    }
}
