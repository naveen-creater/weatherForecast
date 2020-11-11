package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;

public class SplashActivity extends AppCompatActivity {
    private Animation animation;
    private ImageView movingImg;
    private Trace trace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        //Custom firebase moniterning trace implementation
        trace = FirebasePerformance.getInstance().newTrace("splash_trace");
        trace.start();

//      initview
        initView();


        animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.animation_moving);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
                trace.stop();
                System.out.println("sample hit");
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        movingImg.setAnimation(animation);

    }
    private void initView() {
        movingImg = findViewById(R.id.movingImg);
    }
}
