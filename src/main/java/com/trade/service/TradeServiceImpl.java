package com.trade.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.trade.api.DataPoints;
import com.trade.api.ParentBankNiftyFuture;
import com.trade.api.ParentMostActiveCallPutAll;
import com.trade.api.ResSupport;
import com.trade.api.Utils;
import com.trade.provider.RestTemplateIssueResolver;


@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	RestTemplateIssueResolver restTemplateIssueResolver;

	@Override
	public String getNSEData() {
//		String url = "https://www1.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?symbolCode=-10006&symbol=NIFTY&symbol=NIFTY&instrument=-&date=-&segmentLink=17&symbolCount=2&segmentLink=17";
//		Document doc = null;
//		try {
//			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(15 * 1000).get();
//			Elements content = doc.getElementsByClass("goodTextBox");
//			if (content != null && content.size() > 1) {
//				List<String> list = content.eachText();
//				String value = list.get(1).replaceAll("Select", "").trim();
//				// System.out.println(value);
//				String[] values = value.split("\\s+");
//				return Arrays.asList(values);
//			} else {
//				List<String> list = new ArrayList<String>();
//				list.add("Error While Loading...");
//				return list;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			List<String> list = new ArrayList<String>();
//			list.add("Read time out while Loading...");
//			return list;
//		}

		StringBuffer sb = new StringBuffer();
		try {
			String httpsURL = "https://www1.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?symbolCode=-10006&symbol=NIFTY&symbol=NIFTY&instrument=-&date=-&segmentLink=17&symbolCount=2&segmentLink=17";
			URL myUrl = new URL(httpsURL);
			HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public List<ParentBankNiftyFuture> getNiftyFutureOIReader() {
		return null;
//		List<ParentBankNiftyFuture> parentBankNiftyFutureList = new ArrayList<>();
//		List<String> expiryMonthList = getNSEData();
//		for (String expiryMonth : expiryMonthList) {
//			String url = "https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteFO.jsp?underlying=NIFTY&instrument=FUTIDX&type=-&strike=-&expiry="
//					+ expiryMonth;
//			Document doc = null;
//			try {
//				doc = Jsoup.connect(url.replaceAll(" ", "%20"))
//						   .userAgent("Chrome Browser")
//                           .header("Accept", "text/html")
//                           .header("Accept-Encoding", "gzip,deflate")
//                           .header("Accept-Language", "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2")
//                           .header("Connection", "keep-alive")
//                           .timeout(10*10000)
//                           .ignoreContentType(true)
//                           .get();
//				Element content = doc.getElementById("responseDiv");
//				String jsonCont = content.html();
//				// System.out.println(jsonCont);
//				ParentBankNiftyFuture parentBankNiftyFuture = new Gson().fromJson(jsonCont,
//						ParentBankNiftyFuture.class);
//				parentBankNiftyFutureList.add(parentBankNiftyFuture);
//
//			} catch (IOException e) {
//				e.printStackTrace();
//				return null;
//			}
//		}
//		return parentBankNiftyFutureList;

	}

	@Override
	public DataPoints getNiftyAndBankNiftySupportResistance(String url) {
		Document doc = null;
		DataPoints dataPoints = new DataPoints();
		HashMap<String, Float> map = new HashMap<String, Float>();
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10 * 1000).get();
			Element ltdElement = doc.getElementById("last_last");
			String ltp = ltdElement.html();
			ltp = ltp.replaceAll(",", "");
			Float ltp_float = Float.parseFloat(ltp);
			Element content = doc.getElementById("curr_table");
			Elements row = content.getAllElements();
			// System.out.println(row.text());
			String values = row.text();
			if (values != null && values.length() > 0) {
				int beginIndex = values.indexOf("Classic");
				int endIndex = values.indexOf("DeMark's");
				String substr = values.substring(beginIndex, endIndex);
				dataPoints.setLtp(ltp);

				List<ResSupport> resSupportList = new ArrayList<>();
				ResSupport resSupport = new ResSupport();

				resSupport.setName(substr.split("\\s+")[0]);
				resSupport.setS3(substr.split("\\s+")[1]);
				resSupport.setS2(substr.split("\\s+")[2]);
				resSupport.setS1(substr.split("\\s+")[3]);
				resSupport.setPivotPoints(substr.split("\\s+")[4]);
				resSupport.setR1(substr.split("\\s+")[5]);
				resSupport.setR2(substr.split("\\s+")[6]);
				resSupport.setR3(substr.split("\\s+")[7]);

				ResSupport resSupport1 = new ResSupport();
				resSupport1.setName(substr.split("\\s+")[8]);
				resSupport1.setS3(substr.split("\\s+")[9]);
				resSupport1.setS2(substr.split("\\s+")[10]);
				resSupport1.setS1(substr.split("\\s+")[11]);
				resSupport1.setPivotPoints(substr.split("\\s+")[12]);
				resSupport1.setR1(substr.split("\\s+")[13]);
				resSupport1.setR2(substr.split("\\s+")[14]);
				resSupport1.setR3(substr.split("\\s+")[15]);

				ResSupport resSupport2 = new ResSupport();
				resSupport2.setName(substr.split("\\s+")[16]);
				resSupport2.setS3(substr.split("\\s+")[17]);
				resSupport2.setS2(substr.split("\\s+")[18]);
				resSupport2.setS1(substr.split("\\s+")[19]);
				resSupport2.setPivotPoints(substr.split("\\s+")[20]);
				resSupport2.setR1(substr.split("\\s+")[21]);
				resSupport2.setR2(substr.split("\\s+")[22]);
				resSupport2.setR3(substr.split("\\s+")[23]);

				ResSupport resSupport3 = new ResSupport();
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

				map.put("s1", Math.abs(Float.parseFloat(resSupport.getS1()) - ltp_float));
				map.put("s2", Math.abs(Float.parseFloat(resSupport.getS2()) - ltp_float));
				map.put("s3", Math.abs(Float.parseFloat(resSupport.getS3()) - ltp_float));
				map.put("pivotPoints", Math.abs(Float.parseFloat(resSupport.getPivotPoints()) - ltp_float));
				map.put("r1", Math.abs(Float.parseFloat(resSupport.getR1()) - ltp_float));
				map.put("r2", Math.abs(Float.parseFloat(resSupport.getR2()) - ltp_float));
				map.put("r3", Math.abs(Float.parseFloat(resSupport.getR3()) - ltp_float));
				Map<Object, Object> sortedMap = Utils.sortTwoStringKeyValueHashMapByValues(map);
				Map.Entry<Object, Object> entry = sortedMap.entrySet().iterator().next();
				String key = entry.getKey().toString();
				dataPoints.setLtpDataPointcordinate(key);
				// System.out.println("sorted" + sortedMap);
			}
			dataPoints.setSymbol("Nifty");
			return dataPoints;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String testNSE() {
		String url = "https://www1.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionDates.jsp?symbol=NIFTY&instrument=OPTIDX&strike=7400.00";
		Document doc = null;
		String jsonCont = null;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(20 * 1000).get();
			Element content = doc.getElementById("responseDiv");
			jsonCont = content.html();
			return jsonCont;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ParentMostActiveCallPutAll getCallOptions() {
		String path = "https://www1.nseindia.com/live_market/dynaContent/live_analysis/most_active/CallsNIFTYVolume.json";
		String result = restTemplateIssueResolver.CommonHttpDataFetcher(path);
		ParentMostActiveCallPutAll parentMostActiveCallPutAll = new Gson().fromJson(result.toString(),
				ParentMostActiveCallPutAll.class);

		return parentMostActiveCallPutAll;

	}

	@Override
	public ParentMostActiveCallPutAll getPutOptions() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
		String path = "https://www1.nseindia.com/live_market/dynaContent/live_analysis/most_active/PutsALLVolume.json";
	
		String result = restTemplateIssueResolver.commonHttpConnectionDataFetcher(path);
		ParentMostActiveCallPutAll parentMostActiveCallPutAll = new Gson().fromJson(result.toString(),
				ParentMostActiveCallPutAll.class);

		return parentMostActiveCallPutAll;
	}



}
