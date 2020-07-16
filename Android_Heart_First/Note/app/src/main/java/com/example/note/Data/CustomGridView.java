package com.example.note.Data;
import com.example.note.R;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomGridView extends BaseAdapter {
    private ArrayList<Note> note;
    private Activity activity;

    public CustomGridView(ArrayList<Note> note, Activity activity) {
        this.note = note;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return note.size();
    }

    @Override
    public Object getItem(int position) {
        return note.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null){
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.activity_custom_grid_view,parent,false);
            holder = new Holder();
            holder.text = (TextView) convertView.findViewById(R.id.gridText);
            holder.imageView = (ImageView) convertView.findViewById(R.id.gridImage);
            convertView.setTag(holder);
        }else{
            holder =(Holder) convertView.getTag();
        }
        Note data = this.note.get(position);
        holder.text.setText(data.getTitle());
        holder.imageView.setImageResource(data.getGridImage());
        return convertView;
    }
    static class Holder{
        TextView text;
        ImageView imageView;
    }
}
