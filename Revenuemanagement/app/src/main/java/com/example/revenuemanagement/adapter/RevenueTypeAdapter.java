package com.example.revenuemanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revenuemanagement.R;
import com.example.revenuemanagement.entity.RevenueType;

import java.util.ArrayList;
import java.util.List;

public class RevenueTypeAdapter extends RecyclerView.Adapter<RevenueTypeAdapter.ViewHolder> {
    private List<RevenueType> expenditureTypeList;
    private ItemOnListener listenerDelete;
    private ItemOnListener listenerUpdate;

    public void setListenerDelete(ItemOnListener listenerDelete) {
        this.listenerDelete = listenerDelete;
    }

    public void setListenerUpdate(ItemOnListener listenerUpdate) {
        this.listenerUpdate = listenerUpdate;
    }

    public void setExpenditureTypeList(List<RevenueType> expenditureTypeList) {
        this.expenditureTypeList = expenditureTypeList;
        notifyDataSetChanged();
    }

    public RevenueTypeAdapter() {
        expenditureTypeList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_revenue_type, parent, false);

        return new ViewHolder(view);
    }

    public RevenueType getSingleRevenueType(int position){
        return expenditureTypeList.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(expenditureTypeList != null){
            holder.mtitle.setText(expenditureTypeList.get(position).getTitle());
            holder.mupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerUpdate.listener(position);
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
        return expenditureTypeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mtitle;
        private ImageView mdelete;
        private ImageView mupdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtitle = (TextView)itemView.findViewById(R.id.mtitle);
            mdelete = (ImageView) itemView.findViewById(R.id.mimagedelete);
            mupdate = (ImageView)itemView.findViewById(R.id.mimageupdate);

        }
    }
}
