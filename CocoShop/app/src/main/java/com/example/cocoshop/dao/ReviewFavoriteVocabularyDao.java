package com.example.cocoshop.dao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;
import com.example.cocoshop.adapter.ReviewVocabularyAdapter;
import com.example.cocoshop.listener.Listener;
import com.example.cocoshop.listener.ListenerViewPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReviewFavoriteVocabularyDao extends AsyncTask<Void, List<Map<String,Object>>,Void> {
    private static final FirebaseFirestore store;
    private static final FirebaseUser user;
    private List<Map<String,Object>> data;
    @SuppressLint("StaticFieldLeak")
    private ViewPager2 viewPager2;
    private Activity activity;
    public ReviewFavoriteVocabularyDao(ViewPager2 viewPager,Activity activity) {
        this.viewPager2 = viewPager;
        data = new ArrayList<>();
        this.activity = activity;
    }

    static {
        store = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        store.collection("favoritevocabulary").document(user.getUid()).collection(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot i : task.getResult().getDocuments()){
                        data.add((Map<String, Object>) i.getData());
                    }
                    publishProgress(data);
                }
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(final List<Map<String, Object>>... values) {
        super.onProgressUpdate(values);
        ReviewVocabularyAdapter adapter = new ReviewVocabularyAdapter(values[0],activity);
        viewPager2.setAdapter(adapter);
        viewPager2.setUserInputEnabled(false);
        adapter.setOnClickNextQuestion(new ListenerViewPage() {
            @Override
            public void onClickListener() {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
            }
        });
    }
}
