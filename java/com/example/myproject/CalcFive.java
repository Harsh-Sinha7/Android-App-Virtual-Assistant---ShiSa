package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalcFive extends AppCompatActivity {
    EditText pres_value,fut_value;
    EditText Rate,Time;
    Button Calculate = null;
    EditText fvans,pvans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_five);

        pres_value = findViewById(R.id.editText);
        Rate = findViewById(R.id.editText2);
        fut_value = findViewById(R.id.editText3);
        Time = findViewById(R.id.editTextTime);
        Calculate = findViewById(R.id.button);
        fvans =findViewById(R.id.editText4);
        pvans=findViewById(R.id.editText5);

        try{
            Calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String PV = pres_value.getText().toString();
                    String FV = fut_value.getText().toString();
                    String R = Rate.getText().toString();
                    String T =Time.getText().toString();
                    if(TextUtils.isEmpty(PV)){
                        pres_value.setError("Please enter the Present Value");
                        pres_value.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(FV)){
                        fut_value.setError("Please enter the Present Value");
                        fut_value.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(R)){
                        Rate.setError("Please enter the Rate of Interest");
                        Rate.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(T)){
                        Time.setError("Please enter the Time Duration");
                        Time.requestFocus();
                        return;
                    }

                    double pv = Double.parseDouble(PV);
                    double fv = Double.parseDouble(FV);
                    double r = Double.parseDouble(R);
                    double n = Double.parseDouble(T);
                    double factor = Math.pow((1+r),n);

                    double fv_ans=pv*factor;
                    double pv_ans=fv/factor;



                    String ans = String.format("%.2f",fv_ans);
                    String ans1 = String.format("%.2f",pv_ans);

                    fvans.setText(ans);
                    pvans.setText(ans1);

                }
            });

        }
        catch(NullPointerException ignored){

        }




    }
}
