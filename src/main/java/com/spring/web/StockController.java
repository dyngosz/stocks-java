package com.spring.web;

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.Stock;
import com.spring.service.StockService;

@RestController
@RequestMapping(value = "/stock")
public class StockController {
	private final StockService stockService;
	
	@Autowired
	public StockController(StockService stockService){
		this.stockService = stockService;
	}
	
	@RequestMapping(params = {"stockID"}, method = RequestMethod.GET)
	public Stock stockData(@RequestParam(value = "stockID") String stockID) throws IOException, ParseException, JSONException, org.json.simple.parser.ParseException{
		return stockService.getStockData(stockID);
	}
}
