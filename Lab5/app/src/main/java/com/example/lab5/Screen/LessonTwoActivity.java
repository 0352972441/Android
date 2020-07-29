package com.example.lab5.Screen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lab5.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LessonTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_two);
    }


    public void showAlertDiaglog(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Do you want logout?");
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.setIcon(R.drawable.warn);
        alertDialog.setMessage("Are you ok ?");
        alertDialog.show();
    }

    public void showAlertDiaglogChoice(View view) {
        final String[] colors = new String[]{"Red", "Blue", "Yellow", "Green"};
        final boolean[] Favorite ={false, false, false, false};
        final Map<Integer, String> FavoriteColors = new HashMap<>();
        FavoriteColors.put(0,"");
        FavoriteColors.put(1,"");
        FavoriteColors.put(2,"");
        FavoriteColors.put(3,"");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("What is your favarite colors ?");
        //builder.setMessage("Are you fovarite color ?");
        //builder.setIcon(R.mipmap.done);
        builder.setMultiChoiceItems(colors, Favorite, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                boolean isCurrent = Favorite[which];
                if(isCurrent){
                    FavoriteColors.replace(which,colors[which]);
                }else{
                    FavoriteColors.replace(which,"");
                }
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String data = "";
                for(Map.Entry me : FavoriteColors.entrySet()){
                    if(!me.getValue().equals("")) {
                        data += "\n" + me.getValue();
                    }
                }
                Toast.makeText(LessonTwoActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
