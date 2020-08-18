package com.example.revenuemanagement.ui.Expenditures;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.revenuemanagement.R;
import com.example.revenuemanagement.adapter.ExpenditureAdapter;
import com.example.revenuemanagement.adapter.ItemOnListener;
import com.example.revenuemanagement.dialog.ExpenditureDialog;
import com.example.revenuemanagement.entity.ExpenditureType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Expenditure extends Fragment {
    /*private FrameLayout parentLayout;
    private ImageButton mupdate, mdelete;*/
    //
    private List<ExpenditureType> expenditureTypeList;
    private ExpensesTypeViewModel expensesTypeViewModel;
    private ExpenditureViewModel mViewModel;
    private RecyclerView mRecyclerView_Expenditure;
    private ExpenditureAdapter adapter;
    private FloatingActionButton mFloatAdd;
    private ExpenditureDialog dialog;
    private com.example.revenuemanagement.entity.Expenditure expenditure = null;
    public static Expenditure newInstance() {
        return new Expenditure();
    }
    // Bộ lọc
    private static boolean isPurchase;
    private static boolean isSalary;
    private static boolean isAd;
    private static boolean isOutfit;

    public static void setIsPurchase(boolean isPurchase) {
        Expenditure.isPurchase = isPurchase;
    }

    public static boolean isIsPurchase() {
        return isPurchase;
    }

    public static boolean isIsSalary() {
        return isSalary;
    }

    public static void setIsSalary(boolean isSalary) {
        Expenditure.isSalary = isSalary;
    }

    public static boolean isIsAd() {
        return isAd;
    }

    public static void setIsAd(boolean isAd) {
        Expenditure.isAd = isAd;
    }

    public static boolean isIsOutfit() {
        return isOutfit;
    }

    public static void setIsOutfit(boolean isOutfit) {
        Expenditure.isOutfit = isOutfit;
    }
    // Bộ lọc

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView_Expenditure = (RecyclerView)view.findViewById(R.id.recyclerview_expenditure);
        adapter = new ExpenditureAdapter();
        mRecyclerView_Expenditure.setAdapter(adapter);
        mRecyclerView_Expenditure.setLayoutManager(new GridLayoutManager(getContext(),1));


        mFloatAdd = (FloatingActionButton)view.findViewById(R.id.fab);
        mFloatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ExpenditureDialog(expenditureTypeList);
                dialog.showDialog(v.getContext(),mViewModel,false);
            }
        });


        adapter.setListener(new ItemOnListener() {
            @Override
            public void listener(int position) {
                expenditure = adapter.getExpenditure(position);
                dialog = new ExpenditureDialog(expenditure,expenditureTypeList);
                dialog.showDialog(getContext(),mViewModel,true);
            }
        });
        adapter.setListenerDelete(new ItemOnListener() {
            @Override
            public void listener(int position) {
                final int index = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Are you sure want to delete");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mViewModel.delete(adapter.getExpenditure(index));
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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.expenditure_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ExpenditureViewModel.class);
        expensesTypeViewModel = new ViewModelProvider(this).get(ExpensesTypeViewModel.class);
        expensesTypeViewModel.getGetAll().observe(getViewLifecycleOwner(), new Observer<List<ExpenditureType>>() {
            @Override
            public void onChanged(List<ExpenditureType> expenditureTypes) {
                expenditureTypeList = expenditureTypes;
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        String categoryPurchase="", categoryAd="",categorySalary = "",categoryOutfit="";
        if(isAd){
            categoryAd = "Advertisement";
        }
        if(isSalary){
            categorySalary = "Salary";
        }
        if(isOutfit){
            categoryOutfit = "Outfit";
        }
        if(isPurchase){
            categoryPurchase = "Purchase";
        }
        if(categoryAd.equals("") && categoryOutfit.equals("")&&categoryPurchase.equals("")&& categorySalary.equals("")){
            mViewModel.getGetAll().observe(getViewLifecycleOwner(), new Observer<List<com.example.revenuemanagement.entity.Expenditure>>() {
                @Override
                public void onChanged(List<com.example.revenuemanagement.entity.Expenditure> expenditures) {
                    adapter.setListExpenditure(expenditures);
                }
            });
        }else{
            mViewModel.getAllCategory(categorySalary,categoryAd,categoryPurchase,categoryOutfit).observe(getViewLifecycleOwner(), new Observer<List<com.example.revenuemanagement.entity.Expenditure>>() {
                @Override
                public void onChanged(List<com.example.revenuemanagement.entity.Expenditure> expenditures) {
                    adapter.setListExpenditure(expenditures);
                }
            });
        }
    }
}
