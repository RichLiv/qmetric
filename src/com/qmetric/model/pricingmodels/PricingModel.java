/**
 * 
 */
package com.qmetric.model.pricingmodels;

import java.util.Calendar;

import com.qmetric.goods.StockUnitsOfMeasure;

/**
 * @author Richard Livingstone
 *
 */
public interface PricingModel {
	public StockUnitsOfMeasure getUnits();
	public Currency getCurrency();
	public int getPriceInCents();// avoid nastiness with fractional conversions up front
	public Calendar getDateInForce(); // don't forget to observe timezones here and especially changes from BST etc as it will affect pricing
}
