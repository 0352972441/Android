package com.example.revenuemanagement.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.revenuemanagement.Exception.EditTextEmptyException;
import com.example.revenuemanagement.Exception.HandleException;
import com.example.revenuemanagement.R;
import com.example.revenuemanagement.entity.Revenue;
import com.example.revenuemanagement.entity.RevenueType;
import com.example.revenuemanagement.ui.Revenue.RevenueExpenditureViewModel;
import com.example.revenuemanagement.ui.Revenue.RevenueTypeFragment;
import com.example.revenuemanagement.ui.Revenue.RevenueTypeViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class RevenueTypeDialog {
    AlertDialog.Builder dialog;
    TextInputLayout mTitle, mdescription,mMoney;
    private TextView mTime;
    RevenueTypeViewModel revenueTypeModel;
    private boolean isUpdate = false;
    public RevenueTypeDialog() {
    }

    public RevenueTypeDialog(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void revenueTypeDialog(final Context context, final RevenueTypeViewModel revenueTypeModel, final RevenueType revenueTypeData){
        this.revenueTypeModel = revenueTypeModel;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_view,null);
        mTitle = (TextInputLayout)view.findViewById(R.id.title);
        mMoney = (TextInputLayout)view.findViewById(R.id.money);
        mTime = (TextView)view.findViewById(R.id.mTime);
        mdescription = (TextInputLayout)view.findViewById(R.id.description);
        if(isUpdate){
            mTitle.getEditText().setText(revenueTypeData.getTitle());
            mdescription.getEditText().setText(revenueTypeData.getDescription());
            mMoney.getEditText().setText(String.valueOf(revenueTypeData.getMoney()));
            mTime.setText(revenueTypeData.getDate());
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
                    message +="\n Money can't empty";
                }
                if(message.equals("")){
                    try {
                        //RevenueType revenueType = getRevenueType();; //= new RevenueType();
                        RevenueType revenueType = getRevenueType();
                        if(isUpdate){
                            revenueType.setId(revenueTypeData.getId());
                            revenueTypeModel.update(revenueType);
                            Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show();
                        }else{
                            revenueTypeModel.insert(revenueType);
                            Toast.makeText(context, "Add successful", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }catch (NumberFormatException ex){
                        Toast.makeText(context, "Money has to a number ", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private RevenueType getRevenueType(){
        RevenueType revenueType = new RevenueType();
        revenueType.setTitle(mTitle.getEditText().getText().toString());
        revenueType.setDescription(mdescription.getEditText().getText().toString());
        revenueType.setMoney(Double.parseDouble(mMoney.getEditText().getText().toString()));
        return  revenueType;
    }

}
