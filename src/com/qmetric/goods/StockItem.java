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
public interface StockItem {
	public CostPricingModel getCostOfSupply();
	public SimplePricingModel getPriceAtTill();
}
