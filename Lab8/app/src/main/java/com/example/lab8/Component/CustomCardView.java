package com.example.lab8.Component;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8.Models.Scenery;
import com.example.lab8.R;

import java.util.ArrayList;

public class CustomCardView extends RecyclerView.Adapter<CustomCardView.ViewHolder>{
    private ArrayList<Scenery> listScenery;

    public CustomCardView(ArrayList<Scenery> listScenery) {
        this.listScenery = listScenery;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_custom,parent,false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView textView = (TextView)cardView.findViewById(R.id.mTitle);
        ImageView imageView = (ImageView)cardView.findViewById(R.id.mImage);
        textView.setText(listScenery.get(position).getTitle());
        imageView.setImageResource(listScenery.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return listScenery.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }
    }

}
