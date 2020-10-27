package com.example.cocoshop.screen.topicsscreen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.cocoshop.adapter.topicsadapter.ViewPagerVocabulary;
import com.example.cocoshop.animation.Animations;
import com.example.cocoshop.database.entity.TopicProgressEntity;
import com.example.cocoshop.database.responsive.TopicProgressResponsive;
import com.example.cocoshop.models.Vocabulary;
import com.example.cocoshop.R;
import com.example.cocoshop.dao.PopularDao;
import com.example.cocoshop.dao.RecommendDao;
import com.example.cocoshop.dao.topicdao.TopicDao;
import com.example.cocoshop.listener.Listener;
import com.example.cocoshop.fragment.HomeFragment;
import com.example.cocoshop.fragment.TopicFragment;

import java.util.ArrayList;

public class LearningTopicActivity extends AppCompatActivity {
    public static final String KEYPOSITION = "position";
    public static final String MODEL = "model";
    public static final String ID = "id";
    private ViewPager2 viewPagerVocabulary;
    private ViewPagerVocabulary vocabularyAdapter;
    private int position=0;
    private TopicDao dao;
    private RecommendDao recommendDao;
    private PopularDao popularDao;
    private Intent intent = null;
    private static ArrayList<Vocabulary> vocabularies;
    private int topicId,currentProgressive;
    private static TopicProgressResponsive responsive;
    private Animations animations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_topic);
        responsive = new TopicProgressResponsive(this);
        animations = new Animations(this);

        vocabularies = new ArrayList<>();
        dao = TopicFragment.dao;
        recommendDao = HomeFragment.recommendDao;
        popularDao = HomeFragment.popularDao;

        position = (int)getIntent().getExtras().get(KEYPOSITION);
        topicId = (int)getIntent().getExtras().get(ID);
        currentProgressive = responsive.getProgressive(topicId).getPosition();
        switch ((String)getIntent().getExtras().get(MODEL)){
            case TopicDao.MODEL:
                for (int i = currentProgressive; i < dao.getTopics().get(position).getVocabularies().size(); i++) {
                    vocabularies.add(dao.getTopics().get(position).getVocabularies().get(i));
                }
                break;
            case RecommendDao.MODEL:
                for (int i = currentProgressive; i < recommendDao.getRecommendTopic().get(position).getVocabularies().size(); i++) {
                    vocabularies.add(recommendDao.getRecommendTopic().get(position).getVocabularies().get(i));
                }
                break;
            case PopularDao.MODEL:
                for (int i = currentProgressive; i < popularDao.getListPopular().get(position).getVocabularies().size(); i++) {
                    vocabularies.add(popularDao.getListPopular().get(position).getVocabularies().get(i));
                }
                break;
        }
    }

    /*private boolean isCompleteTopic(int currentProgressive, ArrayList<Vocabulary> data){
        if(currentProgressive == data.size()-1){
            return true;
        }
        return false;
    }*/
    /*private void dialogComfirmStartOver(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Do you want to start over ?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TopicProgressEntity entity = new TopicProgressEntity(topicId,1);
                responsive.update(entity);
                switch ((String)getIntent().getExtras().get(MODEL)){
                    case TopicDao.MODEL:
                        vocabularies = dao.getTopics().get(position).getVocabularies();
                        break;
                    case PopularDao.MODEL:
                        vocabularies = popularDao.getListPopular().get(position).getVocabularies();
                        break;
                    case RecommendDao.MODEL:
                        vocabularies = recommendDao.getRecommendTopic().get(position).getVocabularies();
                        break;
                }
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dialog.cancel();
                finish();
            }
        });
        dialog.show();
    }*/

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
                    viewPagerVocabulary.setCurrentItem(viewPagerVocabulary.getCurrentItem()+1);
                    TopicProgressEntity entity = new TopicProgressEntity(topicId,viewPagerVocabulary.getCurrentItem()+currentProgressive);
                    responsive.update(entity);
                }
            });
            vocabularyAdapter.setOnClickMultipleChoice(new Listener() {
                @Override
                public void listener(int position) {
                    TopicProgressEntity entity = new TopicProgressEntity(topicId,viewPagerVocabulary.getCurrentItem()+currentProgressive);
                    responsive.update(entity);
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
