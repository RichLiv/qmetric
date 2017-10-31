/**
 * 
 */
package com.qmetric.goods;

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
	public Double getQuantity(); // for many items, an integer, for weighedout items, could be a fraction
}
