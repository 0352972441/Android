package com.example.lab3.CustomListView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab3.Models.Actor;
import com.example.lab3.R;

import java.util.ArrayList;

public class Custom_BaseAdapter_ListView extends BaseAdapter {
    Context mContext;
    ArrayList<Actor> listActor;

    public Custom_BaseAdapter_ListView(Context mContext, ArrayList<Actor> listActor) {
        this.mContext = mContext;
        this.listActor = listActor;
    }

    @Override
    public int getCount() {
        return listActor.size();
    }

    @Override
    public Object getItem(int position) {
        return listActor.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHoder hoder = null;
        if(view == null){
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            view = inflater.inflate(R.layout.custom_list_view_item, null);
            hoder = new ViewHoder();
            hoder.mImage = view.findViewById(R.id.image);
            hoder.mName = view.findViewById(R.id.name);
            view.setTag(hoder);
        }else{
            hoder = (ViewHoder)view.getTag();
        }
        hoder.mImage.setImageResource(listActor.get(position).getImage());
        hoder.mName.setText(listActor.get(position).getName());
        return view;
    }

    public static class ViewHoder{
        TextView mName;
        ImageView mImage;
    }
}
