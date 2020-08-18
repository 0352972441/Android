package com.example.revenuemanagement.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.revenuemanagement.Models.Category;
import com.example.revenuemanagement.R;

import java.util.ArrayList;
import java.util.List;

public class SpinerAdapter extends BaseAdapter {
    private ArrayList<Category> listCategory;

    public SpinerAdapter(ArrayList<Category> listCategory) {
        this.listCategory = listCategory;
    }

    @Override
    public int getCount() {
        return listCategory.size();
    }

    @Override
    public Object getItem(int position) {
        return listCategory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spiner_view_category, parent, false);
        if(holder == null){
            holder = new ViewHolder();
            holder.image =(ImageView)view.findViewById(R.id.mimagecategory);
            holder.titleCategory = (TextView)view.findViewById(R.id.mtitlecategory);
            view.setTag(holder);
        }
        holder = (ViewHolder)view.getTag();
        holder.image.setImageResource(listCategory.get(position).getImage());
        holder.titleCategory.setText(listCategory.get(position).getTitle());
        return view;
    }

    static class ViewHolder{
        ImageView image;
        TextView titleCategory;
    }
}
