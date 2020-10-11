package com.example.cocoshop.dao.audiodao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.Adapter.MyFavoriteAdapter;
import com.example.cocoshop.Models.vocabularysmodel.Vocabulary;
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

public class MyFavoriteVocabularyDao extends AsyncTask<Void, ArrayList<Map<String,Object>>,Void> {
    private static final FirebaseFirestore store;
    private static final FirebaseUser user;
    private ArrayList<Map<String,Object>> data;
    private RecyclerView vocabulary_recycler_view;
    private Context context;
    public MyFavoriteVocabularyDao(RecyclerView vocabulary_recycler_view, Context context) {
        this.vocabulary_recycler_view = vocabulary_recycler_view;
        this.context = context;
        data = new ArrayList<>();
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
                       Log.d("Value:",""+i.getData());
                       data.add((Map<String, Object>) i.getData());
                   }
                    publishProgress(data);
                }
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(ArrayList<Map<String, Object>>... values) {
        super.onProgressUpdate(values);
        MyFavoriteAdapter adapter = new MyFavoriteAdapter(values[0]);
        vocabulary_recycler_view.setAdapter(adapter);
        vocabulary_recycler_view.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
    }
}
