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
public abstract class BuyXGetYFree extends UniqueDealRules {
	public Collection<StockItem> getRelatedItems() {
		return null;
	};

	public BigDecimal getTotalDealSaving(Currency requiredCurrency, int numberOfApplicationsOfDeal) {
		return BigDecimal.ZERO;
	}

	public BigDecimal getBaseCost(Currency requiredCurrency) {
		return BigDecimal.ZERO;
	}
}
