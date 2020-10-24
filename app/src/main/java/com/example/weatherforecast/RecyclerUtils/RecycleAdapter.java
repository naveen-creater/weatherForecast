package com.example.weatherforecast.RecyclerUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforecast.Modelclass.ListItem;
import com.example.weatherforecast.Modelclass.WeatherItem;
import com.example.weatherforecast.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyviewHolder> {
    private List<ListItem> listItem;
    private Context context;

    public RecycleAdapter(Context context, List<ListItem> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        RecycleAdapter.MyviewHolder myviewHolder = new MyviewHolder(view);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        ListItem listItemz = listItem.get(position);
        WeatherItem weatherItem = listItemz.getWeather().get(0);
        int temp = (int) listItemz.getTemp().getDay() - 273;
        int speed = (int) (listItemz.getSpeed() * 3.6);
        double pre = (double) listItemz.getPressure() / 760;
        holder.description.setText(weatherItem.getDescription());
        holder.temptrature.setText(temp + " c");
        holder.windSpeed.setText(speed + " kmph");
        holder.pressure.setText(pre + " atm");
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private TextView temptrature;
        private TextView windSpeed;
        private TextView pressure;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.dayOfWeek);
            temptrature = itemView.findViewById(R.id.weatherType);
            windSpeed = itemView.findViewById(R.id.tempMax);
            pressure = itemView.findViewById(R.id.tempMin);

        }
    }
}
