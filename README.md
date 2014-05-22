# Alainn Discount Service
Utility Service responsible for calculating Disount's for Customer Orders and direct Sales based on the Customer's loyalty points and the value of the Order / Sale.

# Operations

* **calculateDiscount**

  Receives Customer's Order or Sale and transforms it to an OrderSummary POJO (source is part of the solution) which it immediately passes to Drools for Rule Execution. The result returned by the Rules Engine is transformed into an appropriate Response for the client.

# Technical Points of Note

## Transformation
* **DataMapper** cannot handle the DOM returned by the SOAP Message Processor. We need to transform it to an Xml String
````xml
	<mulexml:dom-to-xml-transformer doc:name="DOM to XML"/>
````

## Rules Execution
* Message Processor refers to Drools file and an inital Fact which is a Spring Bean.
````xml
	<spring:beans>
        <spring:bean name="NoFactsBean" class="java.util.ArrayList"/>
    </spring:beans>
````
* Payload is passed as a POJO which contains the data to be used to match rules and fields which Drools will set to store the results of the execution.
````java
	public class OrderSummary {

		private int loyaltyPoints;
		
		private List<OrderItemSummary> orderItemSummaries = new ArrayList<OrderItemSummary>();
		
		private double totalValue;
		
		private double discountPercentage = 0;
		
		private int newLoyaltyPoints;

		...
	}
````

* Result is accessed using **#[payload.object]**
* **Rules** are fired by matching a Rule definition with an instance of OrderSummary whose fields (loyaltyPoints and totalValue) fall with certain ranges. The firing rule sets data on the same OrderSummary instance.
````java
	package com.alainn.soa.rules.discount;

	global org.mule.module.bpm.MessageService mule;

	dialect "mvel"

	rule "Gold"
	    lock-on-active
	when
	$orderSummary : OrderSummary( loyaltyPoints >= 1000 || totalValue >= 150 )
	then
		$orderSummary.setDiscountPercentage(0.12);
		$orderSummary.setNewLoyaltyPoints(10);
	end

	rule "Silver"
	    lock-on-active
	when
		$orderSummary : OrderSummary( loyaltyPoints >= 500 && < 1000, totalValue >= 100 )
	then
		$orderSummary.setDiscountPercentage(0.08);
		$orderSummary.setNewLoyaltyPoints(6);
	end

	rule "Bronze"
	    lock-on-active
	when
		$orderSummary : OrderSummary( loyaltyPoints >= 300 && < 500, totalValue >= 80  )
	then
		$orderSummary.setDiscountPercentage(0.05);
		$orderSummary.setNewLoyaltyPoints(3);
	end
````

# Contact
nial.darbey@mulesoft.com