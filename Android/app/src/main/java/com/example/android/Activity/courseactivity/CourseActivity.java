package com.example.android.Activity.courseactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android.R;

public class CourseActivity extends AppCompatActivity {
    private CardView cardRegisterCourse,cardMyCourse,cardStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        cardMyCourse = (CardView)findViewById(R.id.card_my_course);
        cardRegisterCourse = (CardView)findViewById(R.id.card_registration);
        cardStudent = (CardView)findViewById(R.id.card_student);
    }

    public void onClickCard(View view){
        CardView card = (CardView)view;
        Intent intent = null;
        switch (card.getId()){
            case R.id.card_my_course:
                intent = new Intent(this,MyCourseActivity.class);
                startActivity(intent);
                break;
            case R.id.card_registration:
                intent = new Intent(this,CourseRegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.card_student:
                intent = new Intent(this,StudentActivity.class);
                startActivity(intent);
                break;
        }
    }

}
