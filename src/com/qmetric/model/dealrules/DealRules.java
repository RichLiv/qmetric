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
public interface DealRules extends Comparable {
	public Collection<StockItem> getRelatedItems();

	/**
	 * @param requiredCurrency
	 * @param numberOfApplicationsOfDeal - depending on the basket, this deal could be applied multiple times 
	 * @return
	 */
	public BigDecimal getTotalDealSaving(Currency requiredCurrency, int numberOfApplicationsOfDeal);

	/*
	 * Cost of items in deal before saving
	 */
	public BigDecimal getBaseCost(Currency requiredCurrency); 

	public String getReceiptLine();
}
