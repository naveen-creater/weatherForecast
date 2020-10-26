package com.example.weatherforecast.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.weatherforecast.DBHelper.DatabaseHelper;
import com.example.weatherforecast.Modelclass.Userdata;
import com.example.weatherforecast.R;

public class SignUpModuleFragment extends Fragment {
    private static View signupview;
    private static FragmentManager registerfragmentManager;
    private static EditText fullName, emailId, mobileNumber, age, password, confirmPassword;
    private static Button registerbutton,back;

    public SignUpModuleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        signupview = inflater.inflate(R.layout.signupmodulefragment, container, false);
        registerfragmentManager = getActivity().getSupportFragmentManager();
        getUserEnteredDetails();
        Log.d("fullName",fullName.getText().toString());
        registerbutton = (Button) signupview.findViewById(R.id.signUpBtn);
        back = signupview.findViewById(R.id.back);

        registerbutton.setOnClickListener(v -> {

            boolean areFieldsValid = validatefields();
            if(areFieldsValid) {
                Toast.makeText(getActivity(), "Successfully registered!!!", Toast.LENGTH_LONG).show();

                Userdata userdata = new Userdata();
                userdata.setFullName(fullName.getText().toString());
                userdata.setEmail(emailId.getText().toString());
                userdata.setAge(Integer.parseInt(age.getText().toString()));
                userdata.setMobileNo(mobileNumber.getText().toString());
                userdata.setPassword(password.getText().toString());

                Log.d("userdata.getFullname", userdata.getFullName());
                Log.d("userdata.getEmailid", userdata.getEmail());
                Log.d("userdata.getAge", String.valueOf(userdata.getAge()));
                Log.d("userdata.getMobile", userdata.getMobileNo());
                Log.d("userdata.getPassword", userdata.getPassword());

                new DatabaseHelper(getActivity()).insertData(userdata);

                registerfragmentManager.beginTransaction().replace(R.id.fragment_container, new LoginModuleFragment(), "LoginModuleFragment").commit();
            }

        });

        back.setOnClickListener(view -> registerfragmentManager.beginTransaction().replace(R.id.fragment_container, new LoginModuleFragment(), "LoginModuleFragment").commit());

        return signupview;
    }


    public void getUserEnteredDetails(){

        fullName =  signupview.findViewById(R.id.fullName);
        emailId =  signupview.findViewById(R.id.userEmailId);
        mobileNumber = signupview.findViewById(R.id.mobileNumber);
        age =  signupview.findViewById(R.id.age);
        password =  signupview.findViewById(R.id.password);
        confirmPassword =  signupview.findViewById(R.id.confirmPassword);

    }

    // Check Validation Method
    private boolean validatefields() {

        // Get all edittext texts
        String getFullName = fullName.getText().toString();
        String getEmailId = emailId.getText().toString();
        String getMobileNumber = mobileNumber.getText().toString();
        String getAge = age.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();

        // Check if all strings are null or not
        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getAge.equals("") || getAge.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0) {

            Toast.makeText(getActivity(), "All fields are required.", Toast.LENGTH_LONG).show();
            return false;
        }
        // Check if both password should be equal
        else if (!getConfirmPassword.equals(getPassword)) {
            Toast.makeText(getActivity(), "Passwords don't match!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;

    }
}
