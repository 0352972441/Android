package com.example.cocoshop.Screen.topicsscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.cocoshop.Adapter.topicsadapter.ViewPagerVocabulary;
import com.example.cocoshop.Models.vocabularysmodel.Vocabulary;
import com.example.cocoshop.R;
import com.example.cocoshop.dao.audiodao.TopicDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LearningTopicActivity extends AppCompatActivity {
    public static final String KEYPOSITION = "position";
    private ViewPager2 viewPagerVocabulary;
    private int position=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_topic);
        position = (int)getIntent().getExtras().get(KEYPOSITION);
        Log.d("Vocabulary:",""+ TopicDao.topics.get(position).getVocabularies());
    }

    /*private ArrayList<Vocabulary> fetchData(){
        ArrayList<Vocabulary> data = new ArrayList<>();
        for(int i=0; i<TopicDao.topics.get(position).getVocabularies().size(); i++){
            String mean = TopicDao.topics.get(position).getVocabularies().get(i).getMean();
            String vocabulary = TopicDao.topics.get(position).getVocabularies().get(i).getMean();
            String TopicDao.topics.get(position).getVocabularies().get(i).getMean();
            TopicDao.topics.get(position).getVocabularies().get(i).getMean();
            TopicDao.topics.get(position).getVocabularies().get(i).getMean();
            data.add(new Vocabulary(TopicDao.topics.get(position).getVocabularies().get(i).getMean()));
        }
        return data;
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        viewPagerVocabulary = findViewById(R.id.page_learning_vocabulary);
        viewPagerVocabulary.setAdapter(new ViewPagerVocabulary(TopicDao.topics.get(position).getVocabularies()));
    }
}
