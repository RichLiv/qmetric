package com.qmetric.model.pricingmodels;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import com.qmetric.goods.StockUnitsOfMeasure;

/**
 * @author Richard Livingstone
 *
 */
public class SimplePricingModel implements PricingModel {

	private StockUnitsOfMeasure units;
	private Currency curr;
	private BigDecimal price;
	private Calendar dateInForce;
	private Calendar expiryDate;

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

	/* (non-Javadoc)
	 * @see com.qmetric.model.pricingmodels.PricingModel#getDateInForce()
	 */
	@Override
	public Calendar getDateInForce() {
		return dateInForce;
	}

	/**
	 * @return
	 */
	public Calendar getExpiryDate() {
		return expiryDate;
	}
}
