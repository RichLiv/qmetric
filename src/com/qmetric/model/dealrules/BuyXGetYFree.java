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
	/* (non-Javadoc)
	 * @see com.qmetric.model.dealrules.DealRules#getRelatedItems()
	 */
	public abstract Collection<StockItem> getRelatedItems();

	/* (non-Javadoc)
	 * @see com.qmetric.model.dealrules.DealRules#getTotalDealSaving(com.qmetric.model.pricingmodels.Currency, int)
	 */
	public abstract BigDecimal getTotalDealSaving(Currency requiredCurrency, int numberOfApplicationsOfDeal);

	/* (non-Javadoc)
	 * @see com.qmetric.model.dealrules.DealRules#getBaseCost(com.qmetric.model.pricingmodels.Currency)
	 */
	public abstract BigDecimal getBaseCost(Currency requiredCurrency);
}
