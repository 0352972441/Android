package com.example.revenuemanagement.ui.Revenue;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.revenuemanagement.R;
import com.example.revenuemanagement.adapter.ItemOnListener;
import com.example.revenuemanagement.adapter.RevenueAdapter;
import com.example.revenuemanagement.dialog.RevenueTypeDialog;
import com.example.revenuemanagement.entity.RevenueType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class RevenueTypeFragment extends Fragment {

    private RevenueTypeViewModel mViewModel;
    RevenueAdapter recycler;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.revenue_type_fragment, container, false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.revenueType_Recyclerview);
        recycler = new RevenueAdapter(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(recycler);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RevenueTypeDialog().revenueTypeDialog(view.getContext(), mViewModel,null);
            }
        });
        recycler.setListener(new ItemOnListener() {
            @Override
            public void listener(int position) {
                new RevenueTypeDialog(true).revenueTypeDialog(getContext(), mViewModel,recycler.getRevenueType(position));
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mViewModel.delete(recycler.getRevenueType(viewHolder.getAdapterPosition()));
                recycler.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RevenueTypeViewModel.class);
        mViewModel.getGetAll().observe(getActivity(), new Observer<List<RevenueType>>() {
            @Override
            public void onChanged(List<RevenueType> revenueTypes) {
                recycler.setListData(revenueTypes);
            }
        });
    }
}
