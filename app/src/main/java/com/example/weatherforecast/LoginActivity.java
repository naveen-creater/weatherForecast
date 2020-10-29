package com.example.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.weatherforecast.Fragment.LoginModuleFragment;

public class LoginActivity extends FragmentActivity {
    private EditText emailid;
    private EditText pass;
    private TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        skip = findViewById(R.id.skip);
        skip.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });

        LoginModuleFragment loginmodulefragment = new LoginModuleFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, loginmodulefragment).commit();
    }
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        emailid = LoginModuleFragment.loginview.findViewById(R.id.login_emailid);
        outState.putString("emailid", emailid.getText().toString());
        pass =  LoginModuleFragment.loginview.findViewById(R.id.login_password);
        outState.putString("password", pass.getText().toString());
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            Log.d("emailid",emailid.getText().toString());
            Log.d("password+",pass.getText().toString());
            emailid.setText(savedInstanceState.getString("emailid"));
            pass.setText(savedInstanceState.getString("password"));
            Log.d("Received ",savedInstanceState.getString("emailid"));

        }
    }
}
