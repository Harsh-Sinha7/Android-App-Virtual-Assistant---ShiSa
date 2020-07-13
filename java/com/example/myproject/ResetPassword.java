package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    private EditText emailId;
    private Button reset=null;
    private FirebaseAuth mAuth;


    public void goToLogin(View view){
        startActivity(new Intent(ResetPassword.this,LoginActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        emailId=(EditText)findViewById(R.id.email);
        reset=(Button)findViewById(R.id.btn_sendEmail);
        mAuth=FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =emailId.getText().toString();
                if(TextUtils.isEmpty(email)){
                    emailId.setError("Please Enter Valid Email Id");
                    emailId.requestFocus();
                }
                else {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ResetPassword.this,"If the email mentioned is correct, you will receive a verification link!",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(ResetPassword.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
