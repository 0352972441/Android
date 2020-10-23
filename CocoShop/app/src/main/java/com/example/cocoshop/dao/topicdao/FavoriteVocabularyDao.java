package com.example.cocoshop.dao.topicdao;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class FavoriteVocabularyDao extends AsyncTask<Map<String,Object>,Void,Void> {
    private FirebaseFirestore firestore;
    private FirebaseUser user;
    private boolean isFavorite;

    public FavoriteVocabularyDao(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        firestore = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @SafeVarargs
    @Override
    protected final Void doInBackground(final Map<String, Object>... vocabularies) {
        /*if(isFavorite){
            firestore.collection("favoritevocabulary").document(user.getUid()).collection(vocabularies[0].get("vocabulary").toString()).document(user.getUid()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    // Do something
                }
            });
        }else {
            firestore.collection("favoritevocabulary").document(user.getUid()).collection(vocabularies[0].get("vocabulary").toString()).document(user.getUid()).set(vocabularies[0]).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    //
                }
            });
        }
        return null;*/
        if(isFavorite){
            firestore.collection("favoritevocabulary").document(user.getUid()).collection(user.getUid()).document(vocabularies[0].get("vocabulary").toString()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    // Do something
                }
            });
        }else {
            firestore.collection("favoritevocabulary").document(user.getUid()).collection(user.getUid()).document(vocabularies[0].get("vocabulary").toString()).set(vocabularies[0]).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    //
                }
            });
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("Result:","Successfully");
    }
}
