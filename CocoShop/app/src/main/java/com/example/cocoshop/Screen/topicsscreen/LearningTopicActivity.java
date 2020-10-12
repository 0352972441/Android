package com.example.cocoshop.Screen.topicsscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.cocoshop.Adapter.topicsadapter.ViewPagerVocabulary;
import com.example.cocoshop.Models.vocabularysmodel.Vocabulary;
import com.example.cocoshop.R;
import com.example.cocoshop.dao.audiodao.TopicDao;
import com.example.cocoshop.listener.Listener;
import com.example.cocoshop.ui.Learnbytopic.FragmentLearningTopic;

import java.util.ArrayList;

public class LearningTopicActivity extends AppCompatActivity {
    public static final String KEYPOSITION = "position";
    private ViewPager2 viewPagerVocabulary;
    private ViewPagerVocabulary vocabularyAdapter;
    private int position=0;
    private TopicDao dao;
    private Intent intent = null;
    private static ArrayList<Vocabulary> vocabularies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_topic);
        dao = FragmentLearningTopic.dao;
        position = (int)getIntent().getExtras().get(KEYPOSITION);
        vocabularies = dao.getTopics().get(position).getVocabularies();
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
                }
            });
        } catch (Exception ex){

        }
    }

    public static ArrayList<Vocabulary> getVocabularies() {
        return vocabularies;
    }
}
