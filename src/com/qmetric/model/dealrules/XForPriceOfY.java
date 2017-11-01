/**
 * 
 */
package com.qmetric.model.dealrules;

import java.math.BigDecimal;
import java.util.Collection;

import com.qmetric.goods.StockItem;
import com.qmetric.model.pricingmodels.Currency;

/**
 * @author Richard Livingstone
 *
 */
public abstract class XForPriceOfY extends UniqueDealRules {
	public Collection<StockItem> getRelatedItems() {
		return null; 
	};
	
	public BigDecimal getTotalDealSaving(Currency requiredCurrency) {
		return BigDecimal.ZERO;
	}
	
	public BigDecimal getBaseCost(Currency requiredCurrency) {
		return BigDecimal.ZERO;
	}

	@Override
	public int compareTo(Object o) {
		return 0; // we don't care about the order of application in a larger set for now
	}
}
