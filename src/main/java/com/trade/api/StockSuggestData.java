package com.trade.api;

import java.util.List;

public class StockSuggestData {
	
	List<BuyingShares> buyingShares;
	List<SellingShares> sellingShares;
	
	public List<BuyingShares> getBuyingShares() {
		return buyingShares;
	}
	public void setBuyingShares(List<BuyingShares> buyingShares) {
		this.buyingShares = buyingShares;
	}
	public List<SellingShares> getSellingShares() {
		return sellingShares;
	}
	public void setSellingShares(List<SellingShares> sellingShares) {
		this.sellingShares = sellingShares;
	}

	
	
}
