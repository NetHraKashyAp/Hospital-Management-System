package com.example.hospitalmanagementsystem;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class welcome extends AppCompatActivity {
    RadioButton patient,doctor;
    Button submit;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        radioGroup = findViewById(R.id.rdGroup);
        patient = findViewById(R.id.rbBhop);
        doctor = findViewById(R.id.rbmum);
        submit = findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(patient.isChecked()){
                    Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                    startActivity(intent);
                }
                else if(doctor.isChecked()){
                    Intent intent = new Intent(getApplicationContext(),DrLoginPage.class);
                    startActivity(intent);
                }

            }
        });
    }
}