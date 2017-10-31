package com.qmetric.model.pricingmodels;

import java.util.Calendar;

import com.qmetric.actors.Supplier;
import com.qmetric.goods.StockUnitsOfMeasure;
import com.qmetric.model.pricingmodels.PricingModel;

public class CostPricingModel implements PricingModel {

	private Supplier supplier;
	private StockUnitsOfMeasure units;
	private Currency curr;
	private int price;
	private Calendar dateInForce;

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
	
	public Supplier getSupplier() {
		return supplier;
	}

	@Override
	public Calendar getDateInForce() {
		return dateInForce;
	}

}
