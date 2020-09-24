package com.example.revenuemanagement.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
    private EditText mTitle, mdescription;
    private TextView mTime;
    RevenueTypeViewModel revenueTypeModel;
    private RevenueType revenueTypeData;
    public RevenueTypeDialog() {
    }

    public RevenueTypeDialog(RevenueType revenueType){
        this.revenueTypeData = revenueType;
    }

    public void revenueTypeDialog(final Context context, final RevenueTypeViewModel revenueTypeModel, final boolean isUpdate){
        this.revenueTypeModel = revenueTypeModel;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_revenue_type,null);
        mTitle = (EditText) view.findViewById(R.id.mtitle);
//        mTime = (TextView)view.findViewById(R.id.mTime);
        mdescription = (EditText) view.findViewById(R.id.mdescription);
        if(isUpdate){
            mTitle.setText(revenueTypeData.getTitle());
            mdescription.setText(revenueTypeData.getDescription());
//            mTime.setText(revenueTypeData.getDate());
        }
        dialog = new AlertDialog.Builder(context);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "";
                if(mTitle.getText().toString().isEmpty()){
                    message = "Title can't empty";
                }
                if(mdescription.getText().toString().isEmpty()){
                    message += "\nDescription can't empty";
                }
                if(message.equals("")){
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
                }else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private RevenueType getRevenueType(){
        RevenueType revenueType = new RevenueType();
        revenueType.setTitle(mTitle.getText().toString());
        revenueType.setDescription(mdescription.getText().toString());
        return  revenueType;
    }

}
