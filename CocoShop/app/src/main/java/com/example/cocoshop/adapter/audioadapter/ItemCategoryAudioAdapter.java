package com.example.cocoshop.adapter.audioadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.models.audiomodels.Category;
import com.example.cocoshop.R;
import com.example.cocoshop.listener.Listener;

public class ItemCategoryAudioAdapter extends RecyclerView.Adapter<ItemCategoryAudioAdapter.ViewHolder> {
    private Listener cardItemCategoryListener;
    private boolean flagItemSelected = false;
    private Context context;

    public ItemCategoryAudioAdapter(Context context) {
        this.context = context;
    }

    public void setCardItemCategoryListener(Listener cardItemCategoryListener) {
        this.cardItemCategoryListener = cardItemCategoryListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txLabelItemCategoryAudio.setText(Category.values()[position].toString());
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



    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardItemCategory;
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

/*private CardView cardItemCategory;
        private TextView txLabelItemCategoryAudio;
        private RelativeLayout background_Card_Item_Category;
        public ViewHolder(@NonNull View view) {
            super(view);
            cardItemCategory = (CardView) view.findViewById(R.id.card_item_category);
            txLabelItemCategoryAudio = (TextView)view.findViewById(R.id.label_category_audio);
            background_Card_Item_Category = (RelativeLayout)view.findViewById(R.id.background_item_card_category);

            //
            if(!flagItemSelected){
            holder.background_Card_Item_Category.setBackgroundResource(R.color.colorPrimary);
            flagItemSelected = true;
        }else{
            holder.background_Card_Item_Category.setBackgroundResource(R.color.white);
        }
        }*/