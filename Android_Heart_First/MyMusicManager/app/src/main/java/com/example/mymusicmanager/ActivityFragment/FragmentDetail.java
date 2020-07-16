package com.example.mymusicmanager.ActivityFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymusicmanager.Data.Song;
import com.example.mymusicmanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetail extends Fragment {
    private int id;
    public FragmentDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
    public void onStart(){
        super.onStart();
        View view = getView();
        if(view != null) {
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView description = (TextView) view.findViewById(R.id.description);
            Song song = Song.ListSong[id];
            title.setText(song.getTitle());
            description.setText(song.getDescription());
        }
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            WatchFragment watchFragment = new WatchFragment();
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.add(R.id.fragment_container,watchFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }else{
            savedInstanceState.getInt("id");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
            outState.putInt("id",id);
    }

    public void getWordId(int id){
        this.id = id;
    }
}
