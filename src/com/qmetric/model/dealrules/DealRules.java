/**
 * 
 */
package com.qmetric.model.dealrules;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import com.qmetric.goods.StockItem;
import com.qmetric.model.pricingmodels.Currency;

/**
 * @author Richard Livingstone
 *
 */
/**
 * @author Richard Livingstone
 *
 */
public interface DealRules extends Comparable, Serializable  {
	/**
	 * @return
	 */
	Collection<StockItem> getRelatedItems();

	/**
	 * @param requiredCurrency
	 * @param numberOfApplicationsOfDeal - depending on the basket, this deal could be applied multiple times 
	 * @return
	 */
	BigDecimal getTotalDealSaving(Currency requiredCurrency, int numberOfApplicationsOfDeal);

	/**
	 * Cost of items in deal before saving
	 * @param requiredCurrency
	 * @return
	 */
	BigDecimal getBaseCost(Currency requiredCurrency); 

	/**
	 * @return
	 */
	String getReceiptLine();
}
