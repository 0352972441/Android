package com.example.myswipe.Data;

import android.view.View;
import android.widget.Toast;

public class Listenner implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Toast toast =Toast.makeText(v.getContext(),"You undo success",Toast.LENGTH_SHORT);
        toast.show();
    }
}
