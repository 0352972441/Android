package com.example.cocoshop.dao.audiodao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.signature.ObjectKey;
import com.example.cocoshop.Adapter.topicsadapter.CardItemTopicAdapter;
import com.example.cocoshop.Models.topicsmodel.Levels;
import com.example.cocoshop.Models.topicsmodel.Topic;
import com.example.cocoshop.Models.vocabularysmodel.Vocabulary;
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.topicsscreen.LearningTopicActivity;
import com.example.cocoshop.listener.Listener;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TopicDao extends AsyncTask<Void, ArrayList<Topic>,Boolean> {
    @SuppressLint("StaticFieldLeak")
    private static final FirebaseFirestore firestore;
    private static final FirebaseStorage storage;
    private static final StorageReference mRef;
    public static long countPopular = 1;
    public static final String MODEL = "TOPIC";
    //private static Topic topic = new Topic();
    private ArrayList<Topic> topics = new ArrayList<>();

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    static {
        storage = FirebaseStorage.getInstance();
        firestore = FirebaseFirestore.getInstance();
        mRef = storage.getReference();
    }
    private Activity activity;

    public TopicDao(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        firestore.collection("topics").orderBy("id", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot items : task.getResult().getDocuments()){
                        Map<String,Object> item = items.getData();
                        Topic topic = new Topic(item.get("title").toString(),item.get("description").toString(),(long)item.get("id"),Levels.valueOf(item.get("level").toString()),(ArrayList<Vocabulary>)item.get("vocabuary"),item.get("urlImage").toString(),(long)item.get("popular"),item.get("iddocument").toString());
                        topics.add(topic);
                    }
                    publishProgress(topics);
                }
            }
        });
        return true;
    }

    @Override
    protected void onProgressUpdate(final ArrayList<Topic>... values) {
        super.onProgressUpdate(values);
        RecyclerView cardItemTopic = (RecyclerView)activity.findViewById(R.id.cardTopic);
        CardItemTopicAdapter cardAdapter = new CardItemTopicAdapter(values[0]);
        if(cardAdapter!= null && cardItemTopic!= null){
            cardItemTopic.setAdapter(cardAdapter);
            cardItemTopic.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
            cardAdapter.setListenerItem(new Listener() {
                @Override
                public void listener(int position) {
                    Intent intent = new Intent(activity, LearningTopicActivity.class);
                    intent.putExtra(LearningTopicActivity.KEYPOSITION,position);
                    intent.putExtra(LearningTopicActivity.MODEL,MODEL);
                    activity.startActivity(intent);
                    new PopularAsycnTask().execute(values[0].get(position));
                }
            });
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }



    public static class PopularAsycnTask extends AsyncTask<Topic,Topic,Void>{
        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Topic... maps) {
            onProgressUpdate(maps[0]);
            return null;
        }

        @Override
        protected void onProgressUpdate(Topic... values) {
            super.onProgressUpdate(values);
            if(values[0] != null){
                countPopular = countPopular + values[0].getPopular();
                HashMap<String,Object> popular = new HashMap<>();
                popular.put("popular",countPopular);
                firestore.collection("topics").document(values[0].getIddocument()).update(popular).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
            }
        }
    }

    /*public static void getAllTopic(){
        CollectionReference collectionReference = firestore.collection("topic");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot i : task.getResult().getDocuments()){
//                        Topic topic = new Topic();
                        Map<String,Object> data = i.getData();
                        topic.setName(data.get("title").toString());
                        topic.setDescription(data.get("description").toString());
                        topic.setLevel(Levels.valueOf(data.get("level").toString()));
                        topic.setTopicCode(Integer.valueOf(data.get("id").toString()));
                        topic.setUrlImage(data.get("urlImage").toString());
                        StorageReference storageReference = mRef.child("audioTopic").child(i.getData().get("id").toString());
                        storageReference.listAll().addOnCompleteListener(new OnCompleteListener<ListResult>() {
                            @Override
                            public void onComplete(@NonNull Task<ListResult> task) {
                                if(task.isSuccessful()){
                                    for ( StorageReference i : task.getResult().getItems()){
                                         ArrayList<Vocabulary> vocabularies = new ArrayList<>();
                                         vocabularies.add(new Vocabulary("Nghĩa",i.getName(),"url","",2));
                                        topic.setVocabularies(vocabularies);
                                    }
                                }
                            }
                        });
                        topics.add(topic);
                        topic = new Topic();
                    }
                }
            }
        });
    }*/
    /*static final String[] TitleTopic = {"Gia đình","Công nghệ thông tin","Động vật", "Du lich","Rau củ quả","Phòng và nội thất","Thức ăn","Đất nước","Thể thao","Phương tiện giao thông"};
    static int index = 0;
    @Override
    protected Void doInBackground(Void... voids) {
            StorageReference ref = mRef.child("imgTopics").child(9+".jpg");
            ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()) {
                        final Map<String,Object> data = new HashMap<>();;
                        data.put("title",TitleTopic[9]);
                        data.put("urlImage",task.getResult().toString());
                        data.put("description","Description");
                        data.put("id",9);
                        data.put("level", Levels.BEGINER);
                        StorageReference ref = mRef.child("audioTopic").child(String.valueOf(9));
                        ref.listAll().addOnCompleteListener(new OnCompleteListener<ListResult>() {
                            @Override
                            public void onComplete(@NonNull Task<ListResult> task) {
                                if(task.isSuccessful()){
                                    final List<Vocabulary> vocabularies = new ArrayList<>();
                                    for(StorageReference items : task.getResult().getItems()){
                                        final String name[] = new String[1];
                                        name[0] = items.getName().substring(0,items.getName().length()-4);
                                        final Vocabulary[] vocabulary = new Vocabulary[1];
                                        items.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Uri> task) {
                                                vocabulary[0] = new Vocabulary("null", name[0],task.getResult().toString(),"null", index++);
                                                vocabularies.add(vocabulary[0]);
                                            }
                                        });
                                    }
                                    if (task.isComplete()){
                                        data.put("vocabuary",vocabularies);
                                        firestore.collection("topics").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                                Log.d("Size","Size:"+ data.size());
                                                firestore.collection("topics").document("0xF1n5z4I02FVJEW51M8").update(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                    }
                                                });
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }
                }
            });
        return null;
    }*/
}
