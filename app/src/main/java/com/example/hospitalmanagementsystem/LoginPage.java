package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    EditText phoneNumber,password;
    Button Login;
    TextView create_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        phoneNumber = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);
        Login = findViewById(R.id.loginButton);
        create_account = findViewById(R.id.signup);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login_clicked();

            }
        });
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this,SignUp.class);
                startActivity(intent);
            }
        });

    }
    public void Login_clicked(){
        if(!ValidateFields()){
            return;
        }
        String PhoneNumber = phoneNumber.getText().toString().trim();
        String Password = password.getText().toString().trim();

        Query checkuser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNumber").equalTo(PhoneNumber);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if(snapshot.exists()){
                    phoneNumber.setError(null);
                    String systemPassword = snapshot.child(PhoneNumber).child("password").getValue(String.class);
                    if(systemPassword.equals(Password)){
                        Intent intent = new Intent(LoginPage.this,BottomNavigation.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginPage.this,"Password does not match!",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginPage.this,"No such user exists!",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginPage.this,error.getMessage(),Toast.LENGTH_SHORT).show();



            }
        });


    }

    private Boolean ValidateFields(){
        String Username = phoneNumber.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if(Username.isEmpty()){
            Toast.makeText(LoginPage.this,"Username cant be empty",Toast.LENGTH_SHORT).show();
        }else if(Password.isEmpty()){
            Toast.makeText(LoginPage.this,"Password cant be empty",Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}