package com.example.cocoshop.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.adapter.Chat.MessageAdapter;
import com.example.cocoshop.models.Message;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class MessageDao extends AsyncTask<Void, ArrayList<Message>,Void> {
    private static final FirebaseFirestore store;
    private static final String COLECTION = "messages";
    private ArrayList<Message> messages = new ArrayList<>();
    private RecyclerView messageRecyclerView;
    private Context context;

    public MessageDao(RecyclerView messageRecyclerView, Context context) {
        this.messageRecyclerView = messageRecyclerView;
        this.context = context;
    }

    static {
        store = FirebaseFirestore.getInstance();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        store.collection(COLECTION).orderBy("time", Query.Direction.DESCENDING).limit(15).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(int i = task.getResult().getDocuments().size()-1; i>=0; i--){
                        Map<String,Object> item = task.getResult().getDocuments().get(i).getData();
                        String mes = item.get("message").toString();
                        String avata = item.get("imgUrl").toString();
                        String uid = item.get("me").toString();
                        String typeAccount = item.get("accounttype").toString();
                        String userName = item.get("userName").toString();
                        String time = item.get("time").toString();
                        Message message = new Message(mes,avata,userName,time,uid,typeAccount);
                        messages.add(message);
                    }
                    onProgressUpdate(messages);
                }
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
