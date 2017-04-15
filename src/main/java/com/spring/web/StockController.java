package com.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Stock stockData(){
		return stockService.getStockData("FB");
	}
}
