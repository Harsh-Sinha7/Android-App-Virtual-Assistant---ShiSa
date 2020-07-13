package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalcOne extends AppCompatActivity {
    EditText Principle;
    EditText Rate;
    EditText Time;
    EditText EMI;
    EditText Total_Interest;
    Button Calculate = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_one);
        Principle = findViewById(R.id.editText);
        Rate = findViewById(R.id.editText2);
        Time = findViewById(R.id.editText3);
        EMI = findViewById(R.id.editText4);
        Total_Interest=findViewById(R.id.editText5);
        Calculate = findViewById(R.id.button);


        try{
            Calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String P = Principle.getText().toString();
                    String R = Rate.getText().toString();
                    String T = Time.getText().toString();
                    if(TextUtils.isEmpty(P)){
                        Principle.setError("Enter Principle Amount");
                        Principle.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(R)){
                        Rate.setError("Enter Rate of Interest");
                        Rate.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(T)){
                        Time.setError("Enter Time");
                        Time.requestFocus();
                        return;
                    }
                    double p = Double.parseDouble(P);
                    if(p==0){
                        Principle.setError("Enter Principal Value greater than 0");
                    }
                    double r = Double.parseDouble(R);
                    double t = Double.parseDouble(T);
                    double rate = t/(12*100);
                    double month = t*12;
                    double factor = Math.pow((1+rate),month);
                    double dividend = factor *p*rate;
                    double divisor = factor-1;
                    double emi = dividend/divisor;
                    double interest = emi*month;
                    double tot_int=interest-p;
                    String ans = String.format("%.2f",emi);
                    String ans1=String.format("%.2f",tot_int);
                    EMI.setText(ans);
                    Total_Interest.setText(ans1);
                }
            });
        }
        catch (NullPointerException ignored){
        }
    }

}
