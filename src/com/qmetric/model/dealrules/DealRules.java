/**
 * 
 */
package com.qmetric.model.dealrules;

import java.util.Collection;

import com.qmetric.goods.StockItem;
import com.qmetric.model.pricingmodels.Currency;

/**
 * @author Richard Livingstone
 *
 */
public interface DealRules {
	public Collection<StockItem> getRelatedItems();
	public int getTotalDealSaving(Currency requiredCurrency); // although for now we will stick with one
	public int getBaseCost(Currency requiredCurrency); // although for now we will stick with one
}
