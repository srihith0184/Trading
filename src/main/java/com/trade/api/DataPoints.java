package com.trade.api;

import java.util.List;

public class DataPoints {

	private String ltpDataPointcordinate;
	private String ltp;
	private String symbol;
	
	List<ResSupport> resSupport;
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getLtp() {
		return ltp;
	}
	public void setLtp(String ltp) {
		this.ltp = ltp;
	}
	
	
	
	public List<ResSupport> getResSupport() {
		return resSupport;
	}
	public void setResSupport(List<ResSupport> resSupport) {
		this.resSupport = resSupport;
	}
	public String getLtpDataPointcordinate() {
		return ltpDataPointcordinate;
	}
	public void setLtpDataPointcordinate(String ltpDataPointcordinate) {
		this.ltpDataPointcordinate = ltpDataPointcordinate;
	}
	
	
	
}
