package com.example.cocoshop.Screen.topicsscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.cocoshop.Adapter.topicsadapter.ViewPagerVocabulary;
import com.example.cocoshop.Models.vocabularysmodel.Vocabulary;
import com.example.cocoshop.R;
import com.example.cocoshop.dao.PopularDao;
import com.example.cocoshop.dao.RecommendDao;
import com.example.cocoshop.dao.audiodao.TopicDao;
import com.example.cocoshop.listener.Listener;
import com.example.cocoshop.ui.Home.FragmentHome;
import com.example.cocoshop.ui.Learnbytopic.FragmentLearningTopic;

import java.util.ArrayList;

public class LearningTopicActivity extends AppCompatActivity {
    public static final String KEYPOSITION = "position";
    public static final String MODEL = "model";
    private ViewPager2 viewPagerVocabulary;
    private ViewPagerVocabulary vocabularyAdapter;
    private int position=0;
    private TopicDao dao;
    private RecommendDao recommendDao;
    private PopularDao popularDao;
    private Intent intent = null;
    private static ArrayList<Vocabulary> vocabularies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_topic);
        dao = FragmentLearningTopic.dao;
        recommendDao = FragmentHome.recommendDao;
        popularDao = FragmentHome.popularDao;

        position = (int)getIntent().getExtras().get(KEYPOSITION);
        switch ((String)getIntent().getExtras().get(MODEL)){
            case TopicDao.MODEL:
                vocabularies = dao.getTopics().get(position).getVocabularies();
                break;
            case RecommendDao.MODEL:
                vocabularies = recommendDao.getRecommendTopic().get(position).getVocabularies();
                break;
            case PopularDao.MODEL:
                vocabularies = popularDao.getListPopular().get(position).getVocabularies();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            viewPagerVocabulary = findViewById(R.id.page_learning_vocabulary);
            vocabularyAdapter = new ViewPagerVocabulary(vocabularies);
            viewPagerVocabulary.setAdapter(vocabularyAdapter);
            viewPagerVocabulary.setUserInputEnabled(false);
            vocabularyAdapter.setOnClickNextVocabulary(new Listener() {
                @Override
                public void listener(int position) {
                    viewPagerVocabulary.setCurrentItem(vocabularies.size()-1/*viewPagerVocabulary.getCurrentItem()+1*/);
                }
            });
            vocabularyAdapter.setOnClickMultipleChoice(new Listener() {
                @Override
                public void listener(int position) {
                    intent = new Intent(LearningTopicActivity.this,MultipleChoiceActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } catch (Exception ex){

        }
    }

    public static ArrayList<Vocabulary> getVocabularies() {
        return vocabularies;
    }
}
