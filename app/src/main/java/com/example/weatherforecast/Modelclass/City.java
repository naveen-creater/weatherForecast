package com.example.weatherforecast.Modelclass;

import com.google.gson.annotations.SerializedName;

public class City{

	@SerializedName("country")
	private String country;

	@SerializedName("coord")
	private Coord coord;

	@SerializedName("timezone")
	private int timezone;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("population")
	private int population;

	public String getCountry(){
		return country;
	}

	public Coord getCoord(){
		return coord;
	}

	public int getTimezone(){
		return timezone;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public int getPopulation(){
		return population;
	}
}