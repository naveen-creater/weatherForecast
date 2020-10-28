package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button next;
    private ArrayList<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        next = findViewById(R.id.next);

        names.add("One");names.add("Two");names.add("Three");names.add("Four");names.add("Five");
    }

    @Override
    public void onClick(View view) {
        System.out.println("print onclick");
        switch (view.getId()) {
            case R.id.one:
                changeUI();
                changeListOrder();
                break;

            case R.id.two:
                changeUI();
                changeListOrder();
                break;

            case R.id.three:
                changeUI();
                changeListOrder();
                break;

            case R.id.four:
                changeUI();
                changeListOrder();
                break;

            case R.id.five:
                changeUI();
                changeListOrder();
                break;

            case R.id.next:
                startActivity(new Intent(TestActivity.this, Test2.class));
                break;
        }
    }

    private void changeListOrder() {
//        clear the List
        names.clear();

        names.add(one.getText().toString());
        names.add(two.getText().toString());
        names.add(three.getText().toString());
        names.add(four.getText().toString());
        names.add(five.getText().toString());
    }

    private void changeUI() {
        one.setText(names.get(4));
        two.setText(names.get(0));
        three.setText(names.get(1));
        four.setText(names.get(2));
        five.setText(names.get(3));
    }
}
