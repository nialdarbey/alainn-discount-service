package com.alainn.soa.rules.discount;

import java.util.ArrayList;
import java.util.List;


public class OrderSummary {

	private int loyaltyPoints;
	
	private List<OrderItemSummary> orderItemSummaries = new ArrayList<OrderItemSummary>();
	
	private double totalValue;
	
	private int numberOfItems;
	
	private double discount = 0;

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public double getTotalValue() {
		double value = 0;
		for (OrderItemSummary summary : this.orderItemSummaries) {
			value += (summary.getPrice() * summary.getQuantity());
		}
		return value;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public int getNumberOfItems() {
		return this.orderItemSummaries.size();
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public List<OrderItemSummary> getOrderItemSummaries() {
		return orderItemSummaries;
	}

	public void setOrderItemSummaries(List<OrderItemSummary> orderItemSummaries) {
		this.orderItemSummaries = orderItemSummaries;
	}
}
