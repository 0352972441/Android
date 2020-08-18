package com.example.revenuemanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revenuemanagement.Models.Category;
import com.example.revenuemanagement.Models.Image;
import com.example.revenuemanagement.R;

import java.util.ArrayList;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.ViewHolder> {
    private ArrayList<Image> categories = new ArrayList<>();
    private ItemOnListener listener;

    public ArrayList<Image> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Image> categories) {
        this.categories = categories;
    }

    public ItemOnListener getListener() {
        return listener;
    }

    public void setListener(ItemOnListener listener) {
        this.listener = listener;
    }

    public Category_Adapter(ArrayList<Image> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_category,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(categories != null){
            holder.mimage.setImageResource(categories.get(position).getImage());
            holder.mimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.listener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView mimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardview);
            mimage = (ImageView)itemView.findViewById(R.id.mImageCategory);
        }
    }
}
