package com.example.cocoshop.ui.Progressivelearning;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cocoshop.Models.UserAccount;
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.HomeScreen.HomeScreen;
import com.example.cocoshop.firebaseStorange.FirebaseStorangeUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;


public class FragmentChat extends Fragment {
    private RecyclerView message;
    private EditText edBoxMessage;
    private ImageView imgSend;
    private ImageView imgrecord;
    private FirebaseUser user;
    private static final FirebaseFirestore firestore;
    private static final FirebaseAuth mAuth;
    private UserAccount userAccount;
    static {
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        message = (RecyclerView)view.findViewById(R.id.message);
        edBoxMessage = (EditText)view.findViewById(R.id.box_message);
        imgSend = (ImageView)view.findViewById(R.id.img_send);
        imgrecord = (ImageView)view.findViewById(R.id.img_record);
        new getUserAsyncTask(firestore,mAuth).execute();
        onClickSendMessage();
    }

    private void onClickSendMessage(){
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = FirebaseAuth.getInstance().getCurrentUser();
                firestore.collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            String message = edBoxMessage.getText().toString();
                            if(!message.isEmpty() && !message.contains("du ma")){
                                HashMap<String,Object> userMessage = new HashMap<>();
                                userMessage.put("me",user.getUid());
                                userMessage.put("message",message);
                                userMessage.put("imgUrl",task.getResult().get("urlImage"));
                                userMessage.put("userName",task.getResult().get("email").toString());
                                userMessage.put("time", Calendar.getInstance().getTime());
                                firestore.collection("messages").document(user.getUid()).set(userMessage).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Log.d("Thành công",""+task.getResult());
                                    }
                                });
                            }
                        }
                    }
                });
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
                        if(snapshot != null){
                            String email = snapshot.getData().get("email").toString();
                            String avata = snapshot.getData().get("avata").toString();
                            String account = snapshot.getData().get("account").toString();
                            String uid = snapshot.getData().get("uid").toString();
                            userAccount = new UserAccount(email,avata,account,uid);
                        }
                    }
                }
            });
            return true;
        }
    }
}
