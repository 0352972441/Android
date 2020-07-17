package com.example.note.Activity;
import com.example.note.ActivityFragment.StoryFragment;
import com.example.note.Data.CustomAdapter;
import com.example.note.Data.Transaction;
import com.example.note.R;
import com.example.note.TransactionFragment.TranFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class ContextStory extends AppCompatActivity {
    public static final String EXTRA = "KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_story);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cha giầu cha nghèo");
        setSupportActionBar(toolbar);
        getExtraIntent();
        TranFragment.add(getSupportFragmentManager());
    }

    public void getExtraIntent(){
        Intent intent = getIntent();
        int index = intent.getExtras().getInt(EXTRA);
        new StoryFragment().index(index);
    }


}
