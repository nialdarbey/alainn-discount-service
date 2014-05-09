package com.alainn.soa.rules.discount;

import java.util.ArrayList;
import java.util.List;


public class OrderSummary {

	private int loyaltyPoints;
	
	private List<OrderItemSummary> orderItemSummaries = new ArrayList<OrderItemSummary>();
	
	private double totalValue;
	
	private double discountPercentage = 0;
	
	private int newLoyaltyPoints;

	public int getNewLoyaltyPoints() {
		return newLoyaltyPoints;
	}

	public void setNewLoyaltyPoints(int newLoyaltyPoints) {
		this.newLoyaltyPoints = newLoyaltyPoints;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discount) {
		this.discountPercentage = discount;
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

	public List<OrderItemSummary> getOrderItemSummaries() {
		return orderItemSummaries;
	}

	public void setOrderItemSummaries(List<OrderItemSummary> orderItemSummaries) {
		this.orderItemSummaries = orderItemSummaries;
	}
}
