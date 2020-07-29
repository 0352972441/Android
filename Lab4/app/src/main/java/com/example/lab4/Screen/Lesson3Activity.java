package com.example.lab4.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.lab4.R;

public class Lesson3Activity extends AppCompatActivity {
    Button mPopMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPopMenu = (Button)findViewById(R.id.popButton);
        /*mPopMenu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopMenu();
                Toast.makeText(Lesson3Activity.this, "OK", Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/
        mPopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopMenu();
            }
        });
    }


    void showPopMenu(){
        PopupMenu popupMenu = new PopupMenu(this, mPopMenu);
        popupMenu.inflate(R.menu.optionmenuitem);
        popupMenu.show();
    }
}
