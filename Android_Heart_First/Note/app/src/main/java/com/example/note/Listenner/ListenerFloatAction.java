package com.example.note.Listenner;

import android.content.DialogInterface;
import android.service.autofill.OnClickAction;
import com.example.note.R;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListenerFloatAction implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(),"Thank you every one use application",Toast.LENGTH_LONG).show();
    }
}
