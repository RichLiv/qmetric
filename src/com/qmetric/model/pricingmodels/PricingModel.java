/**
 * 
 */
package com.qmetric.model.pricingmodels;

import java.math.BigDecimal;
import java.util.Calendar;

import com.qmetric.goods.StockUnitsOfMeasure;

/**
 * @author Richard Livingstone
 *
 */
public interface PricingModel {
	public StockUnitsOfMeasure getUnits();
	public Currency getCurrency();
	public BigDecimal getPriceInCents();// avoid nastiness with fractional conversions by using BigDecimal
	public Calendar getDateInForce(); // don't forget to observe timezones here and especially changes from BST etc as it will affect pricing
}
