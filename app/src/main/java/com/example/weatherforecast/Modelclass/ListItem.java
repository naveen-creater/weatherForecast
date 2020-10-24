package com.example.weatherforecast.Modelclass;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("dt")
	private int dt;

	@SerializedName("pop")
	private double pop;

	@SerializedName("sunrise")
	private int sunrise;

	@SerializedName("temp")
	private Temp temp;

	@SerializedName("sunset")
	private int sunset;

	@SerializedName("deg")
	private int deg;

	@SerializedName("weather")
	private List<WeatherItem> weather;

	@SerializedName("humidity")
	private int humidity;

	@SerializedName("pressure")
	private int pressure;

	@SerializedName("clouds")
	private int clouds;

	@SerializedName("feels_like")
	private FeelsLike feelsLike;

	@SerializedName("speed")
	private double speed;

	@SerializedName("rain")
	private double rain;

	public int getDt(){
		return dt;
	}

	public double getPop(){
		return pop;
	}

	public int getSunrise(){
		return sunrise;
	}

	public Temp getTemp(){
		return temp;
	}

	public int getSunset(){
		return sunset;
	}

	public int getDeg(){
		return deg;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public int getHumidity(){
		return humidity;
	}

	public int getPressure(){
		return pressure;
	}

	public int getClouds(){
		return clouds;
	}

	public FeelsLike getFeelsLike(){
		return feelsLike;
	}

	public double getSpeed(){
		return speed;
	}

	public double getRain(){
		return rain;
	}
}