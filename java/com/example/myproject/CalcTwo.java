package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalcTwo extends AppCompatActivity {
    EditText Factor;
    EditText Rate;
    Button Calculate = null;
    EditText Time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_two);

        Factor = findViewById(R.id.editText);
        Rate = findViewById(R.id.editText2);
        Calculate = findViewById(R.id.button);
        Time =findViewById(R.id.editText4);
        try{
            Calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String F = Factor.getText().toString();
                    String R = Rate.getText().toString();

                    if(TextUtils.isEmpty(F)){
                        Factor.setError("Enter Principle Amount");
                        Factor.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(R)){
                        Rate.setError("Enter Rate of Interest");
                        Rate.requestFocus();
                        return;
                    }
                    double f = Double.parseDouble(F);
                    double r = Double.parseDouble(R);
                    double time = f/r;
                    String ans = String.format("%.2f",time);
                    Time.setText(ans);

                }
            });

        }
        catch(NullPointerException ignored){

        }

    }
}
