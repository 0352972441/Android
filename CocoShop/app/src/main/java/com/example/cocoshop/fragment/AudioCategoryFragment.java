package com.example.cocoshop.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cocoshop.R;
import com.example.cocoshop.adapter.audioadapter.CardItemAudioPopularAdapter;
import com.example.cocoshop.dao.AudioDao;
import com.example.cocoshop.dao.ViewAllAudioDao;
import com.example.cocoshop.models.audiomodels.Audio;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AudioCategoryFragment extends Fragment {
    public  String category = "";
    private RecyclerView itemAudioRecycler;
    public AudioCategoryFragment(String category) {
        this.category = category;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemAudioRecycler = view.findViewById(R.id.item_audio_recycler);
        new ViewAllAudioDao(itemAudioRecycler).execute(category);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }
}
