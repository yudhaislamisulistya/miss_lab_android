package com.leadevs.misslab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText ETemail, ETpassword;
    LoadingDialogLogin loadingDialogLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ETemail = (EditText) findViewById(R.id.email);
        ETpassword = (EditText) findViewById(R.id.password);
        loadingDialogLogin = new LoadingDialogLogin(Login.this);

    }

    public void goHomeScreen(View view) {
        final String email = ETemail.getText().toString();
        final String password = ETpassword.getText().toString();
        System.out.println(email + "  :  " +password);
        loadingDialogLogin.startLoadingDialog();
        Handler handler  = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialogLogin.dismissDialog();
                    startActivity(new Intent(Login.this, MainActivity.class));
            }
        }, 3000);
    }
}