package com.example.myswipe.Data;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myswipe.Activity.Activity_Detail;
import com.example.myswipe.Listenner.Listener;
import com.example.myswipe.R;

import java.util.ArrayList;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    private ArrayList<Pizza> listData;
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public CaptionedImagesAdapter(ArrayList<Pizza> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;
        TextView name = (TextView)cardView.findViewById(R.id.info_text);
        ImageView imageView = (ImageView)cardView.findViewById(R.id.info_image);
        name.setText(listData.get(position).getName());
        imageView.setContentDescription(listData.get(position).getName());
        imageView.setImageResource(listData.get(position).getImage());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onclick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

     public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

         public ViewHolder(CardView cardView) {// Chỉ định kiểu dữ liệu mà Recyler cần hiển thị() vì vậy ta chỉ định viewholder chứa ở đây là cardView
             super(cardView);
             this.cardView = cardView;
         }
     }
}
