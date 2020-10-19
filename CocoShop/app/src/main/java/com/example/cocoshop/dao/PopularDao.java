package com.example.cocoshop.dao;

import android.content.Intent;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.Adapter.CardItemAdapter;
import com.example.cocoshop.Models.topicsmodel.Levels;
import com.example.cocoshop.Models.topicsmodel.Topic;
import com.example.cocoshop.Models.vocabularysmodel.Vocabulary;
import com.example.cocoshop.Screen.topicsscreen.LearningTopicActivity;
import com.example.cocoshop.dao.audiodao.TopicDao;
import com.example.cocoshop.listener.Listener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PopularDao extends AsyncTask<Void, ArrayList<Topic>,Void> {
    private RecyclerView popularRecyclerView;
    private static FirebaseFirestore firestore;
    private ArrayList<Topic> listPopular;
    public static final String MODEL = "POPUlAR";
    static {
        firestore = FirebaseFirestore.getInstance();
    }

    public PopularDao(RecyclerView popularRecyclerView) {
        this.popularRecyclerView = popularRecyclerView;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        firestore.collection("topics").limit(4).orderBy("popular", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    listPopular = new ArrayList<>();
                    for(DocumentSnapshot i : task.getResult().getDocuments()){
                        Map<String,Object> item = i.getData();
                        Topic topic = new Topic(item.get("title").toString(),item.get("description").toString(),(long)item.get("id"), Levels.valueOf(item.get("level").toString()),(ArrayList<Vocabulary>)item.get("vocabuary"),item.get("urlImage").toString(),(long)item.get("popular"),item.get("iddocument").toString());
                        listPopular.add(topic);
                    }
                    onProgressUpdate(listPopular);
                }
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(final ArrayList<Topic>... values) {
        super.onProgressUpdate(values);
        if(values[0] != null){
            CardItemAdapter cardItemAdapter = new CardItemAdapter(values[0]);
            popularRecyclerView.setAdapter(cardItemAdapter);
            popularRecyclerView.setLayoutManager(new GridLayoutManager(popularRecyclerView.getContext(),2));
            cardItemAdapter.setCardListener(new Listener() {
                @Override
                public void listener(int position) {
                    Intent intent = new Intent(popularRecyclerView.getContext(), LearningTopicActivity.class);
                    intent.putExtra(LearningTopicActivity.KEYPOSITION,position);
                    intent.putExtra(LearningTopicActivity.MODEL,MODEL);
                    popularRecyclerView.getContext().startActivity(intent);
                    new TopicDao.PopularAsycnTask().execute((Topic) values[0].get(position));
                }
            });
        }
    }

    public ArrayList<Topic> getListPopular() {
        return listPopular;
    }
}
