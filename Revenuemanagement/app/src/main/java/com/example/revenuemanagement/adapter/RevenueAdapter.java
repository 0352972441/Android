package com.example.revenuemanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.revenuemanagement.R;
import com.example.revenuemanagement.entity.Revenue;
import com.example.revenuemanagement.entity.RevenueType;
import java.util.ArrayList;
import java.util.List;

public class RevenueAdapter extends RecyclerView.Adapter<RevenueAdapter.ViewHolder>{
    private enum Model {isOpen, isClose};
    private RevenueAdapter.Model model = RevenueAdapter.Model.isClose;
    private boolean isType = true;// Kiểm tra Revenue or RevenueType
    private List<Revenue> listDataRevenue;
    private List<RevenueType> listDataRevenueType;// = new ArrayList<>();
    private ItemOnListener listener;


    public void setListener(ItemOnListener listener) {
        this.listener = listener;
    }

    public RevenueAdapter(Boolean isType) {
        this.isType = isType;
        listDataRevenue = new ArrayList<>();
        listDataRevenueType = new ArrayList<>();
    }


    public void setListDataRevenue(List<Revenue> listDataRevenue) {
        this.listDataRevenue = listDataRevenue;
        notifyDataSetChanged();
    }
    public void setListData(List<RevenueType> listData) {
        this.listDataRevenueType = listData;
        notifyDataSetChanged();
    }

    public Revenue getRevunue(int position){
        return listDataRevenue.get(position);
    }
    public RevenueType getRevenueType(int position){
        return listDataRevenueType.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.revenue_type_cardview,parent,false);
        return new RevenueAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(listDataRevenue != null){
            if (isType) {
                holder.title.setText(listDataRevenueType.get(position).getTitle());
                holder.description.setText(listDataRevenueType.get(position).getDescription());
                holder.mMoney.setText(String.valueOf(listDataRevenueType.get(position).getMoney())+"$");
                holder.mTime.setText(listDataRevenueType.get(position).getDate());
            } else {
                holder.title.setText(listDataRevenue.get(position).getTitle());
                holder.description.setText(listDataRevenue.get(position).getDescription());
                holder.mMoney.setText(String.valueOf(listDataRevenue.get(position).getMoney())+"$");
                System.out.println(listDataRevenue.get(position).getDate());
                holder.mTime.setText(listDataRevenue.get(position).getDate());
            }
            holder.imageEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.listener(position);
                }
            });
            holder.drop_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewGroup.LayoutParams params = holder.cardView.getLayoutParams();
                    if(model == RevenueAdapter.Model.isClose) {
                        holder.imageEdit.setVisibility(View.VISIBLE);
                        holder.mContentLinear.setVisibility(View.VISIBLE);
                        params.height = 500;
                        holder.cardView.setLayoutParams(params);
                        model = RevenueAdapter.Model.isOpen;
                        holder.drop_down.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                        //holder.description.setVisibility(View.VISIBLE);
                    }else {
                        holder.imageEdit.setVisibility(View.INVISIBLE);
                        holder.mContentLinear.setVisibility(View.INVISIBLE);
                        params.height = 220;
                        holder.cardView.setLayoutParams(params);
                        model = RevenueAdapter.Model.isClose;
                        holder.drop_down.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        //holder.description.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return isType ? listDataRevenueType.size() :listDataRevenue.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,description,mMoney,mTime;
        ImageView imageEdit,drop_down;
        LinearLayout mContentLinear;
        CardView cardView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTime = (TextView)itemView.findViewById(R.id.mtime);
            mContentLinear = (LinearLayout)itemView.findViewById(R.id.contentLayout) ;
            title = (TextView)itemView.findViewById(R.id.text);
            mMoney = (TextView) itemView.findViewById(R.id.money);
            imageEdit = (ImageView) itemView.findViewById(R.id.imageEdit);
            drop_down = (ImageView) itemView.findViewById(R.id.drop_down);
            description = (TextView)itemView.findViewById(R.id.mdescription);
            cardView = (CardView)itemView;
        }
    }
}
