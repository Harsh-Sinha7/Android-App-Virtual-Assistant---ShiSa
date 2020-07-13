package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalcFour extends AppCompatActivity {
    EditText mark_price,sell_price;
    EditText prof_amnt,prof_per,loss_amnt,loss_per;
    Button Calculate = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_four);

        mark_price = findViewById(R.id.editText);
        sell_price= findViewById(R.id.editText2);
        loss_amnt = findViewById(R.id.editText3);
        loss_per = findViewById(R.id.editText4);
        prof_amnt = findViewById(R.id.editText5);
        prof_per = findViewById(R.id.editText6);
        Calculate = findViewById(R.id.button);


        try{
            Calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String MP = mark_price.getText().toString();
                    String SP = sell_price.getText().toString();
                    if(TextUtils.isEmpty(MP)){
                        mark_price.setError("Please enter the number of they are asking you to buy");
                        mark_price.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(SP)){
                        sell_price.setError("Please enter the number of items they are offering you for free");
                        sell_price.requestFocus();
                        return;
                    }

                    double mp = Double.parseDouble(MP);
                    double sp = Double.parseDouble(SP);

                    if(sp>mp){
                        double profit = (sp-mp);
                        double profit_perc=profit/mp*100;

                        String ans = String.format("%.2f",profit);
                        String ans1=String.format("%.2f",profit_perc);
                        String ans2= "No Loss";
                        String ans3 = "0";

                        prof_amnt.setText(ans);
                        prof_per.setText(ans1);
                        loss_amnt.setText(ans2);
                        loss_per.setText(ans3);

                    }
                    else if(sp<mp){
                        double loss = (mp-sp);
                        double loss_perc=loss/mp*100;

                        String ans = String.format("%.2f",loss);
                        String ans1=String.format("%.2f",loss_perc);
                        String ans2= "No Profit";
                        String ans3 = "0";

                        loss_amnt.setText(ans);
                        loss_per.setText(ans1);
                        prof_amnt.setText(ans2);
                        prof_per.setText(ans3);
                    }
                    else if(mp==sp){
                        loss_amnt.setText("None");
                        loss_per.setText("0");
                        prof_amnt.setText("None");
                        prof_per.setText("0");
                    }


                }
            });

        }
        catch(NullPointerException ignored){

        }



    }
}
