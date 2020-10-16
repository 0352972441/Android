package com.example.android.Activity.courseactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.android.R;
import com.example.android.adapter.Card_Student_Adapter;
import com.example.android.dialog.ShowDatePicker;
import com.example.android.entity.StudentEntity;
import com.example.android.listener.Listener;
import com.example.android.responsive.StudentResponsive;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
enum Type {
    INSERT,UPDATE,DELETE
}
public class StudentActivity extends AppCompatActivity {
    StudentResponsive studentResponsive;
    private RecyclerView recyclerStudent;
    private Card_Student_Adapter student_adapter;
    private List<StudentEntity> studentEntityList;
    private FloatingActionButton fab;
    private EditText edName,edDateOfBirth;
    private  ImageView imgMale,img_Menu;
    private ImageView imgFemale;
    private boolean isMale = false;
    private static Context context;
    private BottomSheetDialog bottomSheetDialog;
    private View view_Bottom_Sheet;
    private  Button btnSave;
    private Type type = Type.INSERT;
    private static Activity activity;
    private static int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        context = this;
        activity = this;
        studentResponsive = new StudentResponsive(this);
        studentEntityList = studentResponsive.getStudentEntityArrayList();
        recyclerStudent = findViewById(R.id.recycler_student);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = Type.INSERT;
                showBottomDialog();
            }
        });
        student_adapter = new Card_Student_Adapter(studentEntityList);
        recyclerStudent.setAdapter(student_adapter);
        recyclerStudent.setLayoutManager(new GridLayoutManager(this,2));
        student_adapter.setOnClickShowMenu(new Listener() {
            @Override
            public void lintener(int position) {
                index = position;
            }
        });
    }

    public static void registerForContextMenus(View view){
        activity.registerForContextMenu(view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.update){
            type = Type.UPDATE;
            showBottomDialog();
        }else if(item.getItemId() == R.id.delete){
            type = Type.DELETE;
            studentResponsive.delete(student_adapter.getSingleStudentEntity(index));
            Toast.makeText(StudentActivity.this, "Delete", Toast.LENGTH_SHORT).show();
            studentEntityList.remove(index);
            recyclerStudent.removeView(recyclerStudent.getChildAt(index));
            student_adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }

    private void showBottomDialog(){
        setUpWidget();

        final StudentEntity studentEntity = new StudentEntity();
        imgFemale.setImageResource(R.drawable.female_color);
        edDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDatePicker.showDate(context,edDateOfBirth);
            }
        });
        imgFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMale = false;
                imgFemale.setImageResource(R.drawable.female_color);
                imgMale.setImageResource(R.drawable.male);
            }
        });
        imgMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMale = true;
                imgMale.setImageResource(R.drawable.male_color);
                imgFemale.setImageResource(R.drawable.female);
            }
        });

        @SuppressLint("SimpleDateFormat") final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudentActivity.this, "Save", Toast.LENGTH_SHORT).show();
                String name = edName.getText().toString();
                String dateOfBirth = edDateOfBirth.getText().toString();
                if(!name.isEmpty() && !dateOfBirth.isEmpty()){
                    studentEntity.setGender(isMale);
                    studentEntity.setName(name);
                    studentEntity.setDayOfBirth(dateOfBirth);
                    if(type == Type.INSERT){
                        studentEntity.setRegisterDate(simpleDateFormat.format(Calendar.getInstance().getTime()));
                        studentResponsive.insert(studentEntity);
                        studentEntityList.add(studentEntity);
                        student_adapter.notifyDataSetChanged();
                    }else if(type == Type.UPDATE){
                        StudentEntity student = student_adapter.getSingleStudentEntity(index);
                        int id = student.getId();
                        String registrationDate = student.getRegisterDate();
                        studentEntity.setId(id);
                        studentEntity.setRegisterDate(registrationDate);
                        studentResponsive.update(studentEntity);
                        studentEntityList.set(index,studentEntity);
                        student_adapter.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(StudentActivity.this, "Insert failed", Toast.LENGTH_SHORT).show();
                }
                bottomSheetDialog.dismiss();
            }
        });
            bottomSheetDialog.show();
            bottomSheetDialog.setContentView(view_Bottom_Sheet);
    }

    private void setUpWidget(){
        bottomSheetDialog = new BottomSheetDialog(StudentActivity.this,R.style.bottomsheetstyle);
        view_Bottom_Sheet = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_bottom_sheet, (RelativeLayout)findViewById(R.id.bottom_sheet_container),false);
        btnSave = view_Bottom_Sheet.findViewById(R.id.btn_add);
        imgMale = view_Bottom_Sheet.findViewById(R.id.img_male);
        imgFemale = view_Bottom_Sheet.findViewById(R.id.img_female);
        img_Menu = view_Bottom_Sheet.findViewById(R.id.img_menu);
        edName = (EditText)view_Bottom_Sheet.findViewById(R.id.ed_name);
        edDateOfBirth = (EditText) view_Bottom_Sheet.findViewById(R.id.ed_date_of_birth);
    }

}
