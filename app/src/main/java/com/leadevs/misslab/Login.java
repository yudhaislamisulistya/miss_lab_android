package com.leadevs.misslab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.leadevs.misslab.ui.praktikan.HomePraktikanActivity;

public class Login extends AppCompatActivity {

    EditText ETemail, ETpassword;
    LoadingDialogLogin loadingDialogLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        ETemail = (EditText) findViewById(R.id.email);
        ETpassword = (EditText) findViewById(R.id.password);
        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
        loadingDialogLogin = new LoadingDialogLogin(Login.this);

    }

    public void goHomeScreen(View view) {
        final String email = ETemail.getText().toString();
        final String password = ETpassword.getText().toString();
        System.out.println(email + "  :  " +password);
        loadingDialogLogin.startLoadingDialog();
        final Handler handler  = new Handler();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loadingDialogLogin.dismissDialog();
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                    finish();
                                }
                            }, 3000);
                        } else {
                            // If sign in fails, display a message to the user.
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loadingDialogLogin.dismissDialog();
                                    Toast.makeText(Login.this, "Surel atau Kata Sandi Keliru.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }, 3000);

                        }

                        // ...
                    }
                });

    }

    public void goHomeScreenPraktikan(View view){
        startActivity(new Intent(Login.this, HomePraktikanActivity.class));
        Animatoo.animateFade(Login.this);
    }
}