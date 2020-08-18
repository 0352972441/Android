package com.example.revenuemanagement.ui.Revenue;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.revenuemanagement.MainActivity;
import com.example.revenuemanagement.R;
import com.example.revenuemanagement.adapter.ViewPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RevenueFragment extends Fragment {
    TabLayout tabBarRevenue;
    ViewPager2 viewPageRevenue;
    private List<HashMap<String, Object>> listViewPage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_revenue, container, false);
        listViewPage = new ArrayList<>();
        initData();
        onCreateViewPage(view);
        ((MainActivity)getActivity()).setActionBarTitle("Revenue");
        return view;
    }

    public void onCreateViewPage(View view){
        tabBarRevenue = (TabLayout)view.findViewById(R.id.tabBarRevenue);
        viewPageRevenue = (ViewPager2)view.findViewById(R.id.viewPageRevenue);
        ViewPageAdapter adapter = new ViewPageAdapter(getActivity());
        adapter.AddFragment((Fragment) listViewPage.get(0).get("fragment"));
        adapter.AddFragment((Fragment) listViewPage.get(1).get("fragment"));
        viewPageRevenue.setAdapter(adapter);
        new TabLayoutMediator(tabBarRevenue, viewPageRevenue, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
               tab.setText((String)listViewPage.get(position).get("title"));
               tab.setIcon((Integer) listViewPage.get(position).get("icon"));
            }
        }).attach();
    }

    private void initData(){
        HashMap<String,Object> revenueType = new HashMap<>();
        revenueType.put("fragment", new RevenueTypeFragment());
        revenueType.put("title","Revenue Type");
        revenueType.put("icon",R.drawable.ic_local_atm_black_24dp);
        HashMap<String,Object> revenueExpenditure = new HashMap<>();
        revenueExpenditure.put("fragment", new RevenueExpenditureFragment());
        revenueExpenditure.put("title", "Revenue");
        revenueExpenditure.put("icon",R.drawable.icon_revenue_yellow);
        listViewPage.add(revenueExpenditure);
        listViewPage.add(revenueType);
    }
}
