package com.example.cocoshop.dao.audiodao;

import android.os.AsyncTask;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.cocoshop.Adapter.topicsadapter.ViewPagerVocabulary;
import com.example.cocoshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class VocabularyLikedDao extends AsyncTask<String,Boolean,Void> {
    private FirebaseFirestore firestore;
    private FirebaseUser user;
    private ImageView isFavorite;

    public VocabularyLikedDao(ImageView isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        firestore = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    protected Void doInBackground(final String... vocabulary) {
        firestore.collection("favoritevocabulary").document(user.getUid()).collection(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot i: task.getResult().getDocuments()){
                        if(i.getData().get("vocabulary").equals(vocabulary[0])){
                            publishProgress(true);
                            return;
                        }
                    }
                }
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(Boolean... values) {
        super.onProgressUpdate(values);
        if(values[0]){
            isFavorite.setImageResource(R.drawable.ic_on_favorite_audio_24dp);
            new ViewPagerVocabulary().setFavorite(true);
        }
    }
}
