package com.spring.domain;

import java.util.List;
import java.util.Map;

public class Stock {
	private String symbol;
	private String information;
	private String lastRefreshed;
	private String[] dates;
	private String[] stockValues;
	private Map<String, Map<String, Double>> stockValuesMap;
	
	public String[] getDates() {
		return dates;
	}
	public void setDates(String[] dates) {
		this.dates = dates;
	}
	public String[] getStockValues() {
		return stockValues;
	}
	public void setStockValues(String[] stockValues) {
		this.stockValues = stockValues;
	}
	public Map<String, Map<String, Double>> getStockValuesMap() {
		return stockValuesMap;
	}
	public void setStockValuesMap(Map<String, Map<String, Double>> stockValuesMap) {
		this.stockValuesMap = stockValuesMap;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getLastRefreshed() {
		return lastRefreshed;
	}
	public void setLastRefreshed(String lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}
}
