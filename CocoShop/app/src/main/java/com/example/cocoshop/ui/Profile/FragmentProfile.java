package com.example.cocoshop.ui.Profile;

import android.app.ProgressDialog;
import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.provider.MediaStore;
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
import com.example.cocoshop.Adapter.Item_Profile_Adapter;
import com.example.cocoshop.Models.User;
import com.example.cocoshop.Models.UserAccount;
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.ChangePasswordScreen.ChangePasswordScreen;
import com.example.cocoshop.Screen.AuthScreen.LoginScreen;
import com.example.cocoshop.Screen.HomeScreen.HomeScreen;
import com.example.cocoshop.Screen.profileActivity.InfomationActivity;
import com.example.cocoshop.Screen.profileActivity.MyFavoriteActivity;
import com.example.cocoshop.Screen.profileActivity.PolicyActivity;
import com.example.cocoshop.Screen.profileActivity.TermOfUseActivity;
import com.example.cocoshop.fireStore.FireStoreUser;
import com.example.cocoshop.firebaseStorange.FirebaseStorangeUser;
import com.example.cocoshop.listener.Listener;
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
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


public class FragmentProfile extends Fragment {
    private ImageView mImgSetting,imgAvata;
    private Handler handler;
    private TextView tvNameDisplay,tvEmailDisplay;
    private static final FirebaseUser user;
    public static final int CODE_ID_IMAGE = 1000;
    private static final FirebaseStorage store;
    private static final StorageReference storeRef;
    private static Uri filePath;
    private Context context;
    private RecyclerView itemProfile;
    private Item_Profile_Adapter item_profile_adapter;
    private Intent intent;
    private static final Object[] activity = {MyFavoriteActivity.class, InfomationActivity.class, PolicyActivity.class, TermOfUseActivity.class};
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
        tvNameDisplay = (TextView)view.findViewById(R.id.tvNameDisplay);
        tvEmailDisplay = (TextView)view.findViewById(R.id.tvemailDisplay);
        itemProfile = (RecyclerView)view.findViewById(R.id.item_profile);
        item_profile_adapter = new Item_Profile_Adapter();
        itemProfile.setAdapter(item_profile_adapter);
        itemProfile.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        tvNameDisplay.setText("Unknow");
        this.context = getContext();
        onClickItemListener();
        HomeScreen.setThemeFragment(R.style.ActionBarProfile);
    }

    private void onClickItemListener(){
        item_profile_adapter.setOnClickItemListener(new Listener() {
            @Override
            public void listener(int position) {
                if(position== activity.length){
                    intent = new Intent(getContext(), LoginScreen.class);
                    startActivity(intent);
                    getActivity().finish();
                    LoginScreen.mAuth.signOut();
                    User.setKind("");
                    User.setEmail("");
                }else{
                    intent = new Intent(getContext(), (Class<?>) activity[position]);
                    startActivity(intent);
                }
            }
        });
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
        setUpProfile();
    }

    @Override
    public void onStart() {
        super.onStart();
        HomeScreen.isCurrentFragment = "Profile";
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
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
                User.setKind("");
                User.setEmail("");
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void setUpProfile(){
        handler = new Handler();
        try {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tvEmailDisplay.setText(User.getEmail() == null ? "Unknow" : User.getEmail());
                    tvNameDisplay.setText(User.getKind() == null ? "Unknow" : User.getKind());
                        if (context != null) {
                            storeRef.child("avatas/" + user.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Picasso.with(context).load(uri).error(R.drawable.defaultavata).placeholder(R.drawable.defaultavata).into(imgAvata);
                                }
                            });
                    }
                }
            });
        }catch (Exception ex){
            Log.d("Error",ex.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
                        Picasso.with(context).load(filePath).into(imgAvata);
                        new FirebaseStorangeUser(filePath,storeRef,getContext()).execute();
                    }
                }catch(Exception ex){
                    Log.d("Eorror=============",ex.getMessage(),ex.getCause());
                    Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
