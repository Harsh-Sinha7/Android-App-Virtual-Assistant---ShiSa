package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuOptions extends AppCompatActivity {
    public void goToMain(View view){
        finish();
    }
    public void goToCalc (View view){
        startActivity(new Intent(MenuOptions.this,HomeFeed.class));
    }
    public void goToTranslate (View view){
        startActivity(new Intent(MenuOptions.this,Translator.class));
    }
    public void goToEmail (View view){
        startActivity(new Intent(MenuOptions.this,ActEmail.class));
    }
    public void goToImage (View view){
        startActivity(new Intent(MenuOptions.this,ImageToText.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_options);
    }
}
