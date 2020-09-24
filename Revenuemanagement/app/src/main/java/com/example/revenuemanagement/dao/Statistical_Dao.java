package com.example.revenuemanagement.dao;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Statistical_Dao {
    public static ArrayList<TextView> getListDayRevenue(TextView wed,TextView thu,TextView fri,TextView sat,TextView sun,TextView mon,TextView tue){
        ArrayList<TextView> list = new ArrayList<>();
        list.add(wed);
        list.add(thu);
        list.add(fri);
        list.add(sat);
        list.add(sun);
        list.add(mon);
        list.add(tue);
        return list;
    }

    public static ArrayList<ViewGroup.LayoutParams> getListParams(ViewGroup.LayoutParams params,ViewGroup.LayoutParams params2, ViewGroup.LayoutParams params3
    ,ViewGroup.LayoutParams params4, ViewGroup.LayoutParams params5, ViewGroup.LayoutParams params6, ViewGroup.LayoutParams params7){
        ArrayList<ViewGroup.LayoutParams> list = new ArrayList<>();
        list.add(params);
        list.add(params2);
        list.add(params3);
        list.add(params4);
        list.add(params5);
        list.add(params6);
        list.add(params7);
        return list;
    }

    public static ArrayList<LinearLayout> getListColumn(LinearLayout column1,LinearLayout column2,LinearLayout column3,LinearLayout column4,LinearLayout column5,LinearLayout column6,LinearLayout column7){
        ArrayList<LinearLayout> list = new ArrayList<>();
        list.add(column1);
        list.add(column2);
        list.add(column3);
        list.add(column4);
        list.add(column5);
        list.add(column6);
        list.add(column7);
        return list;
    }

}