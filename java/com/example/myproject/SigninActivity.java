package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SigninActivity extends AppCompatActivity {
    EditText number,fname,lname,emailId,password;
    Button btnSignUp;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        fname = (EditText) findViewById(R.id.first_name);
        lname= (EditText) findViewById(R.id.last_name);
        number = (EditText) findViewById(R.id.phoneNumber);
        emailId = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnSignUp = findViewById(R.id.btn_login);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(!email.isEmpty() && !pwd.isEmpty()){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){

                                Toast.makeText(SigninActivity.this,"Sign Up Unsuccessfull",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                sendUserData();
                                Toast.makeText(SigninActivity.this,"Account Created",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SigninActivity.this,MainActivity.class));
                            }
                        }
                    });
                }
            }
        });
    }
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(mFirebaseAuth.getUid());

        String u_fname=fname.getText().toString();
        String u_lname=lname.getText().toString();
        String u_number = number.getText().toString();
        String u_email = emailId.getText().toString();

        UserProfile userProfile =new UserProfile(u_fname,u_lname,u_number,u_email);
        myRef.setValue(userProfile);
    }
}
