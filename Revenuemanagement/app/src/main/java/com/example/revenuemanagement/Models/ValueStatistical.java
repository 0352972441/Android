package com.example.revenuemanagement.Models;

import com.example.revenuemanagement.entity.Expenditure;

import java.util.List;

public class ValueStatistical {

    public synchronized static int getTotal(List<Expenditure> expenditures){
        int total = 0;
        for(Expenditure i : expenditures){
            total += i.getMoney();
        }
        return total;
    }
}
