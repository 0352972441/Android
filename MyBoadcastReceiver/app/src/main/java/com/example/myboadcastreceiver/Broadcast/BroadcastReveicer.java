package com.example.myboadcastreceiver.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class BroadcastReveicer extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
                String phoneNumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Toast.makeText(context, "Phone: "+phoneNumber, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
