package com.example.cocoshop.Screen.topicsscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.cocoshop.Adapter.topicsadapter.ViewPagerVocabulary;
import com.example.cocoshop.R;
import com.example.cocoshop.dao.audiodao.TopicDao;
import com.example.cocoshop.listener.Listener;
import com.example.cocoshop.ui.Learnbytopic.FragmentLearningTopic;

public class LearningTopicActivity extends AppCompatActivity {
    public static final String KEYPOSITION = "position";
    private ViewPager2 viewPagerVocabulary;
    private ViewPagerVocabulary vocabularyAdapter;
    private int position=0;
    private TopicDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_topic);
        dao = FragmentLearningTopic.dao;
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            position = (int)getIntent().getExtras().get(KEYPOSITION);
            viewPagerVocabulary = findViewById(R.id.page_learning_vocabulary);
            vocabularyAdapter = new ViewPagerVocabulary(dao.getTopics().get(position).getVocabularies());
            viewPagerVocabulary.setAdapter(vocabularyAdapter);
            viewPagerVocabulary.setUserInputEnabled(false);
            vocabularyAdapter.setOnClickNextVocabulary(new Listener() {
                @Override
                public void listener(int position) {
                    viewPagerVocabulary.setCurrentItem(viewPagerVocabulary.getCurrentItem()+1);
                }
            });
        } catch (Exception ex){

        }
    }
}
