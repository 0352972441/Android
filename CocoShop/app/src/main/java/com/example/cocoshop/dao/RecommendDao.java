package com.example.cocoshop.dao;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecommendDao extends AsyncTask<Void, ArrayList<Topic>,Void> {
    private static final FirebaseFirestore firestore;
    private  List<Map<String,Object>> data;
    private ArrayList<Topic> recommendTopic;
    private RecyclerView recommendRecyclerView;
    public static final String MODEL = "RECOMMEND";
    public RecommendDao(RecyclerView recommendRecyclerView) {
        this.recommendRecyclerView = recommendRecyclerView;
    }

    static {
        firestore = FirebaseFirestore.getInstance();
    }
    @Override
    protected Void doInBackground(Void... strings) {
        firestore.collection("topics").limit(4).whereGreaterThanOrEqualTo("popular",1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    recommendTopic = new ArrayList<>();
                    /*data = new ArrayList<>();
                    for(DocumentSnapshot i : task.getResult().getDocuments()){
                        data.add(i.getData());
                    }
                    onProgressUpdate(data);*/
                    for(DocumentSnapshot i : task.getResult().getDocuments()){
                        Map<String,Object> item = i.getData();
                        Topic topic = new Topic(item.get("title").toString(),item.get("description").toString(),(long)item.get("id"), Levels.valueOf(item.get("level").toString()),(ArrayList<Vocabulary>)item.get("vocabuary"),item.get("urlImage").toString(),(long)item.get("popular"),item.get("iddocument").toString());
                        recommendTopic.add(topic);
                    }
                    onProgressUpdate(recommendTopic);
                }
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(final ArrayList<Topic>... values) {
        super.onProgressUpdate(values);
        if(values[0] != null){
            CardItemAdapter adapter = new CardItemAdapter(values[0]);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(recommendRecyclerView.getContext(),2);
            recommendRecyclerView.setAdapter(adapter);
            recommendRecyclerView.setLayoutManager(gridLayoutManager);
            adapter.setCardListener(new Listener() {
                @Override
                public void listener(int position) {
                    Intent intent = new Intent(recommendRecyclerView.getContext(), LearningTopicActivity.class);
                    intent.putExtra(LearningTopicActivity.KEYPOSITION,position);
                    intent.putExtra(LearningTopicActivity.MODEL,MODEL);
                    recommendRecyclerView.getContext().startActivity(intent);
                    new TopicDao.PopularAsycnTask().execute((Topic) values[0].get(position));
                }
            });
        }
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public ArrayList<Topic> getRecommendTopic() {
        return recommendTopic;
    }
}
