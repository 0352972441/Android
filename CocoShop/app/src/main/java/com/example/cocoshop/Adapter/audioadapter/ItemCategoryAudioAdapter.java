package com.example.cocoshop.Adapter.audioadapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.Models.audiomodels.Audio;
import com.example.cocoshop.Models.audiomodels.Category;
import com.example.cocoshop.R;
import com.example.cocoshop.listener.Listener;

import java.util.ArrayList;
import java.util.List;

public class ItemCategoryAudioAdapter extends RecyclerView.Adapter<ItemCategoryAudioAdapter.ViewHolder> {
    private Listener cardItemCategoryListener;
    private boolean flagItemSelected = false;
    public void setCardItemCategoryListener(Listener cardItemCategoryListener) {
        this.cardItemCategoryListener = cardItemCategoryListener;
    }

    @NonNull
    @Override
    public ItemCategoryAudioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new ItemCategoryAudioAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemCategoryAudioAdapter.ViewHolder holder, final int position) {
            holder.txLabelItemCategoryAudio.setText(Category.values()[position].toString());
        if(!flagItemSelected){
            holder.background_Card_Item_Category.setBackgroundResource(R.color.colorPrimary);
            flagItemSelected = true;
        }else{
            holder.background_Card_Item_Category.setBackgroundResource(R.color.white);
        }
            holder.cardItemCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardItemCategoryListener.listener(position);

                }
            });
    }

    @Override
    public int getItemCount() {
        return Category.values().length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardItemCategory;
        private TextView txLabelItemCategoryAudio;
        private RelativeLayout background_Card_Item_Category;
        public ViewHolder(@NonNull View view) {
            super(view);
            cardItemCategory = (CardView) view.findViewById(R.id.card_item_category);
            txLabelItemCategoryAudio = (TextView)view.findViewById(R.id.label_category_audio);
            background_Card_Item_Category = (RelativeLayout)view.findViewById(R.id.background_item_card_category);
        }
    }
}