package com.trade.service;

import java.io.IOException;

import com.trade.api.DataPoints;
import com.trade.api.StockSuggestData;
import com.trade.api.TradeSymbol;

public interface StockService {
	
	public DataPoints getStockSupportResistance(TradeSymbol tradeSymbol);
	public StockSuggestData getTradeSuggestions() throws IOException ;

}
