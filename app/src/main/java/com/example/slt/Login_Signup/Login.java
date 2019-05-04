package com.example.slt.Login_Signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.slt.MainActivity;
import com.example.slt.R;


public class Login extends AppCompatActivity {
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void initViews(){
        signUp = (Button)findViewById(R.id.signupBtn);
    }

    public void signUp(View v){
        Intent intent = new Intent(Login.this, CreateAccount.class);
        startActivity(intent);
    }

    public void forgotPassword(View view) {
        Intent intent = new Intent(Login.this, ForgotPassword.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }
}
