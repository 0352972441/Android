package com.example.cocoshop.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cocoshop.adapter.audioadapter.CardItemAudioApdapter;
import com.example.cocoshop.adapter.audioadapter.CardItemAudioPopularAdapter;
import com.example.cocoshop.models.audiomodels.Audio;
import com.example.cocoshop.R;
import com.example.cocoshop.screen.HomeScreen;
import com.example.cocoshop.screen.audioscreen.MainAudioActivity;
import com.example.cocoshop.dao.AudioDao;

import java.util.ArrayList;

public class AudioFragment extends Fragment {
    private RecyclerView cardItemAudioRecycler;
    private RecyclerView cardItemAudioPopularRecycler;
    private CardItemAudioPopularAdapter audioPopularAdapter;
    private CardItemAudioApdapter audioApdapter;
    private TextView txViewAll; // View_all Click show All category audio//
    private ArrayList<Audio> data;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Declare //Initialization variable widget
        txViewAll = (TextView)view.findViewById(R.id.view_all);
        cardItemAudioRecycler = (RecyclerView)view.findViewById(R.id.card_item_audio_recycler);
        cardItemAudioPopularRecycler = (RecyclerView)view.findViewById(R.id.card_item_audio_popular_recycler);
        //audioApdapter = new CardItemAudioApdapter(Sound.listAllData);
        /*data = Sound.getAllAudioPopular();
        audioPopularAdapter = new CardItemAudioPopularAdapter(data);
        cardItemAudioRecycler.setAdapter(audioApdapter);
        getCardItemAudioPopularRecycler.setAdapter(audioPopularAdapter);
        getCardItemAudioPopularRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        cardItemAudioRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));*/
        new AudioDao(cardItemAudioPopularRecycler,cardItemAudioRecycler).execute();
        //new Sound(cardItemAudioPopularRecycler).execute();
        onClickViewAll();
        //onClickPlayAudio();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audio, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        HomeScreen.isCurrentFragment = "AUDIO";
    }

    private void onClickViewAll(){
        txViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainAudioActivity.class);
                startActivity(intent);
            }
        });
    }

    /*private void onClickPlayAudio(){
        final Intent intent = new Intent(getContext(), PlayAudioActivity.class);
        audioPopularAdapter.setPlayAudioListener(new Listener() {
            @Override
            public void listener(int position) {
                Bundle bundle = new Bundle();
                Audio audio = data.get(position);
                intent.putExtra(PlayAudioActivity.KEYAUDIO, BundleData.sendData(audio));
                startActivity(intent);
            }
        });
        audioApdapter.setPlayAudioListener(new Listener() {
            @Override
            public void listener(int position) {
                Bundle bundle = new Bundle();
                Audio audio = data.get(position);
                intent.putExtra(PlayAudioActivity.KEYAUDIO, BundleData.sendData(audio));
                startActivity(intent);
            }
        });
    }*/
}
