package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ap extends AppCompatActivity {
    Button applyleave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ap);
        applyleave=findViewById(R.id.applyleave);
        applyleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ap.this, "Leave applied successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}