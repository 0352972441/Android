package com.example.android.dialog;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ShowDatePicker {
    private static int days ;
    private static int months ;
    private static int years;
    public static void showDate(Context context, final EditText time){
        final Calendar calendar = Calendar.getInstance();
        days = calendar.get(Calendar.DAY_OF_MONTH);
        months = calendar.get(Calendar.MONTH);
        years = calendar.get(Calendar.YEAR);

        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                if(year > years || month > months || dayOfMonth > days){
                    Toast.makeText(view.getContext(), "The date you selected is greater than the current date", Toast.LENGTH_SHORT).show();
                    return;
                }
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                time.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },years,months,days);
        dialog.show();
    }
}
