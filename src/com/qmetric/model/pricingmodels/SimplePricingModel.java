package com.qmetric.model.pricingmodels;

import java.util.Calendar;

import com.qmetric.goods.StockUnitsOfMeasure;
import com.qmetric.model.pricingmodels.PricingModel;

public class SimplePricingModel implements PricingModel {

	private StockUnitsOfMeasure units;
	private Currency curr;
	private int price;
	private Calendar dateInForce;
	private Calendar expiryDate;

	@Override
	public StockUnitsOfMeasure getUnits() {
		return units;
	}

	@Override
	public Currency getCurrency() {
		return curr;
	}

	@Override
	public int getPriceInCents() {
		return price;
	}

	@Override
	public Calendar getDateInForce() {
		return dateInForce;
	}

	public Calendar getExpiryDate() {
		return expiryDate;
	}
}