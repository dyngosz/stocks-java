package com.spring.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spring.domain.Stock;

@Service
public class StockServiceImpl {

	@Value("${app.key}")
	private String AppKey;
	private final JsonStockDataParser parser = new JsonStockDataParser();

	public Stock getStockData(String stockID) throws IOException {
		return getStockDataFromJson(getJsonFromApi(stockID));
	}

	private Stock getStockDataFromJson(String json) {
		parser.setJsonForParsing(json);
		return parser.getStockData();
	}

	private String getJsonFromApi(String stockID) throws IOException {
		String requestResult = "";
		URL url = new URL(
				"http://www.alphavantage.co/query?apikey=" + AppKey + "&function=TIME_SERIES_DAILY&symbol=" + stockID);
		URLConnection urlConnection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			requestResult += requestResult.concat(inputLine);
		in.close();
		return requestResult;
	}
}
