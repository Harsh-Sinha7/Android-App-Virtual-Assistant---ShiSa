package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public void goToSignIn(View view){
        startActivity(new Intent(MainActivity.this,SigninActivity.class));
    }
    public void goToLogin(View view){
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
