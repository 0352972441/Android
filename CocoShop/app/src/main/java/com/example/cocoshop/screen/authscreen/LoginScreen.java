package com.example.cocoshop.screen.authscreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocoshop.auth.AuthGoogle;
import com.example.cocoshop.models.User;
import com.example.cocoshop.models.UserAccount;
import com.example.cocoshop.R;
import com.example.cocoshop.permission.InternetPermission;
import com.example.cocoshop.screen.HomeScreen;
import com.example.cocoshop.animation.Animations;
import com.example.cocoshop.firebase.FireStoreUser;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LoginScreen extends AppCompatActivity {
    private String email;
    private String password ;

    private Button mbtnLogin;
    private TextView mtxChange,mTxTitle;
    private ImageView imgSignInWithGoogle, imgLogo;
    private TextInputEditText medPassword,medEmail;
    private Animations animations;

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
        setContentView(R.layout.activity_login_screen);
        mbtnLogin = (Button)findViewById(R.id.mbtnLg);
        mtxChange = (TextView)findViewById(R.id.mtxChange);
        mTxTitle = (TextView)findViewById(R.id.mtxTtile);
        medPassword = (TextInputEditText)findViewById(R.id.medPassword);
        medEmail = (TextInputEditText)findViewById(R.id.medGmail);
        imgSignInWithGoogle = (ImageView)findViewById(R.id.imgSignInGoogle);
        imgLogo = (ImageView)findViewById(R.id.logo);
        animations = new Animations(this);
        authGoogle = new AuthGoogle(mAuth,this,getString(R.string.default_web_client_id));

        setOnClickButtonLogin();
        setOnClickTextSwitch();
        signInWithGoogle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        imgLogo.startAnimation(animations.fadeIn(2000));
        mTxTitle.startAnimation(animations.move(2000));
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
                currentUser.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                    @Override
                    public void onComplete(@NonNull Task<GetTokenResult> task) {
                        if(task.isSuccessful()){
                            if (task.getResult().getExpirationTimestamp() > 0) {
                                Calendar calendar = Calendar.getInstance();
                                Date now = calendar.getTime();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                                String localTime = sdf.format(new Date(task.getResult().getExpirationTimestamp() * 1000));
                                Date date = new Date();
                                try {
                                    date = sdf.parse(localTime);//get local date
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (date.after(now)) {
                                    User.setKind("Basic");
                                    User.setEmail(currentUser.getEmail());
                                    Intent intent = new Intent(LoginScreen.this, HomeScreen.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    mAuth.signOut();
                                    Intent intent = new Intent(LoginScreen.this, HomeScreen.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                mAuth.signOut();
                            }
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mAuth.signOut();
                        Intent intent = new Intent(LoginScreen.this, LoginScreen.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mAuth.signOut();
                        Intent intent = new Intent(LoginScreen.this, LoginScreen.class);
                        startActivity(intent);
                        finish();
                        Log.d("tag",e.getMessage());
                    }
                });
        }
    }

    private void setOnClickButtonLogin(){
        mbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtnLogin.startAnimation(animations.zoomOut(100));
                if(signUp()){
                        final ProgressDialog dialog = new ProgressDialog(LoginScreen.this);
                        dialog.setTitle("Please waiting ...");
                        dialog.show();
                        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    // Login into Screen home
                                    // Lưu tài khoản và mật khẩu vào User
                                    //dialog.dismiss();
                                    User.setKind("Basic");
                                    User.setEmail(email);
                                    loginSuccessed();
                                }else{
                                    dialog.dismiss();
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
                mtxChange.startAnimation(animations.zoonIn(100));
                Intent intent = new Intent(LoginScreen.this, SignUpScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private boolean signUp(){
        email = medEmail.getText().toString();
        password = medPassword.getText().toString();
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
                Log.d("Result Account",data.toString());
                firebaseSignInWith(currentAccount.getIdToken());
            }catch (ApiException e){
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
        final ProgressDialog dialog = new ProgressDialog(LoginScreen.this);
        dialog.show();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    dialog.dismiss();
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    try {
                        UserAccount userAccount = new UserAccount("basic",email==null ? firebaseUser.getEmail() : email,String.valueOf(R.drawable.defaultavata),firebaseUser.getUid());
                        FireStoreUser.addUser(userAccount,LoginScreen.this);
                        //FireStoreUser.addUser("basic",email==null ? firebaseUser.getEmail() : email,"",firebaseUser.getUid(),LoginScreen.this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    User.setKind("Basic");
                    User.setEmail(task.getResult().getUser().getEmail());
                    loginSuccessed();
                }else{
                    dialog.dismiss();
                    Toast.makeText(LoginScreen.this, "Logged failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}
