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

import com.example.cocoshop.Models.CardHomeModel.Card;
import com.example.cocoshop.Models.topicsmodel.Topic;
import com.example.cocoshop.R;
import com.example.cocoshop.listener.Listener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.ViewHolder> {
    private Listener cardListener;
    private ArrayList<Topic> card = new ArrayList<>();
    private static View view;
    public CardItemAdapter(ArrayList<Topic> card) {
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
            //Map<String,Object> item = card.get(position);
            holder.tx_Level.setText(card.get(position).getLevel().toString());
            holder.mTxName.setText(card.get(position).getName().toString());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardListener.listener(position);
                }
            });
            Picasso.with(holder.cardView.getContext()).load(card.get(position).getUrlImage().toString()).into(holder.imgBackground);
        }
    }

    @Override
    public int getItemCount() {
        return card.size();
    }
    public void setCardListener(Listener cardListener) {
        this.cardListener = cardListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private FrameLayout mFrameLayout;
        private TextView tx_Level,mtxCountCard,mTxName;
        private ImageView imgBackground;
        private CardView cardView;
        public ViewHolder(@NonNull View view) {
            super(view);
            mFrameLayout = (FrameLayout) view.findViewById(R.id.cardBackground);

            tx_Level = (TextView)view.findViewById(R.id.tx_level);
            //mtxCountCard = (TextView)view.findViewById(R.id.txCountCard);
            mTxName = (TextView)view.findViewById(R.id.tx_name);

            imgBackground = (ImageView)view.findViewById(R.id.img_background);
            cardView = (CardView)view.findViewById(R.id.cardview);
        }
    }
}
