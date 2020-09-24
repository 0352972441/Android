package com.example.cocoshop.ui.Profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocoshop.Models.User;
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.ChangePasswordScreen.ChangePasswordScreen;
import com.example.cocoshop.Screen.AuthScreen.LoginScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FragmentProfile extends Fragment {
    private ImageView mImgSetting;
    private View viewContentProfile;
    private TextView tvNameDisplay,tvEmailDisplay;
    private FirebaseUser user;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mImgSetting = (ImageView)view.findViewById(R.id.imgSetting);
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
        tvNameDisplay.setText("Toán");
        user = FirebaseAuth.getInstance().getCurrentUser();
        setUpProfile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_item_setting,menu);
        menu.setHeaderTitle("Your choose!");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.changeAvata:
                Toast.makeText(getContext(), "Change Avata", Toast.LENGTH_SHORT).show();
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
                User.setPassword("");
                User.setEmail("");
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void setUpProfile(){
        tvEmailDisplay.setText(user.getDisplayName()==null ? "Unknow" :user.getEmail());
        tvNameDisplay.setText(user.getDisplayName()==null ? "Unknow" : user.getDisplayName());
    }

}
