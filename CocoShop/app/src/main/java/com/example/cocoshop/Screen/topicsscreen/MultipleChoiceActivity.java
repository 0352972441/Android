package com.example.cocoshop.Screen.topicsscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.cocoshop.Adapter.topicsadapter.Multiple_Choice_Adapter;
import com.example.cocoshop.R;

public class MultipleChoiceActivity extends AppCompatActivity {
    private ViewPager2 pageMultipleChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);
        pageMultipleChoice = (ViewPager2)findViewById(R.id.page_multiple_choice);
        Multiple_Choice_Adapter adapter = new Multiple_Choice_Adapter(LearningTopicActivity.getVocabularies());
        pageMultipleChoice.setAdapter(adapter);
    }
}
