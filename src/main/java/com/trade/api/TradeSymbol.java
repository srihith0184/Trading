package com.trade.api;

public enum TradeSymbol {

	TCS("tata-consultancy-services"),
	MARUTI("maruti-suzuki-india"),
	BHARTIARTL("bharti-airtel"),
	WIPRO("wipro-ltd"),
	BRITANNIA("britannia-industries"),
	AXISBANK("axis-bank"),
	UPL("united-phosphorus"),
	INDUSINDBK("indusind-bank"),
	BPCL("bharat-petroleum"),
	BAJFINANCE("bajaj-finance"),
	ZEEL("zee-entertainment-enterprises"),
	ULTRACEMCO("ultratech-cement"),
	TITAN("titan-industries"),
	JSWSTEEL("jsw-steel"),
	EICHERMOT("eicher-motors"),
	TATAMOTORS("tata-motors-ltd"),
	CIPLA("cipla"),
	INFY("infosys"),
	ADANIPORTS("mundra-port-special-eco.-zone"),
	TECHM("tech-mahindra"),
	SUNPHARMA("sun-pharma-advanced-research"),
	HINDUNILVR("hindustan-unilever"),
	SBIN("state-bank-of-india"),
	ONGC("oil---natural-gas-corporation"),
	ICICIBANK("icici-bank-ltd"),
	TATASTEEL("tata-steel"),
	RELIANCE("reliance-industries"),
	DRREDDY("dr-reddys-laboratories"),
	IOC("indian-oil-corporation"),
	NESTLEIND("nestle"),
	HCLTECH("hcl-technologies"),
	BAJAJAUTO("bajaj-auto"),
	GRASIM("grasim-industries"),
	LT("larsen---toubro"),
	HDFCBANK("hdfc-bank-ltd"),
	COALINDIA("coal-india"),
	GAIL("gail-(india)"),
	NTPC("ntpc"),
	HEROMOTOCO("hero-motocorp"),
	HDFC("housing-development-finance"),
	VEDL("sesa-goa"),
	ASIANPAINT("asian-paints"),
	HINDALCO("hindalco-industries"),
	BAJAJFINSV("bajaj-finserv-limited"),
	SHREECEM("shree-cements"),
	MM("mahindra---mahindra"),
	POWERGRID("power-grid-corp.-of-india"),
	ITC("itc"),
	KOTAKBANK("kotak-mahindra-bank"),
	INFRATEL("bharti-infratel-ltd");

	private String symbol;
	
	TradeSymbol(String symbol) {
		this.symbol=symbol;
	}
	
	public String getByCode()
	{
		return symbol;
	}
	
}
