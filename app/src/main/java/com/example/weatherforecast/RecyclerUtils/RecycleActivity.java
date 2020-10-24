package com.example.weatherforecast.RecyclerUtils;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.example.weatherforecast.Modelclass.ListItem;
import com.example.weatherforecast.Modelclass.VolleyRes;
import com.example.weatherforecast.R;
import com.example.weatherforecast.VolleyUtils.GsonRequest;
import com.example.weatherforecast.VolleyUtils.SingletonRequestQueue;

import java.util.HashMap;
import java.util.List;

import static com.example.weatherforecast.MainActivity.BASE_URL;

public class RecycleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;
    private TextView title;
    private Response.ErrorListener errorListener;
    private RequestQueue queue;
    private HashMap<String, String> headers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        title = findViewById(R.id.title_recycle);
        errorListener = error -> {
            if (error instanceof NetworkError) {
                Toast.makeText(RecycleActivity.this.getApplicationContext(), "No network available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(RecycleActivity.this.getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        };

        //        Get instance of Queue
        queue = SingletonRequestQueue.getInstance(this).getRequestQueue();

//          Set Headers for the api resquset
        headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        headers.put("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com");
        headers.put("x-rapidapi-key", "9337a5c37bmsh59403bdfdb4654ap191703jsn099f9b2707e2");

        Bundle bundle = getIntent().getExtras();
        title.setText(bundle.getString("city") + " 10 Days Forecas");
        SetRecyclerview(bundle.getString("city"));
    }

    private void SetRecyclerview(String place) {
        String url = BASE_URL + "daily?q=" + place + "&cnt=13&units=metric%20or%20imperial";
        GsonRequest<VolleyRes> gsonRequest = new GsonRequest<>(url, VolleyRes.class, headers, response -> {
            List<ListItem> listItems = response.getList();
            recycleAdapter = new RecycleAdapter(RecycleActivity.this, listItems);
            recyclerView.setAdapter(recycleAdapter);
        }, errorListener);
        queue.add(gsonRequest);
    }


}
