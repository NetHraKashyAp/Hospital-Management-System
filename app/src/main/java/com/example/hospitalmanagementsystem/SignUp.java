package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText name,username,email,password,confirmpassword,phoneNumber;

    Button signup;
    TextView login;
    RadioGroup radioGroup;
    RadioButton gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.name);
        username = findViewById(R.id.Username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
        confirmpassword = findViewById(R.id.Pass2);
        phoneNumber = findViewById(R.id.phone);
        signup = findViewById(R.id.btn_signup);
        login = findViewById(R.id.login);
        radioGroup = findViewById(R.id.radioGroup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignupUser();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,LoginPage.class);
                startActivity(intent);
            }
        });
    }
    private boolean validateFullName()
    {
        String val = name.getText().toString().trim();
        if(val.isEmpty())
        {
            name.setError("Field Cannot be Empty");
            return false;
        }else
        {
            name.setError(null);
            //regname.setErrorEnabled(true);
            return true;
        }
    }
    private boolean validateUserName()
    {
        String val = username.getText().toString().trim();
        //String whitespaces = "\\A\\w{4,20},\\z";
        if(val.isEmpty())
        {
            username.setError("Field Cannot be Empty");
            return false;
        }else if(val.length()>=20){
            username.setError("User Name is too large");
            return false;
        }
        else
        {
            username.setError(null);
            //username.setErrorEnabled(true);
            return true;
        }
    }
    private boolean validateEmail()
    {
        String val = email.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty())
        {
            email.setError("Field Cannot be Empty");
            return false;
        }else if(!val.matches(checkEmail)){
            email.setError("Invalid Email");
            return false;
        } else {
            email.setError(null);
            //email.setErrorEnabled(true);
            return true;
        }
    }
    private boolean validatePassword()
    {
        String val = password.getText().toString().trim();
        if(val.isEmpty())
        {
            password.setError("Field Cannot be Empty");
            return false;
        } else {
            password.setError(null);
            // password.setErrorEnabled(true);
            return true;
        }
    }
    private boolean validateConfirmPassword()
    {
        String val1 = password.getText().toString().trim();
        String val = confirmpassword.getText().toString().trim();

        if(val.isEmpty())
        {
            confirmpassword.setError("Field Cannot be Empty");
            return false;
        }else
        {
            if(val.equals(val1)){
                confirmpassword.setError(null);
                return true;

            }else{
                return false;
            }
        }
    }
    private boolean validateNumber()
    {
        String val = phoneNumber.getText().toString().trim();

        if(val.isEmpty())
        {
            phoneNumber.setError("Field Cannot be Empty");
            return false;
        } else {
            phoneNumber.setError(null);
            return true;
        }
    }
    private boolean validateGender(){
        if(radioGroup.getCheckedRadioButtonId()==-1){
            Toast.makeText(SignUp.this,"Please select gender!",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
    public void SignupUser()
    {
        if(!validateFullName() | !validateEmail() | !validatePassword() | !validateUserName() | !validateConfirmPassword() | !validateNumber() | !validateGender()){
            return;
        }else {
            Validated();
        }

    }
    public void Validated()
    {
        String sname = name.getText().toString();
        String susername = username.getText().toString();
        String spassword = password.getText().toString();
        String sconfirmpassword = confirmpassword.getText().toString();
        String semail = email.getText().toString();
        String sphoneNumber = phoneNumber.getText().toString();
        gender = findViewById(radioGroup.getCheckedRadioButtonId());
        String selected_gender = gender.getText().toString();
        Intent intent = new Intent(SignUp.this,OtpVerification.class);
        intent.putExtra("phoneNumber",sphoneNumber);
        intent.putExtra("name",sname);
        intent.putExtra("username",susername);
        intent.putExtra("email",semail);
        intent.putExtra("password",spassword);
        intent.putExtra("gender",selected_gender);
        startActivity(intent);
    }



}