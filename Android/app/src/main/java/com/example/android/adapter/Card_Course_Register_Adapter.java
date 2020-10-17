package com.example.android.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.R;
import com.example.android.entity.CourseEntity;
import com.example.android.entity.MyCourseEntity;
import com.example.android.listener.Listener;
import com.example.android.models.CourseRegister;
import com.example.android.models.Rate;
import com.example.android.responsive.CourseResponsive;
import com.example.android.responsive.MyCourseResponsive;
import java.util.List;

public class Card_Course_Register_Adapter extends RecyclerView.Adapter<Card_Course_Register_Adapter.ViewHolder> {
    private List<CourseEntity> courseRegisters;
    private Listener onCLickCardCourseListener;
    private Listener onClickRegister;

    public void setOnClickRegister(Listener onClickRegister) {
        this.onClickRegister = onClickRegister;
    }

    public Card_Course_Register_Adapter(List<CourseEntity> courseRegisters) {
        this.courseRegisters = courseRegisters;
    }

    public void setOnCLickCardCourseListener(Listener onCLickCardCourseListener) {
        this.onCLickCardCourseListener = onCLickCardCourseListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_course_registration,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(courseRegisters != null){
            final CourseEntity courseRegister = courseRegisters.get(position);
            holder.txTitle.setText(courseRegister.getName());
            holder.txByAuthor.setText("By "+courseRegister.getByAuthor());
            holder.img_Couser.setImageResource(courseRegister.getImage());
            holder.btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickRegister.lintener(position);
                }
            });
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
            holder.cardCourseRegister.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onCLickCardCourseListener.lintener(position);
                    return true;
                }
            });
        }
    }

    public void rates(LinearLayout layoutRate,int rate){
        for(int i=0; i<rate; i++){
            ImageView star = new ImageView(layoutRate.getContext());
            star.setImageResource(R.drawable.ic_star_black_24dp);
            layoutRate.addView(star);
        }
    }

    public CourseEntity getSingleCourse(int index){
        return courseRegisters.get(index);
    }

    @Override
    public int getItemCount() {
        return courseRegisters.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txTitle,txByAuthor;
        private Button btnRegister;
        private LinearLayout rates;
        private ImageView img_Couser;
        private CardView cardCourseRegister;
        public ViewHolder(@NonNull View view) {
            super(view);
            img_Couser = (ImageView)view.findViewById(R.id.img_course);
            txTitle = (TextView)view.findViewById(R.id.tx_course_title);
            txByAuthor = (TextView)view.findViewById(R.id.tx_author);
            btnRegister = (Button) view.findViewById(R.id.btn_course_register);
            rates = (LinearLayout) view.findViewById(R.id.rates);
            cardCourseRegister = (CardView)view.findViewById(R.id.card_course_register);
        }
    }
}
