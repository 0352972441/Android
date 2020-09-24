package com.example.revenuemanagement.ui.Revenue;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.revenuemanagement.adapter.RevenueTypeAdapter;
import com.example.revenuemanagement.dialog.RevenueTypeDialog;
import com.example.revenuemanagement.entity.RevenueType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class RevenueTypeFragment extends Fragment {

    private RevenueTypeViewModel mViewModel;
    private RevenueTypeAdapter adapter;
    private RevenueTypeDialog revenueTypeDialog;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.revenue_type_fragment, container, false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.revenueType_Recyclerview);
        adapter = new RevenueTypeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revenueTypeDialog = new RevenueTypeDialog();
                revenueTypeDialog.revenueTypeDialog(view.getContext(), mViewModel,false);
            }
        });
        adapter.setListenerDelete(new ItemOnListener() {
            @Override
            public void listener(int position) {
                final int index = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Are you sure want to delete ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mViewModel.delete(adapter.getSingleRevenueType(index));
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        adapter.setListenerUpdate(new ItemOnListener() {
            @Override
            public void listener(int position) {
                revenueTypeDialog = new RevenueTypeDialog(adapter.getSingleRevenueType(position));
                revenueTypeDialog.revenueTypeDialog(getContext(),mViewModel, true);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RevenueTypeViewModel.class);
        mViewModel.getGetAll().observe(getActivity(), new Observer<List<RevenueType>>() {
            @Override
            public void onChanged(List<RevenueType> revenueTypes) {
                adapter.setExpenditureTypeList(revenueTypes);
            }
        });
    }
}
