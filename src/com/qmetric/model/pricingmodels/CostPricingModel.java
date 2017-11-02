package com.qmetric.model.pricingmodels;

import java.math.BigDecimal;
import java.util.Calendar;

import com.qmetric.actors.Supplier;
import com.qmetric.goods.StockUnitsOfMeasure;
import com.qmetric.model.pricingmodels.PricingModel;

public class CostPricingModel implements PricingModel {

	private Supplier supplier;
	private StockUnitsOfMeasure units;
	private Currency curr;
	private BigDecimal price;
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
	public BigDecimal getPriceInCents() {
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
