package com.example.cocoshop.screen.topicsscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.cocoshop.adapter.topicsadapter.Multiple_Choice_Adapter;
import com.example.cocoshop.R;
import com.example.cocoshop.listener.OnClickListener;

public class MultipleChoiceActivity extends AppCompatActivity {
    private ViewPager2 pageMultipleChoice;
    private Multiple_Choice_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);
        pageMultipleChoice = (ViewPager2)findViewById(R.id.page_multiple_choice);
        adapter = new Multiple_Choice_Adapter(LearningTopicActivity.getVocabularies());
        pageMultipleChoice.setAdapter(adapter);
        pageMultipleChoice.setUserInputEnabled(false);

        adapter.setOnClickNextAnswerListener(new OnClickListener() {
            @Override
            public void onClickListener() {
                pageMultipleChoice.setCurrentItem(pageMultipleChoice.getCurrentItem() + 1);
            }
        });
    }
}
