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
import android.widget.TextView;
import android.widget.Toast;


import com.example.revenuemanagement.MainActivity;
import com.example.revenuemanagement.R;
import com.example.revenuemanagement.dao.Statistical_Dao;
import com.example.revenuemanagement.entity.Revenue;
import com.example.revenuemanagement.ui.Expenditures.ExpenditureViewModel;
import com.example.revenuemanagement.entity.Expenditure;
import com.example.revenuemanagement.ui.Revenue.RevenueExpenditureViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class StatisticalFragment extends Fragment {
    private LinearLayout mColumnOne,mColumnTwo,mColumnThree,mColumnFour, mColumnFive,mColumnSix, mColumnSeven;
    private ViewGroup.LayoutParams params,params2,params3,params4,params5,params6,params7;
    private TextView mSunday, mMonday, mTuesday, mWednesday, mThursday, mFriday, mSaturday;
    private TextView mSundayRevenue, mMondayRevenue, mTuesdayRevenue, mWednesdayRevenue, mThursdayRevenue, mFridayRevenue, mSaturdayRevenue;
    private LinearLayout mColumnOneRevenue,mColumnTwoRevenue,mColumnThreeRevenue,mColumnFourRevenue, mColumnFiveRevenue,mColumnSixRevenue, mColumnSevenRevenue;
    private ViewGroup.LayoutParams paramsRevenue,paramsRevenue2,paramsRevenue3,paramsRevenue4,paramsRevenue5,paramsRevenue6,paramsRevenue7;
    private TextView mmoney1,mmoney2,mmoney3,mmoney4,mmoney5,mmoney6,mmoney7;
    private ArrayList<ViewGroup.LayoutParams> layoutParams;
    private ArrayList<LinearLayout> linearLayouts;
    private ArrayList<TextView> weeks;
    private ArrayList<TextView> weeksRevenue;
    private ArrayList<LinearLayout> columnRevenue;
    private ArrayList<ViewGroup.LayoutParams> layoutParamsRevenue;
    private ArrayList<TextView> listMoney;
    private ExpenditureViewModel model;
    private RevenueExpenditureViewModel modelRevenue;
//    final String[] days = new String[] { "Mon","Thu","Sat","Sun","Fri","Wed","Tue" };
    //

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity)getActivity()).setActionBarTitle("Statistical");
        mColumnOne = (LinearLayout)view.findViewById(R.id.mcolumFive);
        params =  mColumnOne.getLayoutParams();
        mColumnTwo = (LinearLayout)view.findViewById(R.id.mcolumFour);
        params2 =  mColumnTwo.getLayoutParams();
        mColumnThree = (LinearLayout)view.findViewById(R.id.mcolumThree);
        params3 =  mColumnThree.getLayoutParams();

        mColumnFour = (LinearLayout)view.findViewById(R.id.mcolumTwo);
        params4 =  mColumnFour.getLayoutParams();
        mColumnFive = (LinearLayout)view.findViewById(R.id.mcolumOne);
        params5 =  mColumnFive.getLayoutParams();
        mColumnSix = (LinearLayout)view.findViewById(R.id.mcolumSeven);
        params6 =  mColumnSix.getLayoutParams();
        mColumnSeven = (LinearLayout)view.findViewById(R.id.mcolumSix);
        params7 =  mColumnSeven.getLayoutParams();
        layoutParams = new ArrayList<>();
        linearLayouts = new ArrayList<>();
        layoutParams.add(params5);
        layoutParams.add(params4);
        layoutParams.add(params3);
        layoutParams.add(params2);
        layoutParams.add(params);
        layoutParams.add(params7);
        layoutParams.add(params6);

        linearLayouts.add(mColumnFive);
        linearLayouts.add(mColumnFour);
        linearLayouts.add(mColumnThree);
        linearLayouts.add(mColumnTwo);
        linearLayouts.add(mColumnOne);
        linearLayouts.add(mColumnSeven);
        linearLayouts.add(mColumnSix);


        //Week
        weeks = new ArrayList<>();
        mMonday = (TextView)view.findViewById(R.id.txt4);
        mSunday = (TextView)view.findViewById(R.id.txt5);
        mSaturday = (TextView)view.findViewById(R.id.txt6);
        mWednesday = (TextView)view.findViewById(R.id.txt2);
        mThursday = (TextView)view.findViewById(R.id.txt1);
        mTuesday = (TextView)view.findViewById(R.id.txt3);
        mFriday = (TextView)view.findViewById(R.id.txt7);
        weeks.add(mThursday);
        weeks.add(mWednesday);
        weeks.add(mTuesday);
        weeks.add(mMonday);
        weeks.add(mSunday);
        weeks.add(mSaturday);
        weeks.add(mFriday);





        // Statiscal 2
        // Column
        mColumnOneRevenue = (LinearLayout)view.findViewById(R.id.column_revenue5);
        paramsRevenue =  mColumnOneRevenue.getLayoutParams();
        mColumnTwoRevenue = (LinearLayout)view.findViewById(R.id.column_revenue4);
        paramsRevenue2 =  mColumnTwoRevenue.getLayoutParams();
        mColumnThreeRevenue = (LinearLayout)view.findViewById(R.id.column_revenue3);
        paramsRevenue3 =  mColumnThreeRevenue.getLayoutParams();

        mColumnFourRevenue = (LinearLayout)view.findViewById(R.id.column_revenue2);
        paramsRevenue4 =  mColumnFourRevenue.getLayoutParams();
        mColumnFiveRevenue = (LinearLayout)view.findViewById(R.id.column_revenue1);
        paramsRevenue5 =  mColumnFiveRevenue.getLayoutParams();
        mColumnSixRevenue = (LinearLayout)view.findViewById(R.id.column_revenue7);
        paramsRevenue6 =  mColumnSixRevenue.getLayoutParams();
        mColumnSevenRevenue = (LinearLayout)view.findViewById(R.id.column_revenue6);
        paramsRevenue7 =  mColumnSevenRevenue.getLayoutParams();
        //Money
        mmoney1 = (TextView)view.findViewById(R.id.money_revenu5);
        mmoney2 = (TextView)view.findViewById(R.id.money_revenu4);
        mmoney3 = (TextView)view.findViewById(R.id.money_revenu3);
        mmoney4 = (TextView)view.findViewById(R.id.money_revenu2);
        mmoney5 = (TextView)view.findViewById(R.id.money_revenu1);
        mmoney6 = (TextView)view.findViewById(R.id.money_revenu7);
        mmoney7 = (TextView)view.findViewById(R.id.money_revenu6);
        listMoney = new ArrayList<>();
        listMoney.add(mmoney5);
        listMoney.add(mmoney4);
        listMoney.add(mmoney3);
        listMoney.add(mmoney2);
        listMoney.add(mmoney1);
        listMoney.add(mmoney7);
        listMoney.add(mmoney6);
        // Day 5 4 3 2 1 0 7 6
        mMondayRevenue = (TextView)view.findViewById(R.id.day_revenue4);
        mSundayRevenue = (TextView)view.findViewById(R.id.day_revenue5);
        mSaturdayRevenue = (TextView)view.findViewById(R.id.day_revenue6);
        mWednesdayRevenue = (TextView)view.findViewById(R.id.day_revenue2);
        mThursdayRevenue = (TextView)view.findViewById(R.id.day_revenue1);
        mTuesdayRevenue = (TextView)view.findViewById(R.id.day_revenue3);
        mFridayRevenue = (TextView)view.findViewById(R.id.day_revenue7);
        //weeksRevenue = Statistical_Dao.getListDayRevenue(mWednesdayRevenue, mThursdayRevenue, mFridayRevenue, mSaturdayRevenue, mSunday, mMondayRevenue, mTuesdayRevenue);
        weeksRevenue = new ArrayList<>();
        weeksRevenue.add(mThursdayRevenue);
        weeksRevenue.add(mWednesdayRevenue);
        weeksRevenue.add(mTuesdayRevenue);
        weeksRevenue.add(mMondayRevenue);
        weeksRevenue.add(mSundayRevenue);
        weeksRevenue.add(mSaturdayRevenue);
        weeksRevenue.add(mFridayRevenue);

        layoutParamsRevenue = Statistical_Dao.getListParams(params5,params4,params3,params2,params,params7,params6);
        columnRevenue = Statistical_Dao.getListColumn(mColumnFiveRevenue,mColumnFourRevenue,mColumnThreeRevenue,mColumnTwoRevenue,mColumnOneRevenue,mColumnSevenRevenue,mColumnSixRevenue);
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
        modelRevenue = new ViewModelProvider(this).get(RevenueExpenditureViewModel.class);
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
                            try {
                                weeks.get(i).setText(String.valueOf(calendar1.get(Calendar.DAY_OF_MONTH)));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            final Date weekend = calendar1.getTime();
                            double sum = 0;
                            for(Expenditure j : dateData){
                                try {
                                    Date date = formatDate.parse(j.getDate());
                                    if(weekend.getDay() == date.getDay() && weekend.getMonth() == date.getMonth() && weekend.getYear() ==date.getYear()){
                                        sum += j.getMoney();
                                        layoutParams.get(i).height = (int)sum;//(int)j.getMoney();
                                        linearLayouts.get(i).setLayoutParams(layoutParams.get(i));
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        handler.postDelayed(this, 1000);
                    }
                });
            }
        });
        statisticalRevenue();
    }

    public void statisticalRevenue(){
        modelRevenue.getGetAll().observe(getViewLifecycleOwner(), new Observer<List<Revenue>>() {
            @Override
            public void onChanged(List<Revenue> revenues) {
                final ArrayList<Revenue> listDays = new ArrayList<>();
                Calendar calendar = Calendar.getInstance();
                final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                calendar.add(Calendar.DAY_OF_MONTH, -7);
                for(Revenue i : revenues){
                    try {
                        Date date = format.parse(i.getDate());
                        if(date.after(calendar.getTime())){
                            listDays.add(i);
                            Toast.makeText(getContext(), String.valueOf(listDays.size()), Toast.LENGTH_SHORT).show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i< 7; i++){
                            Calendar calendar1 = Calendar.getInstance();
                            calendar1.add(Calendar.DAY_OF_MONTH, -i);
                            double total = 0;
                            weeksRevenue.get(i).setText(String.valueOf(calendar1.get(Calendar.DAY_OF_MONTH)));
                            for(Revenue j : listDays){
                                try {
                                    Date date = format.parse(j.getDate());
                                    if(calendar1.getTime().getDay() == date.getDay() && calendar1.getTime().getMonth() == date.getMonth() && calendar1.getTime().getYear() == date.getYear()){
                                        total += j.getMoney();
                                        layoutParamsRevenue.get(i).height = (int)total;
                                        listMoney.get(i).setText(String.valueOf(total));
                                        columnRevenue.get(i).setLayoutParams(layoutParamsRevenue.get(i));
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        handler.postDelayed(this, 1000);
                    }
                });
            }
        });
    }
}
