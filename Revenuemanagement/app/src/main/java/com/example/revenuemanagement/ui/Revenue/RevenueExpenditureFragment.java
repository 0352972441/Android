package com.example.revenuemanagement.ui.Revenue;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.revenuemanagement.R;
import com.example.revenuemanagement.adapter.ItemOnListener;
import com.example.revenuemanagement.adapter.RevenueAdapter;
import com.example.revenuemanagement.dialog.RevenueDialog;
import com.example.revenuemanagement.dialog.RevenueTypeDialog;
import com.example.revenuemanagement.entity.Revenue;
import com.example.revenuemanagement.entity.RevenueType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RevenueExpenditureFragment extends Fragment {
    private RevenueDialog revenueDialog;
    private RevenueExpenditureViewModel mViewModel;
    private RecyclerView mRevenueView;
    private RevenueAdapter recycler;
    //
    private List<RevenueType> revenueTypeList;
    private RevenueTypeViewModel model;
    //
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.revenue_expenditure_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        mRevenueView = (RecyclerView)view.findViewById(R.id.revenue_Recyclerview);
        recycler = new RevenueAdapter();
        recycler.setListener(new ItemOnListener() {
            @Override
            public void listener(int position) {
                revenueDialog = new RevenueDialog(revenueTypeList);
                revenueDialog.revenueDialog(getContext(), mViewModel, recycler.getRevunue(position),true);
            }
        });
        mRevenueView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRevenueView.setAdapter(recycler);
        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revenueDialog = new RevenueDialog(revenueTypeList);
                revenueDialog.revenueDialog(getContext(),mViewModel,null, false);
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                final RecyclerView.ViewHolder viewHolder1 = viewHolder;
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Are you sure want to delete ?");
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            recycler.notifyDataSetChanged();
                        }
                    });
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mViewModel.delete(recycler.getRevunue(viewHolder1.getAdapterPosition()));
                            Toast.makeText(getContext(), "Delete successful", Toast.LENGTH_SHORT).show();
                            recycler.notifyDataSetChanged();
                        }
                    });
                    builder.show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRevenueView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new  ViewModelProvider(this).get(RevenueExpenditureViewModel.class);
        model = new ViewModelProvider(this).get(RevenueTypeViewModel.class);
        mViewModel.getGetAll().observe(getActivity(), new Observer<List<Revenue>>() {
            @Override
            public void onChanged(List<Revenue> revenues) {
                recycler.setListDataRevenue(revenues);
            }
        });

        model.getGetAll().observe(getViewLifecycleOwner(), new Observer<List<RevenueType>>() {
            @Override
            public void onChanged(List<RevenueType> revenueTypes) {
                revenueTypeList = revenueTypes;
            }
        });
    }
}
