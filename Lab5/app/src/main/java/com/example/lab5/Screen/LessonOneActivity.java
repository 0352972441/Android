package com.example.lab5.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lab5.R;

import java.util.Calendar;

public class LessonOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_one);
    }

    public void showDatePickerDialog(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final String time = converDateToString(year)+":"+ converDateToString(month)+":"+converDateToString(day);
                Toast.makeText(LessonOneActivity.this, "Time: "+time, Toast.LENGTH_SHORT).show();
            }
        }, year, month, day);
        dialog.show();
    }

    public void showTimePickerDialog(View view) {
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(LessonOneActivity.this, "Time: "+String.valueOf(hourOfDay) +":"+ String.valueOf(minute), Toast.LENGTH_SHORT).show();
            }
        }, hours, minute, true);
        dialog.show();
    }

    private String converDateToString(int dateTime){
        try{
            String time = dateTime <10 ? "0"+String.valueOf(dateTime) :String.valueOf(dateTime);
            return time;
        }catch (Exception ex){
            return "";
        }
    }
}
