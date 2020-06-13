package com.trade.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trade.api.DataPoints;
import com.trade.api.ParentBankNiftyFuture;
import com.trade.service.BankNiftyService;
import com.trade.service.StockService;
import com.trade.service.TradeService;
import javax.ws.rs.QueryParam;

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
	public List<ParentBankNiftyFuture> getTradeData()
	{
		return tradeService.getNiftyFutureOIReader();
	}

	@GetMapping("/nifty/options")
	@ResponseBody
	public DataPoints getTradeOptionData()
	{
		String nifty = "https://www.investing.com/indices/s-p-cnx-nifty-technical";
		return tradeService.getNiftyAndBankNiftySupportResistance(nifty);
	}
	
	@GetMapping("/banknifty/options")
	@ResponseBody
	public DataPoints getTradeBankNiftyOptionData()
	{		
		return bankNiftyService.getBankNiftySupportResistance();
	}
	
	@GetMapping("/stock/ressup")
	@ResponseBody
	public DataPoints getResistanceAndSupport(@QueryParam("stockName") String stockName)
	{		
		return stockService.getStockSupportResistance(stockName);
	}
	
	
	
}