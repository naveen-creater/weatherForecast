package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.weatherforecast.Modelclass.ListItem;
import com.example.weatherforecast.Modelclass.Temp;
import com.example.weatherforecast.Modelclass.VolleyRes;
import com.example.weatherforecast.Modelclass.WeatherItem;
import com.example.weatherforecast.RecyclerUtils.RecycleActivity;
import com.example.weatherforecast.VolleyUtils.GsonRequest;
import com.example.weatherforecast.VolleyUtils.SingletonRequestQueue;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final static String BASE_URL = "https://rapidapi.p.rapidapi.com/forecast/";
    private TextView city;
    private TextView description;
    private TextView temptrature;
    private TextView windSpeed;
    private TextView pressure;
    private EditText cityName;
    private Button search;
    private Button forecastFind;
    private Response.ErrorListener errorListener;
    private RequestQueue queue;
    private HashMap<String, String> headers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initViews
        initView();
//        Button Listeners
        listeners();

    }

    private void listeners() {
        search.setOnClickListener(view -> updateUI());

        forecastFind.setOnClickListener(view -> {
            String name = cityName.getText().toString().trim();
            if (!name.isEmpty()) {
                callListData(name);
            } else
                cityName.setError("Enter city!");

        });
    }


    private void updateUI() {
        String name = cityName.getText().toString();
        if (!name.isEmpty()) {
            getCustomRequestVolley(name);
        } else
            cityName.setError("Enter city!");
    }

    private void initView() {
        city = findViewById(R.id.description);
        search = findViewById(R.id.search);
        forecastFind = findViewById(R.id.forcast);
        cityName = findViewById(R.id.editText);
        description = findViewById(R.id.dayOfWeek);
        temptrature = findViewById(R.id.weatherType);
        windSpeed = findViewById(R.id.tempMax);
        pressure = findViewById(R.id.tempMin);

        errorListener = error -> {
            if (error instanceof NetworkError) {
                Toast.makeText(MainActivity.this.getApplicationContext(), "No network available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this.getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        };

//        Get instance of Queue
        queue = SingletonRequestQueue.getInstance(this).getRequestQueue();
//          Set Headers for the api resquset
        headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        headers.put("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com");
        headers.put("x-rapidapi-key", "9337a5c37bmsh59403bdfdb4654ap191703jsn099f9b2707e2");

        //        from notification
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            if(bundle.containsKey("location"))
            {
                String place = bundle.getString("location");
                getCustomRequestVolley(place);
                cityName.setText(place);
            }
        }

    }

    private void getCustomRequestVolley(String place) {
        String url = BASE_URL + "daily?q=" + place + "&cnt=5&units=metric%20or%20imperial";
//         Custom Request
        GsonRequest<VolleyRes> gsonRequest = new GsonRequest<>(url, VolleyRes.class, headers, response -> {
            ListItem listItems = response.getList().get(0);
            WeatherItem WeatherItem = listItems.getWeather().get(0);
//              maths Conversion
            int temp = (int) listItems.getTemp().getDay() - 273;
            int speed = (int) (listItems.getSpeed() * 3.6);
            float pre = (float) listItems.getPressure() / 760;

            city.setText(response.getCity().getName());
            description.setText(WeatherItem.getDescription());
            temptrature.setText(temp + " c");
            windSpeed.setText(speed + " kmph");
            pressure.setText(pre + " atm");
        }, errorListener);
//        add to Queue
        queue.add(gsonRequest);
    }

    private void callListData(String place) {
        Intent i = new Intent(MainActivity.this, RecycleActivity.class);
        i.putExtra("city", place);
        startActivity(i);
    }
}
