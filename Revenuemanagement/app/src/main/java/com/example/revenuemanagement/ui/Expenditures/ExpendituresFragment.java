package com.example.revenuemanagement.ui.Expenditures;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.revenuemanagement.MainActivity;
import com.example.revenuemanagement.R;
import com.example.revenuemanagement.adapter.ViewPageAdapter;
import com.example.revenuemanagement.ui.Revenue.RevenueExpenditureFragment;
import com.example.revenuemanagement.ui.Revenue.RevenueTypeFragment;
import com.example.revenuemanagement.ui.SettingActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ExpendituresFragment extends Fragment {
    private TabLayout tabBarExpenses;
    private ViewPager2 viewPageExpenses;
    private boolean isTrue;
    private List<HashMap<String, Object>> listViewPage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenditures, container, false);
        listViewPage = new ArrayList<>();
        initData();
        onCreateViewPage(view);
        setHasOptionsMenu(true);
        ((MainActivity)getActivity()).setActionBarTitle("Expenditure");
        return view;
    }

    public void onCreateViewPage(View view){
        tabBarExpenses = (TabLayout)view.findViewById(R.id.tabBarExpenses);
        viewPageExpenses = (ViewPager2)view.findViewById(R.id.viewPageExpenses);
        ViewPageAdapter adapter = new ViewPageAdapter(getActivity());
        adapter.AddFragment((Fragment) listViewPage.get(0).get("fragment"));
        adapter.AddFragment((Fragment) listViewPage.get(1).get("fragment"));
        viewPageExpenses.setAdapter(adapter);
        new TabLayoutMediator(tabBarExpenses, viewPageExpenses, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText((String)listViewPage.get(position).get("title"));
                tab.setIcon((Integer) listViewPage.get(position).get("icon"));
            }
        }).attach();
    }

    private void initData(){
        HashMap<String,Object> revenueType = new HashMap<>();
        revenueType.put("fragment", new ExpensesTypeFragment());
        revenueType.put("title","Expenditure Type");
        revenueType.put("icon",R.drawable.logo);
        HashMap<String,Object> revenueExpenditure = new HashMap<>();
        revenueExpenditure.put("fragment", new Expenditure());
        revenueExpenditure.put("title", "Expenditure");
        revenueExpenditure.put("icon",R.drawable.icon_expenditure_blue);
        listViewPage.add(revenueType);
        listViewPage.add(revenueExpenditure);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(SettingActivity.ISPURCHASE,Expenditure.isIsPurchase());
        bundle.putBoolean(SettingActivity.ISAD,Expenditure.isIsAd());
        bundle.putBoolean(SettingActivity.ISSALARY,Expenditure.isIsSalary());
        bundle.putBoolean(SettingActivity.ISOUTFIT,Expenditure.isIsOutfit());
        if(item.getItemId() == R.id.action_settings){
            Intent intent = new Intent(getContext(), SettingActivity.class);
            intent.putExtra(SettingActivity.KEYBUNDLE,bundle);
            startActivity(intent);
        }
        return true;
    }

    // NEw
    public ExpenditureViewModel mViewModel;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ExpenditureViewModel.class);
    }
}
