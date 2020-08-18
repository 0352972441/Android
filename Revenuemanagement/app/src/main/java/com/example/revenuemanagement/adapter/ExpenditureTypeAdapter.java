package com.example.revenuemanagement.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revenuemanagement.R;
import com.example.revenuemanagement.entity.ExpenditureType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExpenditureTypeAdapter extends RecyclerView.Adapter<ExpenditureTypeAdapter.ViewHolder> {
    private List<ExpenditureType> listExpenditureType;
    private ItemOnListener listener;
    private ItemOnListener listenerDelete;

    public ExpenditureTypeAdapter() {
        listExpenditureType = new ArrayList<>();
    }

    public void setListener(ItemOnListener listener) {
        this.listener = listener;
    }

    public void setListenerDelete(ItemOnListener listenerDelete) {
        this.listenerDelete = listenerDelete;
    }

    public ExpenditureType getExpenditureType(int position){
        return listExpenditureType.get(position);
    }

    public List<ExpenditureType> getListExpenditureType() {
        return listExpenditureType;
    }

    public void setListExpenditureType(List<ExpenditureType> listExpenditureType) {
        this.listExpenditureType = listExpenditureType;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_expenditure_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(listExpenditureType != null){
            holder.mtitle.setText(listExpenditureType.get(position).getTitle());
            holder.mimage.setImageResource(listExpenditureType.get(position).getImgCategory());
            holder.mdescription.setText(listExpenditureType.get(position).getDescription());
            holder.mupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.listener(position);
                }
            });

            holder.mdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerDelete.listener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listExpenditureType.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mtitle,mdescription;
        private ImageView mimage;
        private CardView cardView;
        private ImageView mdelete,mupdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_view_expenditure_type);
            mtitle = (TextView)itemView.findViewById(R.id.mtitle);
            mimage = (ImageView) itemView.findViewById(R.id.mcategory);
            mdescription = (TextView)itemView.findViewById(R.id.mdescription);
            mdelete = (ImageView) itemView.findViewById(R.id.mimagedelete);
            mupdate = (ImageView) itemView.findViewById(R.id.mimageupdate);
        }
    }
}
