package com.trade.service;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.trade.api.ParentMostActiveCallPutAll;
import com.trade.api.DataPoints;
import com.trade.api.ParentBankNiftyFuture;

public interface TradeService {
	
	public String getNSEData();
	public List<ParentBankNiftyFuture> getNiftyFutureOIReader();
	public String testNSE();
	
	public DataPoints getNiftyAndBankNiftySupportResistance(String url);
	
	public ParentMostActiveCallPutAll getCallOptions();
	
	public ParentMostActiveCallPutAll getPutOptions() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException;
}
