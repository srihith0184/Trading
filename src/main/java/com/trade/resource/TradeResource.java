package com.trade.resource;

import java.io.IOException;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trade.api.DataPoints;
import com.trade.api.StockSuggestData;
import com.trade.api.TradeSymbol;
import com.trade.service.BankNiftyService;
import com.trade.service.StockService;
import com.trade.service.TradeService;


@Controller
@RequestMapping("/trading")
public class TradeResource {
	
	@Autowired
	private TradeService tradeService;
	
	@Autowired
	private BankNiftyService bankNiftyService;
	
	@Autowired
	private StockService stockService;
	
	static final int TIMEOUT = 5000;
	
	@GetMapping("/nifty/dates")
	@ResponseBody
	public String getTradeData()
	{
		return tradeService.getNSEData();
	}

	@GetMapping("/nifty")
	@ResponseBody
	public DataPoints getTradeOptionData()
	{
		
		String nifty = "https://www.investing.com/indices/s-p-cnx-nifty-technical";
		return tradeService.getNiftyAndBankNiftySupportResistance(nifty);
	}
	
   @GetMapping("/banknifty")
   @ResponseBody
   public DataPoints getBankNiftyOptions()
   {
	   return bankNiftyService.getBankNiftySupportResistance();
   }
	
	@GetMapping("/stock/ressup")
	@ResponseBody
	public DataPoints getResistanceAndSupport(@QueryParam("tradeSymbol") TradeSymbol tradeSymbol)
	{		
		return stockService.getStockSupportResistance(tradeSymbol);
	}
	
	@GetMapping("/topgainers")
	@ResponseBody
	public StockSuggestData getTradingSuggestions() throws IOException
	{		
		return stockService.getTradeSuggestions();
	}
	
	
	
	
}
