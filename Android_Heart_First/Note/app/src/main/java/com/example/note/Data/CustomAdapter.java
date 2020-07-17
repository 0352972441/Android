package com.example.note.Data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import com.example.note.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends BaseAdapter {
    String[] result;
    Activity activity;
    int image;

    public CustomAdapter(String[] result, Activity activity, int image) {
        this.result = result;
        this.activity = activity;
        this.image = image;
    }

    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // Trả về vị trí nội dung chứa list view
        return null;
    }

    @Override
    public long getItemId(int position) {
        // trả về vị trí mảng image
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_view_image, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.content);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iconList);
            convertView.setTag(holder);
        }
        ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.title.setText(result[position]);
        viewHolder.imageView.setImageResource(image);
        return convertView;
    }
    static class ViewHolder{
        TextView title;
        ImageView imageView;
    }
}
