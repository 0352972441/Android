package com.example.myboadcastreceiver.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PromotionBroadcast extends BroadcastReceiver {
    public static final String KEYPROMOION = "promotion";
    @Override
    public void onReceive(Context context, Intent intent) {
        String promotion = intent.getExtras().getString(KEYPROMOION);
        if("MEM537128".equals(promotion)){
            Toast.makeText(context, promotion+" promotion 10%", Toast.LENGTH_SHORT).show();
        }else if("MEM537129".equals(promotion)){
            Toast.makeText(context, promotion+" promotion 20%", Toast.LENGTH_SHORT).show();
        }else if("VIP537128".equals(promotion)){
            Toast.makeText(context, promotion+" promotion 30%", Toast.LENGTH_SHORT).show();
        }else if("VIP537129".equals(promotion)){
            Toast.makeText(context, promotion+" promotion 50%", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, promotion+" code is invalid or expired", Toast.LENGTH_SHORT).show();
        }
    }
}
