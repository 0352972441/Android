package com.example.cocoshop.Screen.AuthScreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
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
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.HomeScreen.HomeScreen;
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

public class LoginScreen extends AppCompatActivity {
    private String email;
    private String password ;
    private Button mbtnLogin;
    private TextView mtxChange,mtxTtile;
    private ImageView imgSignInWithGoogle;
    private TextInputEditText medPassword,medEmail;
    //private EditText medConfirmPassword;
    public static final FirebaseAuth mAuth;
    public static FirebaseUser currentUser;
    private static final int RC_SIGN_IN = 9001;
    private AuthGoogle authGoogle;
    private View viewSignInWith;
    static {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        mbtnLogin = (Button)findViewById(R.id.mbtnLg);
        mtxChange = (TextView)findViewById(R.id.mtxChange);
        mtxTtile = (TextView)findViewById(R.id.mtxTtile);
        medPassword = (TextInputEditText)findViewById(R.id.medPassword);
        medEmail = (TextInputEditText)findViewById(R.id.medGmail);
        imgSignInWithGoogle = (ImageView)findViewById(R.id.imgSignInGoogle);
        authGoogle = new AuthGoogle(mAuth,this,getString(R.string.default_web_client_id));
        setOnClickButtonLogin();
        setOnClickTextSwitch();
        signInWithGoogle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            User.setEmail(email);
            User.setPassword(password);
            Intent intent = new Intent(this,HomeScreen.class);
            startActivity(intent);
        }
    }

    private void setOnClickButtonLogin(){
        mbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(modeAuth.LOGIN == currentMode){
                    if(signUp()){
                        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginScreen.this, "Logged in successfuly ", Toast.LENGTH_SHORT).show();
                                    // Login into Screen home
                                    // Lưu tài khoản và mật khẩu vào User
                                    User.setEmail(email);
                                    User.setPassword(password);
                                    loginSuccessed();
                                }else{
                                    Log.w("Thông tin",task.getException());
                                    Toast.makeText(LoginScreen.this, "Account or password is incrrect", Toast.LENGTH_SHORT).show();
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

    public void loginSuccessed(){
        Intent intent = new Intent(LoginScreen.this, HomeScreen.class);
        startActivity(intent);
        finish();
    }

    private void setOnClickTextSwitch(){
        mtxChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, SignUpScreenActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginScreen.this).toBundle());
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
        mAuth.signInWithCredential(credential).addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginScreen.this, "Login Successed", Toast.LENGTH_SHORT).show();
                    loginSuccessed();
                }else{
                    Toast.makeText(LoginScreen.this, "Logged failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
