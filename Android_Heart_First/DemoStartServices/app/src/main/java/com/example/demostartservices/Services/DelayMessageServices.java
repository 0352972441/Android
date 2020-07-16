package com.example.demostartservices.Services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.demostartservices.MainActivity;
import com.example.demostartservices.R;

public class DelayMessageServices extends IntentService {
    public static final String EXTRA_MESSAGE = "Message";
    public static final int ID = 1311;
    public DelayMessageServices() {
        super("DelayMessageServices");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        show(message);
    }
     private void show(String text){
         NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(android.R.drawable.sym_def_app_icon)
                 .setContentTitle(text).setContentText(getString(R.string.question)).setPriority(NotificationCompat.PRIORITY_HIGH).setVibrate(new long[]{0, 1000})
                 .setAutoCancel(true);
         Intent intent = new Intent(this, MainActivity.class);
         PendingIntent  actionIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
         builder.setContentIntent(actionIntent);
         NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
         manager.notify(ID,builder.build());
     }

}
