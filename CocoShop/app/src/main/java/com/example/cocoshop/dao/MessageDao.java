package com.example.cocoshop.dao;

import android.content.Context;
import android.content.Entity;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.adapter.Chat.MessageAdapter;
import com.example.cocoshop.models.Message;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageDao extends AsyncTask<Void, ArrayList<Message>,Void> {
    private static final FirebaseDatabase mDatabase;
    private static final String COLECTION = "messages";
    private ArrayList<Message> messages = new ArrayList<>();
    private RecyclerView messageRecyclerView;
    private Context context;

    public MessageDao(RecyclerView messageRecyclerView, Context context) {
        this.messageRecyclerView = messageRecyclerView;
        this.context = context;
    }

    static {
        mDatabase = FirebaseDatabase.getInstance();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        mDatabase.getReference().child("message").orderByChild("time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null) {
                    messages.clear();
                    for(Map.Entry<String, Object> i : ((Map<String, Object>) snapshot.getValue()).entrySet()){
                        Map<String, Object> item = (Map<String, Object>) i.getValue();
                        Message message = new Message(item.get("message").toString(), item.get("avata").toString(), item.get("userName").toString(), item.get("time").toString(), item.get("uid").toString(), item.get("accoutType").toString());
                        messages.add(message);
                    }
                    Collections.sort(messages, new Comparator<Message>() {
                        @Override
                        public int compare(Message o1, Message o2) {
                            return o1.getTime().compareTo(o2.getTime());
                        }
                    });
                    onProgressUpdate(messages);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return null;
    }


    @Override
    protected void onProgressUpdate(ArrayList<Message>... values) {
        MessageAdapter adapter = new MessageAdapter(values[0], FirebaseAuth.getInstance().getUid());
        if(messageRecyclerView != null){
            messageRecyclerView.setAdapter(adapter);
            messageRecyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setStackFromEnd(true);
            messageRecyclerView.setLayoutManager(linearLayoutManager);
        }
    }
}
