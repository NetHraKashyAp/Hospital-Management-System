package com.example.hospitalmanagementsystem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Ambulance_Service extends AppCompatActivity {

    ImageView imageView;
    TextInputLayout num,add;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    PatientDetails patientDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_service);
        imageView = findViewById(R.id.emergency);
        num = findViewById(R.id.number);
        add = findViewById(R.id.address);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Ambulance Service Patient details");
        patientDetails = new PatientDetails();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num_holder = num.getEditText().getText().toString();
                String add_holder = add.getEditText().getText().toString();
                String phone_number ="8309295163";
                if (TextUtils.isEmpty(num_holder) && TextUtils.isEmpty(add_holder)) {
                    Toast.makeText(Ambulance_Service.this, "Please Enter Number and Address", Toast.LENGTH_SHORT).show();
                } else {
                    Intent phone_intent = new Intent(Intent.ACTION_CALL);
                    phone_intent.setData(Uri.parse("tel:" + phone_number));
                    startActivity(phone_intent);
                    addDatatoFirebase(num_holder, add_holder);
                }

            }
        });

    }
    private void addDatatoFirebase(String number, String address) {
        patientDetails.setNumber(number);
        patientDetails.setAddress(address);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(number).setValue(address);
                Toast.makeText(Ambulance_Service.this, "Calling....", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Ambulance_Service.this, "Failed to reach you" + error, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
