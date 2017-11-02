/**
 * 
 */
package com.qmetric.goods;

import java.io.Serializable;
import java.math.BigDecimal;

import com.qmetric.model.pricingmodels.CostPricingModel;
import com.qmetric.model.pricingmodels.SimplePricingModel;

/**
 * @author Richard Livingstone
 *
 */
public interface StockItem extends Serializable { 
	/**
	 * @return
	 */
	CostPricingModel getCostOfSupply();

	/**
	 * @return
	 */
	SimplePricingModel getPriceAtTill();

	/**
	 * @return
	 */
	String getName();

	/**
	 * @return
	 */
	String getReceiptLine();

	/**
	 * @return
	 */
	BigDecimal getQuantity(); // not always an int so to be safe, use
										// safe arithmetic classes
}
