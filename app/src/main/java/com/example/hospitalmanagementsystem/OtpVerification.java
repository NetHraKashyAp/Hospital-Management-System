package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class OtpVerification extends AppCompatActivity {
    private Button verify;
    private EditText otp;
    private ProgressBar progressBar;
    private String VerificationID;
    private FirebaseAuth auth;
    String name,username,email,password,gender,phone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        verify = findViewById(R.id.verify_btn);
        otp = findViewById(R.id.otp);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        auth = FirebaseAuth.getInstance();
        phone = "+91"+getIntent().getStringExtra("phoneNumber");
        name = getIntent().getStringExtra("name");
        username = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        gender = getIntent().getStringExtra("gender");
        sendVerificationPhone(phone);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = otp.getText().toString();
                if (code.isEmpty() || code.length()<6) {
                    Toast.makeText(OtpVerification.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        });
    }
    private void signInWithCredential(PhoneAuthCredential phoneAuthCredential){
        auth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(OtpVerification.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    StoreUserData();
                    Intent intent = new Intent(OtpVerification.this, BottomNavigation.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(OtpVerification.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendVerificationPhone(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60,
                TimeUnit.SECONDS,OtpVerification.this,mcallbacks);

    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull  PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerificationID = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull  PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                otp.setText(code);
                verifyCode(code);
                progressBar.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull  FirebaseException e) {
            Toast.makeText(OtpVerification.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    };
    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationID, code);
        signInWithCredential(credential);
    }
    private void StoreUserData(){
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = rootNode.getReference("Users");
        Helperclass helperclass = new Helperclass(name,username,email,password,phone,gender);
        databaseReference.child(phone).setValue(helperclass);

    }
}