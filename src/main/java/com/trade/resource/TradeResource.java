package com.trade.resource;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trade.api.DataPoints;
import com.trade.api.ParentMostActiveCallPutAll;
import com.trade.api.TradeSymbol;
import com.trade.service.BankNiftyService;
import com.trade.service.StockService;
import com.trade.service.TradeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/trading")
public class TradeResource {
	
	@Autowired
	private TradeService tradeService;
	
	@Autowired
	private BankNiftyService bankNiftyService;
	
	@Autowired
	private StockService stockService;
	
	@GetMapping("/nifty/dates")
	@ResponseBody
	public String getTradeData()
	{
		return tradeService.getNSEData();
	}

	@GetMapping("/nifty/options")
	@ResponseBody
	public DataPoints getTradeOptionData()
	{
		String nifty = "https://www.investing.com/indices/s-p-cnx-nifty-technical";
		return tradeService.getNiftyAndBankNiftySupportResistance(nifty);
	}
	
	@GetMapping("/nifty/calls")
	@ResponseBody
	public ParentMostActiveCallPutAll getCallOptionData()
	{
		return tradeService.getCallOptions();
	}
	
	@GetMapping("/nifty/puts")
	@ResponseBody
	public ParentMostActiveCallPutAll getPutOptionData() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException
	{
		return tradeService.getPutOptions();
	}
	
	@GetMapping("/banknifty/options")
	public DataPoints getTradeBankNiftyOptionData()
	{		
		return bankNiftyService.getBankNiftySupportResistance();
	}
	
	@GetMapping("/stock/ressup")
	@ResponseBody
	public DataPoints getResistanceAndSupport(@QueryParam("tradeSymbol") TradeSymbol tradeSymbol)
	{		
		return stockService.getStockSupportResistance(tradeSymbol);
	}
	
	
	
}
