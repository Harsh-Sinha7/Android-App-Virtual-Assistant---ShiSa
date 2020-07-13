package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalcSix extends AppCompatActivity {
    EditText buy_items,free_items,discount;
    Button Calculate = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_six);

        buy_items = findViewById(R.id.editText);
        free_items= findViewById(R.id.editText2);
        discount = findViewById(R.id.editText3);
        Calculate = findViewById(R.id.button);


        try{
            Calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String BT = buy_items.getText().toString();
                    String FT = free_items.getText().toString();
                    if(TextUtils.isEmpty(BT)){
                        buy_items.setError("Please enter the number of they are asking you to buy");
                        buy_items.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(FT)){
                        free_items.setError("Please enter the number of items they are offering you for free");
                        free_items.requestFocus();
                        return;
                    }

                    double bt = Double.parseDouble(BT);
                    double ft = Double.parseDouble(FT);
                    double factor = ft/(bt+ft)*100;

                    String ans = String.format("%.3f",factor);
                    discount.setText(ans);
                }
            });

        }
        catch(NullPointerException ignored){

        }

    }
}
