package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DrLoginPage extends AppCompatActivity {
    Button Login;
    EditText email,password;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_login_page);
        Login=findViewById(R.id.login_button);
        email = findViewById(R.id.dremail);
        password = findViewById(R.id.drpass);
        if(Login!=null) {

            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dremail = email.getText().toString();
                    String drpass = password.getText().toString();
                    if (drpass == null || dremail == null) {
                        Toast.makeText(DrLoginPage.this, "Field is mandatory", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(DrLoginPage.this, viewappointments.class);
                        startActivity(intent);
                    }
                }
            });
        }

    }
}
