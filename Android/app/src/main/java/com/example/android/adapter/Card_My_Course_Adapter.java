package com.example.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.entity.MyCourseEntity;
import com.example.android.models.Rate;
import java.util.List;

public class Card_My_Course_Adapter extends RecyclerView.Adapter<Card_My_Course_Adapter.ViewHolder> {
    private List<MyCourseEntity> courseRegisters;

    public Card_My_Course_Adapter(List<MyCourseEntity> courseRegisters) {
        this.courseRegisters = courseRegisters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_my_course,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(courseRegisters != null){
            MyCourseEntity courseRegister = courseRegisters.get(position);
            holder.txTitle.setText(courseRegister.getName());
            holder.txByAuthor.setText("By "+courseRegister.getByAuthor());
            holder.img_Couser.setImageResource(courseRegister.getImage());
            holder.txNameStudent.setText("Name"+courseRegister.getStudentName());
            holder.txId.setText("Student code:"+courseRegister.getStudentId());
            switch (Rate.valueOf(courseRegister.getRate())){
                case BAD:
                    rates(holder.rates,1);
                    break;
                case NORMAL:
                    rates(holder.rates,2);
                    break;
                case GOOD:
                    rates(holder.rates,3);
                    break;
                case VERYGOOD:
                    rates(holder.rates,4);
                    break;
                case EXCELLENT:
                    rates(holder.rates,5);
                    break;
            }
        }
    }

    private void rates(LinearLayout layoutRate, int rate){
        for(int i=0; i<rate; i++){
            ImageView star = new ImageView(layoutRate.getContext());
            star.setImageResource(R.drawable.ic_star_black_24dp);
            layoutRate.addView(star);
        }
    }

    @Override
    public int getItemCount() {
        return courseRegisters.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txTitle, txByAuthor,txNameStudent,txId;
        //private Button btnRegister;
        private LinearLayout rates;
        private ImageView img_Couser;

        public ViewHolder(@NonNull View view) {
            super(view);
            img_Couser = (ImageView) view.findViewById(R.id.img_course);
            txTitle = (TextView) view.findViewById(R.id.tx_course_title);
            txNameStudent = view.findViewById(R.id.tx_name_student);
            txId = view.findViewById(R.id.tx_id);
            txByAuthor = (TextView) view.findViewById(R.id.tx_author);
            //btnRegister = (Button) view.findViewById(R.id.btn_course_register);
            rates = (LinearLayout) view.findViewById(R.id.rates);
        }
    }
}
