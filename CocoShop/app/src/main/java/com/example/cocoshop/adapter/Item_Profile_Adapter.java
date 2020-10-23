package com.example.cocoshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.R;
import com.example.cocoshop.listener.Listener;

public class Item_Profile_Adapter extends RecyclerView.Adapter<Item_Profile_Adapter.ViewHolder>{
    //private static final String[] titleItem = {"Từ vựng", "Thông tin tài khoản", "Điều khoản", "Điều khoản sử dụng", "Đăng xuất"};
    private static final int[] label = {R.string.label_title_vocabulary,R.string.label_title_account_information,R.string.label_title_policy,R.string.label_title_terms_of_use,R.string.label_title_logout};
    private Listener onClickItemListener;

    public void setOnClickItemListener(Listener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (label != null){
            holder.txTitle.setText(label[position]);
            holder.layoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItemListener.listener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return label.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layoutItem;
        private TextView txTitle;

        public ViewHolder(@NonNull View view) {
            super(view);
            layoutItem = (LinearLayout)view.findViewById(R.id.item_layout);
            txTitle = (TextView)view.findViewById(R.id.title_item);
        }
    }
}
