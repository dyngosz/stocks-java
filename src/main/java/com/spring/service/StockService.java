package com.spring.service;

import java.io.IOException;
import org.json.simple.parser.ParseException;

import com.spring.domain.Stock;

public interface StockService {
	Stock getStockData(String stockID) throws IOException, ParseException;
}
