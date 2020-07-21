package com.trade.service;

import com.trade.api.DataPoints;
import com.trade.api.StockDetails;
import com.trade.api.TradeSymbol;

public interface StockService {
	
	public DataPoints getStockSupportResistance(TradeSymbol tradeSymbol);
	public StockDetails getTopGainers();

}
