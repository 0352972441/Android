package com.example.revenuemanagement.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revenuemanagement.Models.Colors;
import com.example.revenuemanagement.R;
import com.example.revenuemanagement.adapter.ColorAdapter;
import com.example.revenuemanagement.adapter.ItemOnListener;

import java.util.ArrayList;

public class ShowColorPicker {
    private static AlertDialog.Builder dialog;
    private static int color;
    public static void showColorPicker(final Context context){
            final ArrayList<Colors> listColor = getAll();
            /*color = listColor.get(0).getColor();
            ExpenditureDialog.setBackgroundColor(color);*/
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_color,null,false);
//            final ImageView showColor = (ImageView)view.findViewById(R.id.mshowColor);
            final CardView showColor = (CardView)view.findViewById(R.id.mshowColor);
            RecyclerView mrecyclerColor = (RecyclerView)view.findViewById(R.id.recycler_view_color);
            ColorAdapter colorAdapter = new ColorAdapter(listColor);
            mrecyclerColor.setAdapter(colorAdapter);
            mrecyclerColor.setLayoutManager(new GridLayoutManager(view.getContext(),4));
            dialog = new AlertDialog.Builder(context);
            colorAdapter.setListener(new ItemOnListener() {
                @Override
                public void listener(int position) {
                    showColor.setBackgroundColor(listColor.get(position).getColor());
                    setColor(listColor.get(position).getColor());
                }
            });
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ExpenditureDialog.setBackgroundColor(getColor());
                    dialog.dismiss();
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.setView(view);
            dialog.show();
    }


    public static int getColor() {
        return color;
    }

    public static void setColor(int color) {
        ShowColorPicker.color = color;
    }

    private static ArrayList<Colors> getAll(){
        ArrayList<Colors> listColors = new ArrayList<>();
        listColors.add(new Colors(Color.BLACK));
        listColors.add(new Colors(Color.YELLOW));
        listColors.add(new Colors(Color.BLUE));
        listColors.add(new Colors(Color.CYAN));
        listColors.add(new Colors(Color.WHITE));
        listColors.add(new Colors(Color.RED));
        listColors.add(new Colors(Color.DKGRAY));
        listColors.add(new Colors(Color.GRAY));
        listColors.add(new Colors(Color.GREEN));
        listColors.add(new Colors(Color.LTGRAY));
        listColors.add(new Colors(Color.MAGENTA));
        listColors.add(new Colors(Color.TRANSPARENT));
        listColors.add(new Colors(Color.parseColor("#8fbc8f")));
        listColors.add(new Colors(Color.parseColor("#2f4f4f")));
        listColors.add(new Colors(Color.parseColor("#daa520")));
        listColors.add(new Colors(Color.parseColor("#f0e68c")));
        listColors.add(new Colors(Color.parseColor("#e6e6fa")));
        listColors.add(new Colors(Color.parseColor("#fff0f5")));
        listColors.add(new Colors(Color.parseColor("#add8e6")));
        listColors.add(new Colors(Color.parseColor("#90ee90")));
        listColors.add(new Colors(Color.parseColor("#778899")));
        listColors.add(new Colors(Color.parseColor("#3cb371")));
        listColors.add(new Colors(Color.parseColor("#800000")));
        listColors.add(new Colors(Color.parseColor("#ff0000")));
        listColors.add(new Colors(Color.parseColor("#800080")));
        listColors.add(new Colors(Color.parseColor("#ff00ff")));
        listColors.add(new Colors(Color.parseColor("#008000")));
        listColors.add(new Colors(Color.parseColor("#808000")));
        listColors.add(new Colors(Color.parseColor("#f0f8ff")));
        listColors.add(new Colors(Color.parseColor("#7fffd4")));
        listColors.add(new Colors(Color.parseColor("#f5f5dc")));
        listColors.add(new Colors(Color.parseColor("#ffe4c4")));
        listColors.add(new Colors(Color.parseColor("#d3d3d3")));
        listColors.add(new Colors(Color.parseColor("#90ee90")));
        listColors.add(new Colors(Color.parseColor("#d3d3d3")));
        listColors.add(new Colors(Color.parseColor("#ffb6c1")));
        listColors.add(new Colors(Color.parseColor("#20b2aa")));
        listColors.add(new Colors(Color.parseColor("#b0c4de")));
        listColors.add(new Colors(Color.parseColor("#7b68ee")));
        listColors.add(new Colors(Color.parseColor("#48d1cc")));
        listColors.add(new Colors(Color.parseColor("#98fb98")));
        listColors.add(new Colors(Color.parseColor("#ffc0cb")));
        return listColors;
    }

}
