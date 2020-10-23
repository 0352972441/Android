package com.example.cocoshop.Adapter.Chat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.Models.chatmodel.Message;
import com.example.cocoshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private ArrayList<Message> messages;
    private  String uid;
    private static final int ME = 1;
    private static final int GUESS = 2;
    public MessageAdapter(ArrayList<Message> messages,String uid) {
        this.messages = messages;
        this.uid = uid;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ME){
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_right,parent,false));
        }else{
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_left,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(messages != null){
            Message message = messages.get(position);
            Picasso.with(holder.imgAvata.getContext()).load(message.getAvata()).into(holder.imgAvata);
            holder.txMessage.setText(message.getMessage());
            holder.txUserName.setText(message.getUserName());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(messages.get(position).getUid().equals(uid)){
            return ME;
        }else{
            return GUESS;
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txUserName,txMessage;
        private ImageView imgAvata;
        public ViewHolder(@NonNull View view) {
            super(view);
            txUserName = (TextView)view.findViewById(R.id.tx_user_name);
            txMessage = (TextView)view.findViewById(R.id.tx_message);
            imgAvata = (ImageView)view.findViewById(R.id.avata_message);
        }
    }
}
