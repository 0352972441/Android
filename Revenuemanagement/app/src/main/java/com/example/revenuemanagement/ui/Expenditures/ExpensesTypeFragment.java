package com.example.revenuemanagement.ui.Expenditures;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.revenuemanagement.R;
import com.example.revenuemanagement.adapter.ExpenditureTypeAdapter;
import com.example.revenuemanagement.adapter.ItemOnListener;
import com.example.revenuemanagement.dialog.ExpenditureTypeDialog;
import com.example.revenuemanagement.entity.ExpenditureType;
import com.example.revenuemanagement.ui.SettingActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ExpensesTypeFragment extends Fragment {

    private ExpensesTypeViewModel mViewModel;
    private RecyclerView mRecyclerExpenditureType;
    private ExpenditureTypeAdapter adapter;
    private ExpenditureTypeDialog expenditureTypeDialog;
    public static ExpensesTypeFragment newInstance() {
        return new ExpensesTypeFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter =  new ExpenditureTypeAdapter();
        mRecyclerExpenditureType = (RecyclerView)view.findViewById(R.id.recycler_view_expenditure_type);
        mRecyclerExpenditureType.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerExpenditureType.setAdapter(adapter);
        FloatingActionButton mAdd = (FloatingActionButton)view.findViewById(R.id.fab);
        mAdd.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                expenditureTypeDialog = new ExpenditureTypeDialog();
                expenditureTypeDialog.showDialogExpenditureType(getContext(),mViewModel,false);
            }
        });
        adapter.setListener(new ItemOnListener() {
            @Override
            public void listener(int position) {
                expenditureTypeDialog = new ExpenditureTypeDialog(adapter.getExpenditureType(position));
            }
        });
        adapter.setListenerDelete(new ItemOnListener() {
            @Override
            public void listener(int position) {
                final int index = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Are you sure want to delete ?");
                builder.setIcon(R.drawable.icon_delete_cardview_green);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mViewModel.delete(adapter.getExpenditureType(index));
                    }
                });
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.expenses_type_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ExpensesTypeViewModel.class);
        mViewModel.getGetAll().observe(getViewLifecycleOwner(), new Observer<List<ExpenditureType>>() {
            @Override
            public void onChanged(List<ExpenditureType> expenditureType) {
                adapter.setListExpenditureType(expenditureType);
            }
        });
    }
}
