package com.example.revenuemanagement.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revenuemanagement.R;
import com.example.revenuemanagement.entity.Expenditure;

import java.util.ArrayList;
import java.util.List;

public class ExpenditureAdapter extends RecyclerView.Adapter<ExpenditureAdapter.ViewHolder>{

    private List<Expenditure> listExpenditure;
    private enum Model{Expenditure, ExpenditureType}
    private Model isExpenditure = Model.Expenditure;
    private ItemOnListener listener;
    private ItemOnListener listenerDelete;
    public ExpenditureAdapter() {
        listExpenditure = new ArrayList<>();
    }

    public void setListExpenditure(List<Expenditure> listExpenditure) {
        this.listExpenditure = listExpenditure;
        notifyDataSetChanged();
    }

    public void setListener(ItemOnListener listener) {
        this.listener = listener;
    }

    public void setListenerDelete(ItemOnListener listenerDelete) {
        this.listenerDelete = listenerDelete;
    }

    public Expenditure getExpenditure(int position){
        return listExpenditure.get(position);
    }

    public List<Expenditure> getListExpenditure() {
        return listExpenditure;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_expenditure,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(isExpenditure == Model.Expenditure){
            if(listExpenditure != null) {
                holder.mTitle.setText(listExpenditure.get(position).getTitle());
                holder.mDescription.setText(listExpenditure.get(position).getDescription());
                holder.mDate.setText(listExpenditure.get(position).getDate());
                holder.mMoney.setText(String.valueOf(listExpenditure.get(position).getMoney()));
                holder.mCategory.setImageResource(listExpenditure.get(position).getImage());
                holder.header.setBackgroundColor(listExpenditure.get(position).getColor());
                System.out.println("===================\n"+"Giá trị ở đây: "+listExpenditure.get(position).getImage() +"Giá trị tiếp theo:"+R.drawable.image_12);
            }
        }
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            Button mupdate,mdelete;
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog builder = new AlertDialog.Builder(v.getContext()).create();
                View main = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_cardview_update,null,false);
                mupdate = (Button)main.findViewById(R.id.mupdate);
                mdelete = (Button)main.findViewById(R.id.mdelete);
                mupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.listener(position);
                        builder.dismiss();
                    }
                });
                mdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listenerDelete.listener(position);
                        builder.dismiss();
                    }
                });
                builder.setView(main);
                builder.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listExpenditure.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTitle,mDescription,mMoney, mDate;
        private ImageView mCategory;
        private RelativeLayout header;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            header = (RelativeLayout)itemView.findViewById(R.id.header);
            cardView = (CardView)itemView.findViewById(R.id.card_view_expenditure);
            mTitle = (TextView)itemView.findViewById(R.id.mtitle);
            mDescription = (TextView)itemView.findViewById(R.id.mdescription);
            mDate = (TextView)itemView.findViewById(R.id.mdate);
            mMoney = (TextView)itemView.findViewById(R.id.mmoney);
            mCategory = (ImageView) itemView.findViewById(R.id.mkind);
        }
    }
}
