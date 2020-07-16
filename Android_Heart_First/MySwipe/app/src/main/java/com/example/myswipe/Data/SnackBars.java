package com.example.myswipe.Data;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class SnackBars {
    public static Snackbar make(View view, CharSequence text, int durition){
        Snackbar snackbar = Snackbar.make(view, text, durition);
        return snackbar;
    }
    public static void setAction(Snackbar snackbar, String text, View.OnClickListener onClickListener){
        snackbar.setAction(text,onClickListener);
    }
    public static void show(Snackbar snackbar){
        snackbar.show();
    }
}
