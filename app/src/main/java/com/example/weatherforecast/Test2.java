package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Test2 extends AppCompatActivity implements View.OnClickListener {
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        initView();
    }

    private void initView() {
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);

        buttons.add(one);buttons.add(two);buttons.add(three);buttons.add(four);buttons.add(five);

        names.add("One");names.add("Two");names.add("Three");names.add("Four");names.add("Five");
    }

    @Override
    public void onClick(View view) {
        System.out.println("print onclick");
        switch (view.getId()) {
            case R.id.one:
                changeUI(0);
                break;

            case R.id.two:
                buttons.get(0).setText(names.get(4));
                changeUI(1);
                break;

            case R.id.three:
                buttons.get(0).setText(names.get(3));
                buttons.get(1).setText(names.get(4));
                changeUI(2);
                break;

            case R.id.four:
                for (int i = 0; i < 3; i++) {
                    buttons.get(i).setText(names.get(i + 2));
                }
                changeUI(3);
                break;

            case R.id.five:
                changeUI(4);
                break;

            case R.id.next:
                startActivity(new Intent(Test2.this, TestActivity.class));
                finish();
                break;
        }
    }

    private void changeUI(int place) {
        Button selectButton = buttons.get(place);
        selectButton.setText("One");


        for (int i = 0; i < 5; i++) {
            if (place == 0) {
                if (i != place) {
                    selectButton = buttons.get(i);
                    selectButton.setText(names.get(i));
                }
            } else if (place == 1) {
                if (i > place) {
                    selectButton = buttons.get(i);
                    selectButton.setText(names.get(i - 1));
                }
            } else if (place == 2) {
                if (i > place) {
                    selectButton = buttons.get(i);
                    selectButton.setText(names.get(i - 2));
                }
            } else if (place == 3) {
                if (i > place) {
                    selectButton = buttons.get(i);
                    selectButton.setText(names.get(i - 3));
                }
            } else {
                if (i != place) {
                    selectButton = buttons.get(i);
                    selectButton.setText(names.get(i + 1));
                }
            }
        }


    }

}
