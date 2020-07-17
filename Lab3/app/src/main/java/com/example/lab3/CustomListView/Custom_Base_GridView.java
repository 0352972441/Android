package com.example.lab3.CustomListView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lab3.Models.Provider;
import com.example.lab3.R;

import java.util.ArrayList;

public class Custom_Base_GridView extends BaseAdapter {
    private Context context;
    private ArrayList<Provider> listData;

    public Custom_Base_GridView(Context context, ArrayList listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder hoder = null;
        if(convertView == null){
            hoder = new ViewHoder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView =  inflater.inflate(R.layout.custom_grid_view_item, null);
            hoder.mImage = (ImageView)convertView.findViewById(R.id.image);
            hoder.mName = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(hoder);
        }else{
            hoder = (ViewHoder) convertView.getTag();
        }
        hoder.mName.setText(listData.get(position).getName());
        hoder.mImage.setImageResource(listData.get(position).getImage());
        return convertView;
    }

    public static class ViewHoder{
        ImageView mImage;
        TextView mName;
    }
}
