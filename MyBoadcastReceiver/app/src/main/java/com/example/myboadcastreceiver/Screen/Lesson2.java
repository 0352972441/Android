package com.example.myboadcastreceiver.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myboadcastreceiver.Broadcast.PromotionBroadcast;
import com.example.myboadcastreceiver.Broadcast.SendBroadcast;
import com.example.myboadcastreceiver.R;

public class Lesson2 extends AppCompatActivity {
    PromotionBroadcast promotionBroadcast;
    private EditText edCoupons;
    String coupons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);
        edCoupons = (EditText)findViewById(R.id.edCoupon);
        promotionBroadcast = new PromotionBroadcast();
        IntentFilter intentFilter = new IntentFilter("com.coupons.promotion");
        registerReceiver(promotionBroadcast,intentFilter);
    }

    @Override
    protected void onStop() {

        super.onStop();
        unregisterReceiver(promotionBroadcast);
    }

    public void onReceiveCoupon(View view) {
            if(isCoupons()){
                Intent intent = new Intent();
                intent.putExtra(PromotionBroadcast.KEYPROMOION, coupons);
                intent.setAction("com.coupons.promotion");
                sendBroadcast(intent);
            }else{
                //Toast.makeText(this, "Lỗi CMT", Toast.LENGTH_SHORT).show();
            }

    }

    private boolean isCoupons(){
        coupons = edCoupons.getText().toString();
        Toast.makeText(this, String.valueOf(coupons.contains("vip")), Toast.LENGTH_SHORT).show();
        if(!coupons.contains("VIP") && !coupons.contains("MEN")) {
            Toast.makeText(this, "Coupons must start VIP or MEN", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(coupons.length() != 9){
            Toast.makeText(this, "Coupons must 9 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
