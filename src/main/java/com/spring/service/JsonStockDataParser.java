package com.spring.service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.domain.Stock;

public class JsonStockDataParser {

	private String jsonForParsing;
	private final JSONParser parser = new JSONParser();

	public void setJsonForParsing(String jsonForParsing) {
		this.jsonForParsing = jsonForParsing;
	}

	public Stock getStockData() throws ParseException, JsonProcessingException, IOException {
		Stock stock = new Stock();
		Object obj = parser.parse(jsonForParsing);

		JSONObject mainJsonObj = (JSONObject) obj;
		JSONObject metaDataArray = (JSONObject) mainJsonObj.get("Meta Data");
		JSONObject timeSeriesDataArray = (JSONObject) mainJsonObj.get("Time Series (Daily)");

		//Setting main information 
		stock.setInformation(String.valueOf(metaDataArray.get("1. Information")));
		stock.setSymbol(String.valueOf(metaDataArray.get("2. Symbol")));
		stock.setLastRefreshed(String.valueOf(metaDataArray.get("3. Last Refreshed")));
		
//		OLDER VERSION OF GETTING ONLY STOCK VALUES TABLE
//		String JSON2 = mainJsonObj.toString();
//		System.out.println(JSON2);
//		final Gson gson = new Gson();
//		final List<String> stockValues = gson.fromJson(JSON2, JsonElement.class)
//		        .getAsJsonObject()
//		        .get("Time Series (Daily)") // get the divisions property
//		        .getAsJsonObject()
//		        .entrySet() // and traverse its key/value pairs
//		        .stream()
//		        .map(Entry::getValue) // discarding the keys
//		        .map(JsonElement::getAsJsonObject)
//		        .map(jo -> jo.get("1. open")) // take the id property from the every `division` object
//		        .map(JsonElement::getAsJsonPrimitive)
//		        .map(JsonPrimitive::getAsString)
//		        .collect(Collectors.toList());
//		

		//Create map of dates and stock values
		Map<String, Map<String, Double>> map = new TreeMap<>(Collections.reverseOrder());
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonForParsing);
		JsonNode data = node.get("Time Series (Daily)");
		

		Iterator<String> iterator = data.fieldNames();
		while (iterator.hasNext()) {
			String date = iterator.next();
			JsonNode value = data.get(date);

			Map<String, Double> priceMap = new HashMap<>();
			Iterator<String> itr = value.fieldNames();
			while (itr.hasNext()) {
				String param = itr.next();
				JsonNode price = value.get(param);

				priceMap.put(param.replaceAll(" ", "").split("\\.")[1], price.asDouble());
			}
			map.put(date, priceMap);
		}
		stock.setStockValuesMap(map);
		
		return stock;
	}

	private String getJsonIntegerObjectDescription(JSONObject jsonObject, String valueName) {
		int jsonValue = new Integer(jsonObject.get(valueName).toString());
		return String.valueOf(jsonValue);

	}
}
