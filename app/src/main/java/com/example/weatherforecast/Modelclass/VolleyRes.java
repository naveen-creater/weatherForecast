package com.example.weatherforecast.Modelclass;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VolleyRes{

	@SerializedName("city")
	private City city;

	@SerializedName("cnt")
	private int cnt;

	@SerializedName("cod")
	private String cod;

	@SerializedName("message")
	private double message;

	@SerializedName("list")
	private List<ListItem> list;

	public City getCity(){
		return city;
	}

	public int getCnt(){
		return cnt;
	}

	public String getCod(){
		return cod;
	}

	public double getMessage(){
		return message;
	}

	public List<ListItem> getList(){
		return list;
	}
}