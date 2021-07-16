package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class viewappointments extends AppCompatActivity {
    Button ViewAppointment;
    Button ApplyForLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewappointments);
        ViewAppointment=findViewById(R.id.app);
        ApplyForLeave=findViewById(R.id.leave);
        ViewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),appointments.class);
                startActivity(intent);
            }
        });
        ApplyForLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ap.class);
                startActivity(intent);
            }
        });
    }

}