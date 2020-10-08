package com.example.cocoshop.ui.Progressivelearning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.cocoshop.Adapter.LevelMissionProgressiveAdapter;
import com.example.cocoshop.Models.ProgressiveModels.LevelMap;
import com.example.cocoshop.Models.ProgressiveModels.Star;
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.HomeScreen.HomeScreen;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProgressive extends Fragment {
    //private RecyclerView leveMap;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /*leveMap = (RecyclerView)view.findViewById(R.id.levelMap);
        LevelMissionProgressiveAdapter adapter = new LevelMissionProgressiveAdapter(data());
        leveMap.setAdapter(adapter);
        leveMap.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_progresive, container, false);
        return view;
    }

    /*private ArrayList data(){
        ArrayList<LevelMap> data = new ArrayList();
        data.add(new LevelMap("Chào hỏi",R.id.cardBackground, Star.BAD));
        data.add(new LevelMap("Hỏi nơi chốn",R.id.cardBackground, Star.BAD));
        data.add(new LevelMap("Lần đầu gặp",R.id.cardBackground, Star.GOOD));
        data.add(new LevelMap("Cảm ơn",R.id.cardBackground, Star.EXCELLENT));
        data.add(new LevelMap("Xin lỗi",R.id.cardBackground, Star.EXCELLENT));
        data.add(new LevelMap("Nhờ ai làm gì đó",R.id.cardBackground, Star.BAD));
        data.add(new LevelMap("Chỉ đường",R.id.cardBackground, Star.GOOD));
        data.add(new LevelMap("Số đếm",R.id.cardBackground, Star.BAD));
        data.add(new LevelMap("Gia đình",R.id.cardBackground, Star.GOOD));
        data.add(new LevelMap("Bạn bè",R.id.cardBackground, Star.BAD));
        return data;
    }*/

    @Override
    public void onResume() {
        super.onResume();
        HomeScreen.isCurrentFragment = "Progressive";
    }
}
