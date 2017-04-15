package com.spring.web;

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.Stock;
import com.spring.service.StockService;

@RestController
public class StockController {
	private final StockService stockService;
	
	@Autowired
	public StockController(StockService stockService){
		this.stockService = stockService;
	}
	
	@RequestMapping(value = "/stock")
	public Stock stockData() throws IOException, ParseException, JSONException, org.json.simple.parser.ParseException{
		return stockService.getStockData("FB");
	}
}
