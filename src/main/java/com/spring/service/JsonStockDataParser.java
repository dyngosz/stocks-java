package com.spring.service;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.spring.domain.Stock;

public class JsonStockDataParser {

	private String jsonForParsing;
	private final JSONParser parser = new JSONParser();

	public void setJsonForParsing(String jsonForParsing) {
		this.jsonForParsing = jsonForParsing;
	}

	public Stock getStockData() throws ParseException {
		Stock stock = new Stock();
		Object obj = parser.parse(jsonForParsing);
		
		JSONObject mainJsonObj = (JSONObject) obj;
		JSONObject metaDataArray = (JSONObject) mainJsonObj.get("Meta Data");
		
//		JSONArray timeSeriesArray = (JSONArray) mainJsonObj.get("Time Series (Daily)");
//		Iterator<JSONObject> i = timeSeriesArray.iterator();
		
		stock.setInformation(String.valueOf(metaDataArray.get("1. Information")));
		stock.setSymbol(String.valueOf(metaDataArray.get("2. Symbol")));
		stock.setLastRefreshed(String.valueOf(metaDataArray.get("3. Last Refreshed")));
		
		return stock;
	}
	
	private String getJsonIntegerObjectDescription(JSONObject jsonObject, String valueName) {
		int jsonValue = new Integer(jsonObject.get(valueName).toString());
		return String.valueOf(jsonValue);
	}

}
