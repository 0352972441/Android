package com.example.assignmentandroid.screens.courseactivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.assignmentandroid.R;

public class CourseActivity extends AppCompatActivity {
    private CardView cardRegisterCourse,cardMyCourse,cardRegisterStudent,cardStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        cardMyCourse = (CardView)findViewById(R.id.card_my_course);
        cardRegisterCourse = (CardView)findViewById(R.id.card_registration);
        cardRegisterStudent = (CardView)findViewById(R.id.card_registration_student);
        cardStudent = (CardView)findViewById(R.id.card_student);
    }

    public void onClickCard(View view){
        CardView card = (CardView)view;
        Intent intent = null;
        switch (card.getId()){
            case R.id.card_my_course:break;
            case R.id.card_registration:
                intent = new Intent(this,CourseRegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.card_registration_student: break;
            case R.id.card_student: break;
        }
    }

}
