package com.example.designgraphics.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.designgraphics.Data.Book;
import com.example.designgraphics.R;

import java.util.ArrayList;

public class CardViewCustomes extends RecyclerView.Adapter<CardViewCustomes.ViewHolder> {
    ArrayList<Book> bookArrayList = new ArrayList<>();

    public CardViewCustomes(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView)cardView.findViewById(R.id.imageview);
        TextView textView = (TextView)cardView.findViewById(R.id.textview);
        imageView.setImageResource(bookArrayList.get(position).getImage());
        imageView.setContentDescription(bookArrayList.get(position).getTite());
        textView.setText(bookArrayList.get(position).getTite());
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }
    }
}
