package com.example.revenuemanagement.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.revenuemanagement.R;
import com.example.revenuemanagement.entity.ExpenditureType;
import com.example.revenuemanagement.entity.Revenue;
import com.example.revenuemanagement.entity.RevenueType;
import com.example.revenuemanagement.ui.Revenue.RevenueExpenditureViewModel;
import com.example.revenuemanagement.ui.Revenue.RevenueTypeViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class RevenueDialog {
    private static final String TITLETIME = "Please choose day";
    private AlertDialog.Builder dialog;
    private View view;
    private TextInputLayout mTitle, mdescription,mMoney;
    private TextView mtime;
    private RevenueExpenditureViewModel revenueModel;
    private Spinner spinner_revenue;
    private List<RevenueType> expenditureTypeList;
    //private boolean isUpdate = false;


    public RevenueDialog(List<RevenueType> expenditureTypeList) {
        this.expenditureTypeList = expenditureTypeList;
    }

    public void revenueDialog(final Context context, final RevenueExpenditureViewModel revenueModel, final Revenue revenueOld, final boolean isUpdate){
        this.revenueModel = revenueModel;
        view = LayoutInflater.from(context).inflate(R.layout.dialog_view,null);
        mTitle = (TextInputLayout)view.findViewById(R.id.title);
        mdescription = (TextInputLayout)view.findViewById(R.id.description);
        mMoney = (TextInputLayout)view.findViewById(R.id.money);
        mtime = (TextView)view.findViewById(R.id.mTime);
        spinner_revenue = (Spinner)view.findViewById(R.id.spinner_revenue);
        ArrayAdapter adapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_dropdown_item,getAllData());
        spinner_revenue.setAdapter(adapter);
        mtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDataPicker.showDate(view.getContext(),mtime);
            }
        });

        if(isUpdate){
            mTitle.getEditText().setText(revenueOld.getTitle());
            mdescription.getEditText().setText(revenueOld.getDescription());
            mMoney.getEditText().setText(String.valueOf(revenueOld.getMoney()));
            mtime.setText(revenueOld.getDate());
        }
        dialog = new AlertDialog.Builder(context);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "";
                if(mTitle.getEditText().getText().toString().isEmpty()){
                    message = "Title can't empty";
                }
                if(mdescription.getEditText().getText().toString().isEmpty()){
                    message += "\nDescription can't empty";
                }
                String money = mMoney.getEditText().getText().toString();
                if(money.isEmpty()){
                    message +="\nMoney can't empty";
                }
                if(mtime.getText().toString().equals(TITLETIME)){
                    message +="\nPlease chooose day";
                }
                try {
                    if(Double.parseDouble(money) <= 0){
                        message += "\nMoney had to bigger 0";
                    }
                }catch (Exception e){

                }
                if(message.equals("")){
                    try {
                        Revenue revenue = new Revenue();
                        revenue.setTitle(mTitle.getEditText().getText().toString());
                        revenue.setDescription(mdescription.getEditText().getText().toString());
                        revenue.setMoney(Double.parseDouble(money));
                        revenue.setDate(mtime.getText().toString());
                        revenue.setCategory(spinner_revenue.getSelectedItem().toString());
                        if(isUpdate){
                            revenue.setId(revenueOld.getId());
                            revenueModel.update(revenue);
                            Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show();
                        }else {
                            revenueModel.insert(revenue);
                            Toast.makeText(context, "Add successful", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }catch (NumberFormatException ex){
                        Toast.makeText(context, "Money has to a number", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private ArrayList<String> getAllData(){
        ArrayList<String> data = new ArrayList<>();
        for(RevenueType i : expenditureTypeList){
            data.add(i.getTitle());
        }
        return data;
    }

}
