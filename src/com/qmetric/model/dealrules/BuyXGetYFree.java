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
public class BuyXGetYFree implements DealRules {
	public Collection<StockItem> getRelatedItems() {
		return null; 
	};
	
	public int getTotalDealSaving(Currency requiredCurrency) {
		return 0;
	}
	
	public int getBaseCost(Currency requiredCurrency) {
		return 0;
	}
	@Override
	public int compareTo(Object o) {
		return 0; // we don't care about the order of application in a larger set for now
	}
}
