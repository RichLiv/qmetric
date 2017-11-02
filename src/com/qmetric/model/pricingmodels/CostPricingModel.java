package com.qmetric.model.pricingmodels;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import com.qmetric.actors.Supplier;
import com.qmetric.goods.StockUnitsOfMeasure;

/**
 * Models the cost of an item from a supplier
 * 
 * @author Richard Livingstone
 *
 */
public class CostPricingModel implements PricingModel {

	private Supplier supplier;
	private StockUnitsOfMeasure units;
	private Currency curr;
	private BigDecimal price;
	private Calendar dateInForce;
	
	public CostPricingModel() {}

	/* (non-Javadoc)
	 * @see com.qmetric.model.pricingmodels.PricingModel#getUnits()
	 */
	@Override
	public StockUnitsOfMeasure getUnits() {
		return units;
	}

	/* (non-Javadoc)
	 * @see com.qmetric.model.pricingmodels.PricingModel#getCurrency()
	 */
	@Override
	public Currency getCurrency() {
		return curr;
	}

	/* (non-Javadoc)
	 * @see com.qmetric.model.pricingmodels.PricingModel#getPriceInCents()
	 */
	@Override
	public BigDecimal getPriceInCents() {
		return price.setScale(0, RoundingMode.HALF_UP);
	}

	/**
	 * @return
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/* (non-Javadoc)
	 * @see com.qmetric.model.pricingmodels.PricingModel#getDateInForce()
	 */
	@Override
	public Calendar getDateInForce() {
		return dateInForce;
	}

}
