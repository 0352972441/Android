package com.example.revenuemanagement.ui.Statistical;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.revenuemanagement.R;
import com.example.revenuemanagement.ui.Expenditures.ExpenditureViewModel;
import com.example.revenuemanagement.entity.Expenditure;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class StatisticalFragment extends Fragment {
    private LinearLayout mColumnOne,mColumnTwo,mColumnThree,getmColumnFour, getmColumnFive,getmColumnSix, getmColumnSeven;
    private ViewGroup.LayoutParams params,params2,params3,params4,params5,params6,params7;
    //private  double monday =3, tuesday=3,wednesday=3,thursday =3,friday = 3,saturday =3,sunday=3;
    ArrayList<ViewGroup.LayoutParams> layoutParams;
    ArrayList<LinearLayout> linearLayouts;
    private ExpenditureViewModel model;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mColumnOne = (LinearLayout)view.findViewById(R.id.mcolumOne);
        params =  mColumnOne.getLayoutParams();
        mColumnTwo = (LinearLayout)view.findViewById(R.id.mcolumTwo);
        params2 =  mColumnTwo.getLayoutParams();
        mColumnThree = (LinearLayout)view.findViewById(R.id.mcolumThree);
        params3 =  mColumnThree.getLayoutParams();

        getmColumnFour = (LinearLayout)view.findViewById(R.id.mcolumFour);
        params4 =  getmColumnFour.getLayoutParams();
        getmColumnFive = (LinearLayout)view.findViewById(R.id.mcolumFive);
        params5 =  getmColumnFive.getLayoutParams();
        getmColumnSix = (LinearLayout)view.findViewById(R.id.mcolumSix);
        params6 =  getmColumnSix.getLayoutParams();
        getmColumnSeven = (LinearLayout)view.findViewById(R.id.mcolumSeven);
        params7 =  getmColumnSeven.getLayoutParams();
        layoutParams = new ArrayList<>();
        linearLayouts = new ArrayList<>();
        layoutParams.add(params);
        layoutParams.add(params2);
        layoutParams.add(params3);
        layoutParams.add(params4);
        layoutParams.add(params5);
        layoutParams.add(params6);
        layoutParams.add(params7);
        linearLayouts.add(mColumnOne);
        linearLayouts.add(mColumnTwo);
        linearLayouts.add(mColumnThree);
        linearLayouts.add(getmColumnFour);
        linearLayouts.add(getmColumnFive);
        linearLayouts.add(getmColumnSix);
        linearLayouts.add(getmColumnSeven);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_loading,menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistical, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model = new ViewModelProvider(this).get(ExpenditureViewModel.class);
        model.getGetAll().observe(getViewLifecycleOwner(), new Observer<List<Expenditure>>() {
            @Override
            public void onChanged(List<Expenditure> expenditures) {
                final SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
                final Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH, -7);

                final ArrayList<Expenditure> dateData = new ArrayList<>();
                for(Expenditure i : expenditures){
                    try {
                        Date date = formatDate.parse(i.getDate());
                        //calendar.add(Calendar.DAY_OF_MONTH, -7);
                        System.out.println("Thời gian 7 ngày trước ==================================\n"+calendar.getTime()+" date:"+date);
                        if(date.after(calendar.getTime())){
                            // Nếu ngày date mà sau ngày hiện tại trừ lui 7(Nếu ngáy nớ sau(nghĩa trước thứ 2 sau thứ 3). Nếu ngày date mà sau(Hôm nay thứ 2 sau thứ 3) ngày trừ lui 7 thì add vô
                            dateData.add(i);
                            //Toast.makeText(getContext(), String.valueOf(dateData.size()), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Date is empty", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
/*Ngày ni ngày 17
     là Thứ 2
    getTime(được thứ nhiện tại)
    thứ hiện tại trừ lui - lui 7 ngày
    nếu ngày mô nằm trong khoảng 7 ngày thì thêm vô
* */
                }
                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i< 7; i++){
                            Calendar calendar1 = Calendar.getInstance();
                            calendar1.add(Calendar.DAY_OF_MONTH, -i);
                            System.out.println("Ngày hiện tại Trừ lui i ngày:"+ calendar1.get(Calendar.DAY_OF_MONTH));
                            final Date weekend = calendar1.getTime();
                            double total = 3;
                            for(Expenditure j : dateData){
                                try {
                                    Date date = formatDate.parse(j.getDate());
                                    if(weekend.getDay() == date.getDay() && weekend.getMonth() == date.getMonth() && weekend.getYear() ==date.getYear()){
                                        total += j.getMoney();
                                        layoutParams.get(i).height = (int)total;//(int)j.getMoney();
                                        linearLayouts.get(i).setLayoutParams(layoutParams.get(i));
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        handler.postDelayed(this, 30000);
                    }
                });
            }
        });
    }
}
