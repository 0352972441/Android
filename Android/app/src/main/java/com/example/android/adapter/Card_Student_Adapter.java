package com.example.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.Activity.courseactivity.StudentActivity;
import com.example.android.R;
import com.example.android.entity.StudentEntity;
import com.example.android.listener.Listener;

import java.util.List;

public class Card_Student_Adapter extends RecyclerView.Adapter<Card_Student_Adapter.ViewHolder> {
    private List<StudentEntity> studentEntities;
    private Listener onClickShowMenu;
    public Card_Student_Adapter(List<StudentEntity> studentEntities) {
        this.studentEntities = studentEntities;
    }

    public void setOnClickShowMenu(Listener onClickShowMenu) {
        this.onClickShowMenu = onClickShowMenu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(studentEntities!= null){
            StudentEntity entity = studentEntities.get(position);
            if(entity.isGender()){
                holder.img_gender.setImageResource(R.drawable.male);
                holder.img_avata.setImageResource(R.drawable.student);
                // set giới tính nam
            }else{
                // set gới tính nữ
                holder.img_gender.setImageResource(R.drawable.female);
                holder.img_avata.setImageResource(R.drawable.student_female);
            }
            holder.txName.setText(entity.getName());
            holder.txDate.setText(entity.getDayOfBirth());
            holder.txRegistrationDate.setText(entity.getRegisterDate());
            StudentActivity.registerForContextMenus(holder.img_menu);
            holder.img_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickShowMenu.lintener(position);
                    v.showContextMenu();
                }
            });
        }
    }

    public StudentEntity getSingleStudentEntity(int position){
        return studentEntities.get(position);
    }

    @Override
    public int getItemCount() {
        return studentEntities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardStudent;
        private ImageView img_avata,img_gender,img_menu;
        private TextView txName,txDate,txRegistrationDate;
        public ViewHolder(@NonNull View view) {
            super(view);
            cardStudent = (CardView)view.findViewById(R.id.card_student);

            img_avata = (ImageView)view.findViewById(R.id.img_avata);
            img_gender = view.findViewById(R.id.img_gender);
            img_menu = view.findViewById(R.id.img_menu);

            txName = view.findViewById(R.id.tx_name);
            txDate = view.findViewById(R.id.date);
            txRegistrationDate = view.findViewById(R.id.registration_date);
        }
    }
}
