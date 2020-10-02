package com.example.cocoshop.fireStore;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cocoshop.Adapter.audioadapter.CardItemAudioPopularAdapter;
import com.example.cocoshop.Models.audiomodels.Audio;
import com.example.cocoshop.Models.audiomodels.Category;
import com.example.cocoshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FireStoreAudio {
    public static ArrayList<Audio> audios;
    private static final FirebaseFirestore mStore;
    static {
        audios = new ArrayList<>();
        mStore = FirebaseFirestore.getInstance();
    }

    public static CardItemAudioPopularAdapter callData(){
        final ArrayList<Audio> audioArrayList = new ArrayList<>();
        CollectionReference mDoc = mStore.collection("audios");
        mDoc.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot i : task.getResult()){
                        Map<String ,Object> data = i.getData();
                        String title = (String)data.get("title");
                        String urlReader = (String)data.get("urlReader");
                        String readerName = (String)data.get("readerName");
                        String urlAudio = (String)data.get("readerName");
                        //int imageAudio = (String)data.get("readerName");
                        boolean favorite = (boolean)data.get("favorite");
                        Category category = Category.valueOf((String)data.get("category"));
                        Audio audio = new Audio(title,urlReader,readerName,urlAudio, R.drawable.background_card_item,category);
                        audio.setFavorite(favorite);
                        audioArrayList.add(audio);
                        Log.d("Tag","Data"+audioArrayList.size());
                    }
                }
            }
        });
        return new CardItemAudioPopularAdapter(audioArrayList);
    }
}
