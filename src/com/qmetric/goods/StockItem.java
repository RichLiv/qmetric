/**
 * 
 */
package com.qmetric.goods;

import java.math.BigDecimal;

import com.qmetric.model.pricingmodels.CostPricingModel;
import com.qmetric.model.pricingmodels.SimplePricingModel;

/**
 * @author Richard Livingstone
 *
 */
public interface StockItem { // to get order in receipt
	public CostPricingModel getCostOfSupply();
	public SimplePricingModel getPriceAtTill();
	public String getName();
	public String getReceiptLine();
	public BigDecimal getQuantity(); // not always an int so to be safe, use safe arithmetic classes 
}
