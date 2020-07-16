package com.example.animate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static boolean barStansaction = false;
    public void fade(View view){
        ImageView barHome = (ImageView)findViewById(R.id.barhome);
        ImageView barSencond = (ImageView)findViewById(R.id.barseconds);
        if(!barStansaction){
            barStansaction = true;
            barHome.animate().alpha(0).scaleX(0.2f).scaleY(0.2f).setDuration(2000);
            barSencond.animate().alpha(1).setDuration(2000);
        }else{
            barStansaction= false;
            barHome.animate().alpha(1).scaleY(1f).scaleX(1f).setDuration(2000);
            barSencond.animate().alpha(0).setDuration(2000);
        }

        Toast.makeText(this, " Transaction ", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
    }
}
