package com.spring.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
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
		JSONObject timeSeriesDataArray = (JSONObject) mainJsonObj.get("Time Series (Daily)");

		//Setting main information 
		stock.setInformation(String.valueOf(metaDataArray.get("1. Information")));
		stock.setSymbol(String.valueOf(metaDataArray.get("2. Symbol")));
		stock.setLastRefreshed(String.valueOf(metaDataArray.get("3. Last Refreshed")));

			
		String JSON2 = mainJsonObj.toString();
		System.out.println(JSON2);
		final Gson gson = new Gson();
		final List<String> stockValues = gson.fromJson(JSON2, JsonElement.class)
		        .getAsJsonObject()
		        .get("Time Series (Daily)") // get the divisions property
		        .getAsJsonObject()
		        .entrySet() // and traverse its key/value pairs
		        .stream()
		        .map(Entry::getValue) // discarding the keys
		        .map(JsonElement::getAsJsonObject)
		        .map(jo -> jo.get("1. open")) // take the id property from the every `division` object
		        .map(JsonElement::getAsJsonPrimitive)
		        .map(JsonPrimitive::getAsString)
		        .collect(Collectors.toList());
		System.out.println(stockValues);
		System.out.println(stockValues.size());
		
		//100 stock values
		stock.setStockValues(stockValues);
		
		
		return stock;
	}

	private String getJsonIntegerObjectDescription(JSONObject jsonObject, String valueName) {
		int jsonValue = new Integer(jsonObject.get(valueName).toString());
		return String.valueOf(jsonValue);

	}
}
