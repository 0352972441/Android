package com.example.cocoshop.Screen.AuthScreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocoshop.Auth.AuthGoogle;
import com.example.cocoshop.Models.User;
import com.example.cocoshop.Models.UserAccount;
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.HomeScreen.HomeScreen;
import com.example.cocoshop.fireStore.FireStoreUser;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.IOException;

public class SignUpScreenActivity extends AppCompatActivity {

    private String email;
    private String password ;
    private Button mbtnRegister;
    private TextView mtxChange;
    private ImageView imgSignInWithGoogle;
    private TextInputEditText medPassword,medEmail,medConfirmPassword;
    public static final FirebaseAuth mAuth;
    public static FirebaseUser currentUser;
    private static final int RC_SIGN_IN = 9001;
    private AuthGoogle authGoogle;
    static {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        mbtnRegister = (Button)findViewById(R.id.mbtnRg);
        mtxChange = (TextView)findViewById(R.id.mtxChange);
        medPassword = (TextInputEditText)findViewById(R.id.medPassword);
        medEmail = (TextInputEditText)findViewById(R.id.medGmail);
        imgSignInWithGoogle = (ImageView)findViewById(R.id.imgSignInGoogle);
        medConfirmPassword = (TextInputEditText)findViewById(R.id.medConfirmPassword);
        authGoogle = new AuthGoogle(mAuth,this,getString(R.string.default_web_client_id));
        setOnClickButtonLogin();
        setOnClickTextSwitch();
        signInWithGoogle();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setOnClickButtonLogin(){
        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(modeAuth.LOGIN == currentMode){
                if(signUp()){
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpScreenActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpScreenActivity.this, "Register a successfuly ", Toast.LENGTH_SHORT).show();
                                currentUser = task.getResult().getUser();
                                // Login into Screen home
                                // Lưu tài khoản và mật khẩu vào User
                                User.setEmail(email);
                                User.setKind("Basic");
                                try {
                                    UserAccount userAccount = new UserAccount("basic",email.isEmpty()? currentUser.getEmail() : email,String.valueOf(R.drawable.defaultavata),currentUser.getUid());
                                    FireStoreUser.addUser(userAccount,SignUpScreenActivity.this);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                registerSuccessed();
                            }else{
                                Log.w("Thông tin",task.getException());
                                Toast.makeText(SignUpScreenActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }/* {
                    if(signUp()){
                        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    AddUser.addUser("Unknow",email,null);
                                    formLogin();
                                    Toast.makeText(LoginScreen.this, "Account registration a successed ", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(LoginScreen.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }*/
        });
    }

    public void registerSuccessed(){
        Intent intent = new Intent(SignUpScreenActivity.this, HomeScreen.class);
        startActivity(intent);
        finish();
    }

    private void setOnClickTextSwitch(){
        mtxChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpScreenActivity.this, LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private boolean signUp(){
        email = medEmail.getText().toString();
        password = medPassword.getText().toString();
        //String confirmPassword = mtxilConfirmPassword.getEditText().getText().toString();
        if(email.isEmpty()){
            medEmail.setError("Email hasn't empty");
            return false;
        }else if(!email.contains("@")){
            medEmail.setError("Invalid email");
        }else if(password.isEmpty()){
            medPassword.setError("Password hasn't empty");
            return false;
        }else if(password.length()< 6){
            medPassword.setError("Password at least 8 character");
            return false;
        }
        if(!medConfirmPassword.getText().toString().equals(password)){
                medConfirmPassword.setError("Password doesn't match");
                return false;
        }
        return  true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> account = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount currentAccount = account.getResult(ApiException.class);
                firebaseSignInWith(currentAccount.getIdToken());
            }catch (ApiException e){
                Log.w("Lỗi đăng nhập",e);
                Toast.makeText(this, "Occured error"+e.getStatusCode(), Toast.LENGTH_SHORT).show();
            }
        }else{
            Log.d("SignIn failed","Failed");
        }
    }


    private void signInWithGoogle(){
        imgSignInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = authGoogle.getSignInIntent();
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });
    }

    public void firebaseSignInWith(String idToken){
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(SignUpScreenActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    try {
                        UserAccount userAccount = new UserAccount("basic",email==null ? firebaseUser.getEmail() : email,String.valueOf(R.drawable.defaultavata),firebaseUser.getUid());
                        FireStoreUser.addUser(userAccount,SignUpScreenActivity.this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    User.setKind("Basic");
                    User.setEmail(task.getResult().getUser().getEmail());
                    Toast.makeText(SignUpScreenActivity.this, "Login Successed", Toast.LENGTH_SHORT).show();
                    registerSuccessed();
                }else{
                    Toast.makeText(SignUpScreenActivity.this, "Logged failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
