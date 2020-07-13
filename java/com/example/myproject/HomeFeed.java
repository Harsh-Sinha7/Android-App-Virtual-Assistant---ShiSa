package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeFeed extends AppCompatActivity {


    public void goToCalcOne(View view){
        startActivity(new Intent(HomeFeed.this,CalcOne.class));
    }
    public void goToCalcTwo(View view){
        startActivity(new Intent(HomeFeed.this,CalcTwo.class));
    }
    public void goToCalcThree(View view){
        startActivity(new Intent(HomeFeed.this,CalcThree.class));
    }
    public void goToCalcFour(View view){
        startActivity(new Intent(HomeFeed.this,CalcFour.class));
    }
    public void goToCalcFive(View view){
        startActivity(new Intent(HomeFeed.this,CalcFive.class));
    }
    public void goToCalcSix(View view){
        startActivity(new Intent(HomeFeed.this,CalcSix.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_feed);
    }
}
