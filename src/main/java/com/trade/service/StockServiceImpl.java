
package com.trade.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.trade.api.DataPoints;
import com.trade.api.ResSupport;
import com.trade.api.TradeSymbol;
import com.trade.api.Utils;

@Service
public class StockServiceImpl implements StockService{

	private String url="https://www.investing.com/equities/";
	
	@Override
	public DataPoints getStockSupportResistance(TradeSymbol tradeSymbol) {
		Document doc = null;
		DataPoints dataPoints=new DataPoints();
		HashMap<String,Float> map=new HashMap<String,Float>();
		try {
			url=url+tradeSymbol.getByCode()+"-technical";
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10*1000000).get();
				Element ltdElement = doc.getElementById("last_last");
				String ltp=ltdElement.html();
				ltp=ltp.replaceAll(",", "");
				Float ltp_float=Float.parseFloat(ltp);
				Element content = doc.getElementById("curr_table");
				Elements row =content.getAllElements();
				//System.out.println(row.text());
				String values=row.text();
				if(values!=null && values.length()>0) {
					int beginIndex=values.indexOf("Classic");
					int endIndex=values.indexOf("DeMark's");
					String substr=values.substring(beginIndex, endIndex);
					dataPoints.setLtp(ltp);
					
					List<ResSupport> resSupportList=new ArrayList<>();
					ResSupport resSupport=new ResSupport();
					
					resSupport.setName(substr.split("\\s+")[0]);
					resSupport.setS3(substr.split("\\s+")[1]);
					resSupport.setS2(substr.split("\\s+")[2]);
					resSupport.setS1(substr.split("\\s+")[3]);
					resSupport.setPivotPoints(substr.split("\\s+")[4]);
					resSupport.setR1(substr.split("\\s+")[5]);
					resSupport.setR2(substr.split("\\s+")[6]);
					resSupport.setR3(substr.split("\\s+")[7]);
					
					ResSupport resSupport1=new ResSupport();
					resSupport1.setName(substr.split("\\s+")[8]);
					resSupport1.setS3(substr.split("\\s+")[9]);
					resSupport1.setS2(substr.split("\\s+")[10]);
					resSupport1.setS1(substr.split("\\s+")[11]);
					resSupport1.setPivotPoints(substr.split("\\s+")[12]);
					resSupport1.setR1(substr.split("\\s+")[13]);
					resSupport1.setR2(substr.split("\\s+")[14]);
					resSupport1.setR3(substr.split("\\s+")[15]);
					
					ResSupport resSupport2=new ResSupport();
					resSupport2.setName(substr.split("\\s+")[16]);
					resSupport2.setS3(substr.split("\\s+")[17]);
					resSupport2.setS2(substr.split("\\s+")[18]);
					resSupport2.setS1(substr.split("\\s+")[19]);
					resSupport2.setPivotPoints(substr.split("\\s+")[20]);
					resSupport2.setR1(substr.split("\\s+")[21]);
					resSupport2.setR2(substr.split("\\s+")[22]);
					resSupport2.setR3(substr.split("\\s+")[23]);
					
					ResSupport resSupport3=new ResSupport();
					resSupport3.setName(substr.split("\\s+")[24]);
					resSupport3.setS3(substr.split("\\s+")[25]);
					resSupport3.setS2(substr.split("\\s+")[26]);
					resSupport3.setS1(substr.split("\\s+")[27]);
					resSupport3.setPivotPoints(substr.split("\\s+")[28]);
					resSupport3.setR1(substr.split("\\s+")[29]);
					resSupport3.setR2(substr.split("\\s+")[30]);
					resSupport3.setR3(substr.split("\\s+")[31]);
					
					resSupportList.add(resSupport);
					resSupportList.add(resSupport1);
					resSupportList.add(resSupport2);
					resSupportList.add(resSupport3);
					
					dataPoints.setResSupport(resSupportList);
					
					map.put("s1", Math.abs(Float.parseFloat(resSupport.getS1())-ltp_float));
					map.put("s2", Math.abs(Float.parseFloat(resSupport.getS2())-ltp_float));
					map.put("s3", Math.abs(Float.parseFloat(resSupport.getS3())-ltp_float));
					map.put("pivotPoints", Math.abs(Float.parseFloat(resSupport.getPivotPoints())-ltp_float));
					map.put("r1", Math.abs(Float.parseFloat(resSupport.getR1())-ltp_float));
					map.put("r2", Math.abs(Float.parseFloat(resSupport.getR2())-ltp_float));
					map.put("r3", Math.abs(Float.parseFloat(resSupport.getR3())-ltp_float));
					 Map<Object, Object> sortedMap= Utils.sortTwoStringKeyValueHashMapByValues(map);
					 Map.Entry<Object,Object> entry = sortedMap.entrySet().iterator().next();
					 String key = entry.getKey().toString();
					 dataPoints.setLtpDataPointcordinate(key);
					 //System.out.println("sorted" + sortedMap);
				}
				dataPoints.setSymbol(tradeSymbol.getByCode());
				url="";
			return dataPoints;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
