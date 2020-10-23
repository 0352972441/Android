package com.example.cocoshop.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cocoshop.models.UserAccount;
import com.example.cocoshop.R;
import com.example.cocoshop.screen.HomeScreen;
import com.example.cocoshop.dao.MessageDao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;


public class ChatFragment extends Fragment {
    private static final String[] keywordForbid = {"dm","du ma","lon","cac","fack","di me may"};
    private RecyclerView messageRecycerView;
    private EditText edBoxMessage;
    private ImageView imgSend;
    private ImageView imgrecord;
    private MessageDao messageDao;
    private static final FirebaseFirestore firestore;
    private static final FirebaseAuth mAuth;
    private UserAccount userAccount;
    static {
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        messageRecycerView = (RecyclerView)view.findViewById(R.id.chat_item);
        edBoxMessage = (EditText)view.findViewById(R.id.box_message);
        imgSend = (ImageView)view.findViewById(R.id.img_send);
        imgrecord = (ImageView)view.findViewById(R.id.img_record);
        new getUserAsyncTask(firestore,mAuth).execute();
        onClickSendMessage();
        messageDao = new MessageDao(messageRecycerView,getContext());
        messageDao.execute();
    }


    private void onClickSendMessage(){
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = edBoxMessage.getText().toString();
                if (!message.isEmpty()) {
                    for(int i=0; i<keywordForbid.length;i++){
                        if (message.contains(keywordForbid[i])){
                            message = "I'm really sorry";
                        }
                    }
                    HashMap<String, Object> userMessage = new HashMap<>();
                    userMessage.put("me", userAccount.getUid());
                    userMessage.put("message", message);
                    userMessage.put("imgUrl", userAccount.getImage());
                    userMessage.put("userName", userAccount.getEmail().split("@")[0]);
                    userMessage.put("time", Calendar.getInstance().getTime());
                    userMessage.put("accounttype",userAccount.getAccount());
                    firestore.collection("messages").document(UUID.randomUUID().toString()).set(userMessage).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            messageDao = new MessageDao(messageRecycerView,getContext());
                            messageDao.execute();
                            edBoxMessage.setText("");
                        }
                    });
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        HomeScreen.isCurrentFragment = "Chat";
    }

     class getUserAsyncTask extends AsyncTask<Void,UserAccount,Boolean>{
        private FirebaseFirestore firestore;
        private FirebaseAuth mAuth;
        public getUserAsyncTask(FirebaseFirestore firestore,FirebaseAuth mAuth) {
            this.firestore = firestore;
            this.mAuth = mAuth;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            firestore.collection("users").document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot snapshot = task.getResult();
                        Log.d("Data:",""+snapshot.getData());
                        String email = snapshot.getData().get("email").toString();
                        String avata = snapshot.getData().get("avata").toString();
                        String account = snapshot.getData().get("account").toString();
                        String uid = snapshot.getData().get("uid").toString();
                        userAccount = new UserAccount(account,email,avata,uid);
                    }
                }
            });
            return true;
        }
    }
}
