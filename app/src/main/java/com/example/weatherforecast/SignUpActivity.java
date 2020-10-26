package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.weatherforecast.DBHelper.DatabaseHelper;

public class SignUpActivity extends AppCompatActivity {
    public DatabaseHelper helper = new DatabaseHelper(SignUpActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupmodulefragment);
    }
}
