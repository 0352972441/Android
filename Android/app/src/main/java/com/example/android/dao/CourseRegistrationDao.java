package com.example.android.dao;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.adapter.Card_Course_Adapter;
import com.example.android.adapter.Card_Course_Register_Adapter;
import com.example.android.adapter.Student_Course_Register_Adapter;
import com.example.android.dialog.AlertDialogCourse;
import com.example.android.entity.CourseEntity;
import com.example.android.entity.MyCourseEntity;
import com.example.android.entity.StudentEntity;
import com.example.android.listener.Listener;
import com.example.android.models.Rate;
import com.example.android.responsive.CourseResponsive;
import com.example.android.responsive.MyCourseResponsive;
import com.example.android.responsive.StudentResponsive;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class CourseRegistrationDao extends AsyncTask<List<CourseEntity>,List<CourseEntity>,Void> {
    @SuppressLint("StaticFieldLeak")
    private RecyclerView card_course_register_recycler;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private static Card_Course_Register_Adapter card_course_register_adapter;
    private static Student_Course_Register_Adapter adapter;
    private CourseResponsive responsive;
    private static List<CourseEntity > courseEntities;
    private static List<StudentEntity> studentEntities;
    private CardView cardCourse;
    @SuppressLint("StaticFieldLeak")
    private RecyclerView recyclerStudent;
    private TextView txName,txByAuthor;
    private Button btn_register;
    private LinearLayout linearStar;
    private static int previousPosition =  0;
    private boolean isFlagItem = false;
    public CourseRegistrationDao(RecyclerView card_course_register_recycler, Context context,CourseResponsive responsive) {
        this.card_course_register_recycler = card_course_register_recycler;
        this.context = context;
        this.responsive = responsive;
    }

    @SuppressLint("WrongThread")
    @Override
    protected Void doInBackground(List<CourseEntity>... lists) {
        studentEntities = new StudentResponsive(context).getStudentEntityArrayList();
        onProgressUpdate(lists[0]);
        return null;
    }

    @Override
    protected void onProgressUpdate(List<CourseEntity>... values) {
        if(values[0] != null){
            courseEntities = values[0];
            card_course_register_adapter = new Card_Course_Register_Adapter(courseEntities);
            card_course_register_recycler.setAdapter(card_course_register_adapter);
            card_course_register_recycler.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
            onClickCardCourse();
            onClickRegister();
        }
    }

    public void onClickCardCourse(){
        card_course_register_adapter.setOnCLickCardCourseListener(new Listener() {
            @Override
            public void lintener(final int position) {
                View view = LayoutInflater.from(context).inflate(R.layout.layout_button_update_course,null,false);
                final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                final AlertDialog alert = dialog.create();
                view.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        responsive.delete(card_course_register_adapter.getSingleCourse(position));
                        courseEntities.remove(position);
                        card_course_register_adapter.notifyDataSetChanged();
                        alert.dismiss();
                    }
                });
                view.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialogCourse(position).showDialog(responsive,context,true,card_course_register_adapter.getSingleCourse(position));
                        alert.dismiss();
                    }
                });
                alert.setView(view);
                alert.show();
            }
        });
    }
    public static void onChangeUpdateListener(CourseEntity courseEntity, int index){
        courseEntities.set(index,courseEntity);
        card_course_register_adapter.notifyDataSetChanged();
    }
    public static void onChangeInsertListener(CourseEntity courseEntity){
        courseEntities.add(courseEntity);
        card_course_register_adapter.notifyDataSetChanged();
    }
    
    private void onClickRegister(){
        card_course_register_adapter.setOnClickRegister(new Listener() {
            @Override
            public void lintener(int position) {
                adapter = new Student_Course_Register_Adapter(studentEntities);
                final CourseEntity courseEntity = courseEntities.get(position);
                final StudentEntity[] studentEntity = {null};
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context,R.style.bottomsheetstyle);
                View viewBottomSheet = LayoutInflater.from(context).inflate(R.layout.layout_bottom_sheet_course,null,false);

                recyclerStudent = viewBottomSheet.findViewById(R.id.recycler_student);
                btn_register = viewBottomSheet.findViewById(R.id.btn_register);
                setCardCourse(courseEntity,viewBottomSheet);
                recyclerStudent.setAdapter(adapter);
                recyclerStudent.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));

                adapter.setOnClickCardStudent(new Listener() {
                    @Override
                    public void lintener(int position) {
                        studentEntity[0] = adapter.getSingleStudentEntity(position);
                        CardView cardStudent;
                        View viewItem;
                        if (position == previousPosition) {
                            if (recyclerStudent.findViewHolderForAdapterPosition(position) != null) {
                                viewItem = recyclerStudent.findViewHolderForAdapterPosition(position).itemView;
                                cardStudent = (CardView) viewItem.findViewById(R.id.card_student);
                                cardStudent.setBackgroundResource(R.color.card);
                                previousPosition = position;
                            }
                        } else {
                            viewItem = recyclerStudent.findViewHolderForAdapterPosition(previousPosition).itemView;
                            cardStudent = (CardView) viewItem.findViewById(R.id.card_student);
                            cardStudent.setBackgroundColor(Color.WHITE);

                            viewItem = recyclerStudent.findViewHolderForAdapterPosition(position).itemView;
                            cardStudent = (CardView) viewItem.findViewById(R.id.card_student);
                            cardStudent.setBackgroundResource(R.color.card);
                            previousPosition = position;
                        }
                    }
                });

                btn_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(studentEntity[0] != null){
                            MyCourseEntity myCourseEntity = new MyCourseEntity(courseEntity.getName(),courseEntity.getImage(),courseEntity.getRate(),courseEntity.getByAuthor(),studentEntity[0].getId(),studentEntity[0].getName());
                            new MyCourseResponsive(context).insert(myCourseEntity);
                            bottomSheetDialog.dismiss();
                        }
                    }
                });

                bottomSheetDialog.setContentView(viewBottomSheet);
                bottomSheetDialog.show();
            }
        });
    }

    private void setCardCourse(CourseEntity courseEntity, View viewBottomSheet){
        txByAuthor = viewBottomSheet.findViewById(R.id.tx_author);
        txName = viewBottomSheet.findViewById(R.id.tx_name);
        cardCourse = viewBottomSheet.findViewById(R.id.card_course);
        linearStar = viewBottomSheet.findViewById(R.id.linear_star);
        txByAuthor.setText(courseEntity.getByAuthor());
        txName.setText(courseEntity.getName());
        cardCourse.setBackgroundResource(courseEntity.getImage());
        switch (Rate.valueOf(courseEntity.getRate())){
            case BAD:
                card_course_register_adapter.rates(linearStar,1);
                break;
            case NORMAL:
                card_course_register_adapter.rates(linearStar,2);
                break;
            case GOOD:
                card_course_register_adapter.rates(linearStar,3);
                break;
            case VERYGOOD:
                card_course_register_adapter.rates(linearStar,4);
                break;
            case EXCELLENT:
                card_course_register_adapter.rates(linearStar,5);
                break;
        }
    }


}
