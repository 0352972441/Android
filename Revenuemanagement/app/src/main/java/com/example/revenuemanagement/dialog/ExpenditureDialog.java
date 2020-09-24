package com.example.revenuemanagement.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.revenuemanagement.Models.Category;
import com.example.revenuemanagement.R;
import com.example.revenuemanagement.adapter.ExpenditureTypeAdapter;
import com.example.revenuemanagement.adapter.SpinerAdapter;
import com.example.revenuemanagement.entity.Expenditure;
import com.example.revenuemanagement.entity.ExpenditureType;
import com.example.revenuemanagement.ui.Expenditures.ExpenditureViewModel;
import com.example.revenuemanagement.ui.Expenditures.ExpensesTypeViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ExpenditureDialog {
    private static AlertDialog.Builder dialog;
    private static ArrayList<Category> listCatrgory;
    private  Spinner mcategory;
    private Expenditure expenditureOld;
    private  View view;
    private  ExpenditureViewModel modelExpenditure;
    private  TextInputLayout mtitle,mdescription,mmoney;
    private  static int color = R.color.colorPrimary;
    private  TextView mdate;
    private static ImageView mcolor;
    private List<ExpenditureType> listExpenditureType;



    public ExpenditureDialog(Expenditure expenditureOld, List<ExpenditureType> listExpenditureType) {
        this.expenditureOld = expenditureOld;
        this.listExpenditureType = listExpenditureType;
    }

    public ExpenditureDialog(List<ExpenditureType> listExpenditureType) {
        this.listExpenditureType = listExpenditureType;
    }

    public  void showDialog(final Context context, final ExpenditureViewModel model, final boolean isUpdate){
        modelExpenditure = model;
        this.view = LayoutInflater.from(context).inflate(R.layout.expenditure_dialog,null,false);
        dialog = new AlertDialog.Builder(context);
        mtitle = (TextInputLayout)view.findViewById(R.id.mtitle);
        mdescription = (TextInputLayout)view.findViewById(R.id.mdescription);
        mdate = (TextView)view.findViewById(R.id.mdate);
        mmoney = (TextInputLayout)view.findViewById(R.id.mmoney);
        mcolor = (ImageView) view.findViewById(R.id.mColor);
        if(isUpdate){
            mtitle.getEditText().setText(expenditureOld.getTitle());
            mdescription.getEditText().setText(expenditureOld.getDescription());
            mdate.setText(expenditureOld.getDate());
            mmoney.getEditText().setText(String.valueOf(expenditureOld.getMoney()));
            mcolor.setBackgroundColor(expenditureOld.getColor());
        }
        setUpSpinerCategory(view);
        mcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowColorPicker.showColorPicker(context);
            }
        });
        mdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDataPicker.showDate(context,mdate);
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    String title = mtitle.getEditText().getText().toString();
                    String description = mdescription.getEditText().getText().toString();
                    String date = mdate.getText().toString();
                    String money = mmoney.getEditText().getText().toString();
                    String message = validationForm(title,date,description,money);
                   if(message.equals("")){
                        final Expenditure expenditure = new Expenditure();
                        expenditure.setTitle(title);
                        expenditure.setDescription(description);
                        expenditure.setDate(date);
                        expenditure.setMoney(Double.parseDouble(money));
                        expenditure.setColor(color);
                        expenditure.setCategory(listCatrgory.get(mcategory.getSelectedItemPosition()).getTitle());
                        expenditure.setImage(listCatrgory.get(mcategory.getSelectedItemPosition()).getImage());
                        if (isUpdate) {
                            expenditure.setId(expenditureOld.getId());
                            model.update(expenditure);
                            mcolor.setBackgroundColor(R.color.colorPrimary);
                            color = R.color.colorPrimary;
                            Toast.makeText(view.getContext(), "Update successful", Toast.LENGTH_SHORT).show();
                        } else {
                            model.insert(expenditure);
                            mcolor.setBackgroundColor(R.color.colorPrimary);
                            color = R.color.colorPrimary;
                            Toast.makeText(context, "Add successful", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                       Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                   }
                }catch (NumberFormatException ex){
                    Toast.makeText(context, "Money has to number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private  void setUpSpinerCategory(final View view){
        mcategory = (Spinner) view.findViewById(R.id.spinnerExpenture);
        listCatrgory = getAll();
        SpinnerAdapter adapter = new SpinerAdapter(listCatrgory);
        mcategory.setAdapter(adapter);
    }

    private  ArrayList<Category> getAll(){
        final ArrayList<Category> listCategory = new ArrayList<>();
        for(ExpenditureType i : listExpenditureType) {
            listCategory.add(new Category(i.getTitle(), i.getImgCategory()));
        }
        /*listCategory.add(new Category("Salary",R.drawable.category_salary));
        listCategory.add(new Category("Outfit",R.drawable.category_outfit));
        listCategory.add(new Category("Advertisement",R.drawable.category_ad));
        listCategory.add(new Category("Purchase",R.drawable.category_purchase));*/
        return listCategory;
    }

    private static String validationForm(String title, String date, String description, String money){
        String message = "";
        if(title.isEmpty()){
            message += "Title can't empty";
        }
        if(date.equals("Please choose date")){
            message += "\n Date can't empty";
        }
        if(description.isEmpty()){
            message += "\n Description can't empty";
        }
        if(money.isEmpty()){
            message += "\n Money can't empty";
        }
        try {
            if (Double.parseDouble(money) <= 0) {
                message += "\n Money had to biger 0";
            }
        }catch (Exception ex){

        }
        return message;
    }

    @SuppressLint("ResourceAsColor")
    public static void setBackgroundColor(int colors){
        color = colors;
        mcolor.setBackgroundColor(color);
    }
}
