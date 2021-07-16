package com.example.hospitalmanagementsystem;

import android.widget.EditText;

public class Helperclass {
    private String name;
    private String username;
    private String password;
    private String Confirmpassword;
    private String email;
    private String phoneNumber;
    public Helperclass() {

    }
    public Helperclass(String name,String username,String email,String password,String confirmpassword,String phoneNumber)
    {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.Confirmpassword = confirmpassword;
    }



    public String getName()
    {
        return name;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public String getConfirmpassword() {
        return Confirmpassword;
    }

    public String getEmail()
    {
        return email;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
}
