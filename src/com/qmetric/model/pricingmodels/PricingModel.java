/**
 * 
 */
package com.qmetric.model.pricingmodels;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.qmetric.goods.StockUnitsOfMeasure;

/**
 * @author Richard Livingstone
 *
 */
public interface PricingModel extends Serializable {
	/**
	 * @return
	 */
	StockUnitsOfMeasure getUnits();

	/**
	 * @return
	 */
	Currency getCurrency();

	/**
	 * @return 
	 */
	BigDecimal getPriceInCents();// avoid nastiness with fractional
										// conversions by using BigDecimal

	/**
	 * @return
	 */
	Calendar getDateInForce(); // don't forget to observe timezones here
										// and especially changes from BST etc
										// as it will affect pricing
}
