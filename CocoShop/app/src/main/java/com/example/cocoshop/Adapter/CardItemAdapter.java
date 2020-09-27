package com.example.cocoshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.Models.Card;
import com.example.cocoshop.Models.Topic;
import com.example.cocoshop.R;
import com.example.cocoshop.listener.Listener;

import java.util.ArrayList;
import java.util.List;

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.ViewHolder> {
    private Listener listener;
    private List<Card> card = new ArrayList<>();
    private static View view;
    public CardItemAdapter(List<Card> card) {
        this.card = card;
    }

    @NonNull
    @Override
    public CardItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_home,parent,false);
        this.view = view;
        return new CardItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardItemAdapter.ViewHolder holder, final int position) {
        if(card != null){
            holder.mtxTitleCard.setText(card.get(position).getTitle());
            holder.imgLogo.setImageResource(card.get(position).getLogo());
            holder.mtxCountCard.setText(card.get(position).getCountCard());
            /*holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.listener(position);
                }
            });*/
            //Glide.with(view.getContext()).load(topics.get(position).getImgUrl()).into(holder.ca);
        }
    }

    @Override
    public int getItemCount() {
        return card.size();
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private FrameLayout mFrameLayout;
        private TextView mtxTitleCard,mtxCountCard;
        private ImageView imgLogo;
        private CardView cardView;
        public ViewHolder(@NonNull View view) {
            super(view);
            mFrameLayout = (FrameLayout) view.findViewById(R.id.cardBackground);
            mtxTitleCard = (TextView)view.findViewById(R.id.txTitleCard);
            mtxCountCard = (TextView)view.findViewById(R.id.txCountCard);
            imgLogo = (ImageView)view.findViewById(R.id.imgLogo);
            cardView = (CardView)view.findViewById(R.id.cardview);
        }
    }
}
