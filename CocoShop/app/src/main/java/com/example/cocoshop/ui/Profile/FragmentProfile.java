package com.example.cocoshop.ui.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cocoshop.Models.User;
import com.example.cocoshop.Models.UserAccount;
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.ChangePasswordScreen.ChangePasswordScreen;
import com.example.cocoshop.Screen.AuthScreen.LoginScreen;
import com.example.cocoshop.Screen.HomeScreen.HomeScreen;
import com.example.cocoshop.fireStore.FireStoreUser;
import com.example.cocoshop.firebaseStorange.FirebaseStorangeUser;
import com.example.cocoshop.permission.PermissionReadExternalStorage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static android.app.Activity.RESULT_OK;


public class FragmentProfile extends Fragment {
    private static Uri uriAvata = null;
    private ImageView mImgSetting,imgAvata;
    private View viewContentProfile;
    private TextView tvNameDisplay,tvEmailDisplay;
    private static final FirebaseUser user;
    //private FirebaseFirestore firestore;
    public static final int CODE_ID_IMAGE = 1000;
    private static final FirebaseStorage store;
    private static final StorageReference storeRef;
    private static Uri filePath;
    static {
        user = FirebaseAuth.getInstance().getCurrentUser();
        store =  FirebaseStorage.getInstance();
        storeRef = store.getReference();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mImgSetting = (ImageView)view.findViewById(R.id.imgSetting);
        imgAvata = (ImageView)view.findViewById(R.id.ivAvata);
        registerForContextMenu(mImgSetting);
        mImgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.showContextMenu();
            }
        });
        viewContentProfile = view.findViewById(R.id.ltcontentProfile);
        tvNameDisplay = (TextView)viewContentProfile.findViewById(R.id.tvNameDisplay);
        tvEmailDisplay = (TextView)viewContentProfile.findViewById(R.id.tvemailDisplay);
        tvNameDisplay.setText("Unknow");
        //firestore = FirebaseFirestore.getInstance();
        setUpProfile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_item_setting,menu);
        menu.setHeaderTitle("Your choose!");
    }

    @Override
    public void onStart() {
        super.onStart();
        HomeScreen.isCurrentFragment = "Profile";
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.changeAvata:
                Toast.makeText(getContext(), "Change Avata", Toast.LENGTH_SHORT).show();
                if(PermissionReadExternalStorage.checkPermission(getContext(),getActivity())){

                }else{
                    pickImageFromGallery();
                }
                return true;
            case R.id.changePassword:
                intent = new Intent(getContext(), ChangePasswordScreen.class);
                startActivity(intent);
                Toast.makeText(getContext(), "Change Password", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                intent = new Intent(getContext(), LoginScreen.class);
                startActivity(intent);
                getActivity().finish();
                LoginScreen.mAuth.signOut();
                //AuthGoogle.signOut();
                User.setKind("");
                User.setEmail("");
                uriAvata = null;
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void setUpProfile(){
        tvEmailDisplay.setText(User.getEmail()==null ? "Unknow" : User.getEmail());
        tvNameDisplay.setText(User.getKind() == null ?"Unknow" :User.getKind());
        if(uriAvata == null){
            storeRef.child("avatas/"+user.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.with(getContext()).load(uri).error(R.drawable.defaultavata).placeholder(R.drawable.defaultavata).into(imgAvata);
                    uriAvata = uri;
                }
            });
        }else{
            Picasso.with(getContext()).load(uriAvata).into(imgAvata);
        }
    }

    private void pickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,CODE_ID_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PermissionReadExternalStorage.PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                pickImageFromGallery();
            }else{
                Toast.makeText(getActivity(), "Permission denied ....", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        if(requestCode == CODE_ID_IMAGE){
            if(resultCode == RESULT_OK) {
                try {
                    filePath = data.getData();
                    if (filePath != null) {
                        imgAvata.setImageURI(filePath);
                        FirebaseStorangeUser.putImageAvata(filePath, getContext());
                        storeRef.child("avatas").child(user.getUid()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull final Task<Uri> taskUrl) {
                                if (taskUrl.isSuccessful()) {
                                    Log.d("Link URI",taskUrl.getResult().toString());
                                    UserAccount userAccount = new UserAccount(User.getKind(), User.getEmail(), taskUrl.getResult().toString());
                                    FireStoreUser.updateUser(userAccount, getContext());
                                }
                            }
                        });
                    }
                }catch(Exception ex){
                    Log.d("Eorror=============",ex.getMessage(),ex.getCause());
                    Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
