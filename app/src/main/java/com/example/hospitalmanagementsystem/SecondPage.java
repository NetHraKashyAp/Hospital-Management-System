package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;

public class SecondPage extends AppCompatActivity {
    Button BookAppointment;
    TextView FullName,Age,mobileno,Date,Time;
    RadioGroup Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        BookAppointment=findViewById(R.id.BookAppointment);


        BookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Third_Page.class);
                startActivity(intent);

            }
        });
    }
}
