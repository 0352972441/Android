package com.example.note.Data;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import com.example.note.Listenner.ListenerFloatAction;
import com.example.note.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Event {
    //public static boolean  ImageLike = false;
    private static boolean like(FloatingActionButton floatingActionButton, boolean checkLike, int like, int unLike){
        if(!checkLike){
            checkLike = true;
            floatingActionButton.setImageResource(like);
        }else{
            checkLike = false;
            floatingActionButton.setImageResource(unLike);
        }
        return checkLike;
    }

    public static void favorite(final View view){
        final FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.favorite);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            private boolean checkLike = false;
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(view.findViewById(R.id.layoutStory),"LIKE",Snackbar.LENGTH_LONG);
                checkLike = Event.like(floatingActionButton,checkLike,R.drawable.favorite,R.drawable.unlike);
                ListenerFloatAction listenerFloatAction = new ListenerFloatAction();
                snackbar.setAction("Undo",listenerFloatAction);
                snackbar.show();
            }
        });

    }

}
