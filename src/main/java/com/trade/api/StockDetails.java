package com.trade.api;

import java.util.List;

public class StockDetails {
	
	List<StockData> data;
	String time;
	
	public List<StockData> getData() {
		return data;
	}
	public void setData(List<StockData> data) {
		this.data = data;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

}
