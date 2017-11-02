package com.qmetric.model.pricingmodels;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import com.qmetric.actors.Supplier;
import com.qmetric.goods.StockUnitsOfMeasure;

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
		return price.setScale(0, RoundingMode.HALF_UP);
	}

	public Supplier getSupplier() {
		return supplier;
	}

	@Override
	public Calendar getDateInForce() {
		return dateInForce;
	}

}
