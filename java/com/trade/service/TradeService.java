package com.trade.service;

import java.util.List;

import com.trade.api.DataPoints;
import com.trade.api.ParentBankNiftyFuture;

public interface TradeService {
	
	public List<String> getNSEData();
	public List<ParentBankNiftyFuture> getNiftyFutureOIReader();
	public String testNSE();
	
	public DataPoints getNiftyAndBankNiftySupportResistance(String url);
}
