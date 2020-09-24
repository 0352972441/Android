package com.example.revenuemanagement.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revenuemanagement.Models.Category;
import com.example.revenuemanagement.Models.Image;
import com.example.revenuemanagement.R;
import com.example.revenuemanagement.adapter.Category_Adapter;
import com.example.revenuemanagement.adapter.ItemOnListener;
import com.example.revenuemanagement.entity.ExpenditureType;
import com.example.revenuemanagement.ui.Expenditures.ExpensesTypeViewModel;

import java.util.ArrayList;

public class ExpenditureTypeDialog {
    private static ExpenditureType expenditureTypeOld;
    private EditText mtitle,mdescription;
    private ImageView mcategory;
    private RecyclerView recyclerView;
    private AlertDialog.Builder dialog;
    private int currentImage = R.drawable.category_outfit;
    private ArrayList<Image> listCategory;
    public ExpenditureTypeDialog() {
    }

    public ExpenditureTypeDialog(ExpenditureType expenditureTypeOld) {
        this.expenditureTypeOld = expenditureTypeOld;
    }

    public void showDialogExpenditureType(final Context context, final ExpensesTypeViewModel model, final boolean isUpdate){
        final View view = LayoutInflater.from(context).inflate(R.layout.expenditure_type_dialog,null, false);
        mtitle = (EditText)view.findViewById(R.id.mtitle);
        mdescription = (EditText)view.findViewById(R.id.mdescription);
        mcategory = (ImageView)view.findViewById(R.id.mcategory);
        if(isUpdate){
            mtitle.setText(expenditureTypeOld.getTitle());
            mcategory.setImageResource(expenditureTypeOld.getImgCategory());
            mdescription.setText(expenditureTypeOld.getDescription());
            //mcategory.setImageResource(expenditureTypeOld.getImgCategory());
            currentImage = expenditureTypeOld.getImgCategory();
        }
        dialog = new AlertDialog.Builder(context);
        mcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View expentditure_type_category = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_category_expentditure_type, null, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                recyclerView = (RecyclerView)expentditure_type_category.findViewById(R.id.reycler_dialog_expenditure_type);
                listCategory = imageCategory();
                final Category_Adapter adapter = new Category_Adapter(listCategory);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(expentditure_type_category.getContext(),4));

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mcategory.setImageResource(currentImage);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setView(expentditure_type_category);
                final AlertDialog alert = builder.create();
                alert.show();
                adapter.setListener(new ItemOnListener() {
                    @Override
                    public void listener(int position) {
                        currentImage =listCategory.get(position).getImage();
                        mcategory.setImageResource(currentImage);
                        alert.dismiss();
                    }
                });
            }
        });
        dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ExpenditureType expenditureType = new ExpenditureType();
                expenditureType.setTitle(mtitle.getText().toString());
                expenditureType.setImgCategory(currentImage);
                expenditureType.setDescription(mdescription.getText().toString());
                if(mtitle.getText().toString().isEmpty()){
                    Toast.makeText(view.getContext(), "Title can't empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isUpdate){
                    expenditureType.setId(expenditureTypeOld.getId());
                    model.update(expenditureType);
                }else{
                    model.insert(expenditureType);
                }
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private ArrayList<Image> imageCategory(){
        ArrayList<Image> list = new ArrayList<>();
        list.add(new Image(R.drawable.category_outfit));
        list.add(new Image(R.drawable.category_salary));
        list.add(new Image(R.drawable.category_ad));
        list.add(new Image(R.drawable.image_1));
        list.add(new Image(R.drawable.image_2));
        list.add(new Image(R.drawable.image_3));
        list.add(new Image(R.drawable.image_4));
        list.add(new Image(R.drawable.image_6));
        list.add(new Image(R.drawable.image_7));
        list.add(new Image(R.drawable.image_8));
        list.add(new Image(R.drawable.image_9));
        list.add(new Image(R.drawable.image_10));
        list.add(new Image(R.drawable.image_11));
        list.add(new Image(R.drawable.image_12));
        list.add(new Image(R.drawable.image_13));
        list.add(new Image(R.drawable.image_14));
        list.add(new Image(R.drawable.image_15));
        list.add(new Image(R.drawable.image_16));
        list.add(new Image(R.drawable.image_17));
        list.add(new Image(R.drawable.image_18));
        list.add(new Image(R.drawable.image_19));
        list.add(new Image(R.drawable.image_22));
        list.add(new Image(R.drawable.image_23));
        return list;
    }

}
