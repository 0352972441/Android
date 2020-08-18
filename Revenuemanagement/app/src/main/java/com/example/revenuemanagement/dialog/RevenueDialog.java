package com.example.revenuemanagement.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.revenuemanagement.R;
import com.example.revenuemanagement.entity.Revenue;
import com.example.revenuemanagement.entity.RevenueType;
import com.example.revenuemanagement.ui.Revenue.RevenueExpenditureViewModel;
import com.example.revenuemanagement.ui.Revenue.RevenueTypeViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class RevenueDialog {
    private static final String TITLETIME = "Please choose day";
    private AlertDialog.Builder dialog;
    private View view;
    private TextInputLayout mTitle, mdescription,mMoney;
    private Button chooseDay;
    private TextView mtime;
    private RevenueExpenditureViewModel revenueModel;
    //private boolean isUpdate = false;

    public void revenueDialog(final Context context, final RevenueExpenditureViewModel revenueModel, final Revenue revenueOld, final boolean isUpdate){
        this.revenueModel = revenueModel;
        view = LayoutInflater.from(context).inflate(R.layout.dialog_view,null);
        mTitle = (TextInputLayout)view.findViewById(R.id.title);
        mdescription = (TextInputLayout)view.findViewById(R.id.description);
        mMoney = (TextInputLayout)view.findViewById(R.id.money);
        mtime = (TextView)view.findViewById(R.id.mTime);
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
                if(message.equals("")){
                    try {
                        Revenue revenue = new Revenue();
                        revenue.setTitle(mTitle.getEditText().getText().toString());
                        revenue.setDescription(mdescription.getEditText().getText().toString());
                        revenue.setMoney(Double.parseDouble(money));
                        revenue.setDate(mtime.getText().toString());
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

}
