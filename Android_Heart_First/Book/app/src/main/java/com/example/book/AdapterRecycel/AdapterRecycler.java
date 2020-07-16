package com.example.book.AdapterRecycel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book.Data.Book;
import com.example.book.ListenerFragment.Listener;
import com.example.book.R;

import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {
    private ArrayList<Book> listDataBook;
    private Listener listener;
    public AdapterRecycler(ArrayList<Book> listDataBook) {
        this.listDataBook = listDataBook;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_adapter,parent,false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        TextView textView = (TextView)cardView.findViewById(R.id.info_text);
        ImageView imageView = (ImageView)cardView.findViewById(R.id.info_image);
        textView.setText(listDataBook.get(position).getTitle());
        imageView.setImageResource(listDataBook.get(position).getImage());
        imageView.setContentDescription(Integer.toString(listDataBook.get(position).getTitle()));
        cardView.setOnClickListener(new RecyclerView.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDataBook.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }
    }
}
