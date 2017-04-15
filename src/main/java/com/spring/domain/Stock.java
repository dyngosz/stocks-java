package com.spring.domain;

public class Stock {
	private String symbol;
	private String information;
	private String lastRefreshed;
	private String stockValue;
	private String stockVolume;
	private String stockDate;
	
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
	public String getStockValue() {
		return stockValue;
	}
	public void setStockValue(String stockValue) {
		this.stockValue = stockValue;
	}
	public String getStockVolume() {
		return stockVolume;
	}
	public void setStockVolume(String stockVolume) {
		this.stockVolume = stockVolume;
	}
	public String getStockDate() {
		return stockDate;
	}
	public void setStockDate(String stockDate) {
		this.stockDate = stockDate;
	}
}
