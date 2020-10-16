package com.example.android.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

import com.example.android.R;
import com.example.android.dao.CourseRegistrationDao;
import com.example.android.entity.CourseEntity;
import com.example.android.models.Rate;
import com.example.android.responsive.CourseResponsive;

public class AlertDialogCourse {
    private int index;

    public AlertDialogCourse(int index) {
        this.index = index;
    }

    public AlertDialogCourse() {
    }

    public  void showDialog(final CourseResponsive courseResponsive, final Context context, final boolean isUpdate, final CourseEntity courseEntity){
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_add_course,null,false);
        final EditText txAuthor = (EditText)view.findViewById(R.id.tx_author);
        final EditText txTitle = (EditText)view.findViewById(R.id.tx_title);
        final ImageView imgCourse = (ImageView)view.findViewById(R.id.img_course);
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setView(view);
        dialog.setTitle("Uploading course");
        dialog.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String author = txAuthor.getText().toString();
                String title = txTitle.getText().toString();
                CourseEntity register = new CourseEntity(title,author, Rate.VERYGOOD.toString(),R.drawable.flutter);
                if(isUpdate){
                    int id = courseEntity.getId();
                    register.setId(id);
                    courseResponsive.update(register);
                    CourseRegistrationDao.onChangeUpdateListener(register,index);
                }else{
                    courseResponsive.insert(register);
                    CourseRegistrationDao.onChangeInsertListener(register);
                }
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
