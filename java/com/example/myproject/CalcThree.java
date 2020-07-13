package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalcThree extends AppCompatActivity {
    EditText I_Rate;
    EditText F_Rate;
    Button Calculate = null;
    EditText Return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_three);
        I_Rate = findViewById(R.id.editText);
        F_Rate = findViewById(R.id.editText2);
        Calculate = findViewById(R.id.button);
        Return =findViewById(R.id.editText4);
        try{
            Calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String I = I_Rate.getText().toString();
                    String R = F_Rate.getText().toString();

                    if(TextUtils.isEmpty(I)){
                        I_Rate.setError("Enter Principle Amount");
                        I_Rate.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(R)){
                        F_Rate.setError("Enter Rate of Interest");
                        F_Rate.requestFocus();
                        return;
                    }
                    double i = Double.parseDouble(I);
                    double r = Double.parseDouble(R);
                    double a = i+1;
                    double b = r+1;
                    double c =(a/b)-1;
                    double ret=c*100;

                    String ans = String.format("%.2f",ret);
                    Return.setText(ans);

                }
            });

        }
        catch(NullPointerException ignored){

        }



    }
}
