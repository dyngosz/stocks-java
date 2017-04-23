package com.spring.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.domain.Stock;

@Service
public class StockServiceImpl implements StockService{

	@Value("${app.key}")
	private String AppKey;
	private final JsonStockDataParser parser = new JsonStockDataParser();

	public Stock getStockData(String stockID) throws IOException, ParseException {
		return getStockDataFromJson(getJsonFromApi(stockID));
	}

	private Stock getStockDataFromJson(String json) throws ParseException, JsonProcessingException, IOException {
		parser.setJsonForParsing(json);
		return parser.getStockData();
	}

	private String getJsonFromApi(String stockID) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		URL url = new URL(
				"http://www.alphavantage.co/query?apikey=" + AppKey + 
				"&function=TIME_SERIES_DAILY&symbol=" + stockID + "&outputsize=compact");
		URLConnection urlConnection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String inputLine;
		System.out.println(url);
		while ((inputLine = in.readLine()) != null)
			stringBuilder.append(inputLine);
		in.close();
		return stringBuilder.toString();
	}
}
