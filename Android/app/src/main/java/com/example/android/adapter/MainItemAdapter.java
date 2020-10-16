package com.example.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.listener.Listener;
import com.example.android.models.MainItem;


import java.util.ArrayList;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.ViewHolder>{
    private ArrayList<MainItem> items;
    private Listener listenerMainItem;
    public MainItemAdapter(ArrayList<MainItem> items) {
        this.items = items;
    }

    public void setListenerMainItem(Listener listenerMainItem) {
        this.listenerMainItem = listenerMainItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_main_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(items!= null){
            MainItem item = items.get(position);
            holder.txTitle.setText(item.getTitle());
            holder.imgImage.setImageResource(item.getImage());
            holder.mainItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerMainItem.lintener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txTitle;
        private ImageView imgImage;
        private RelativeLayout mainItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txTitle = (TextView)itemView.findViewById(R.id.tx_title);
            imgImage = (ImageView)itemView.findViewById(R.id.img_item);
            mainItem = (RelativeLayout)itemView.findViewById(R.id.main_item);
        }
    }
}