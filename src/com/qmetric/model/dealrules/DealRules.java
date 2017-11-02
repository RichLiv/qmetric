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

	public BigDecimal getTotalDealSaving(Currency requiredCurrency, int numberOfApplicationsOfDeal); // although
																		// for
																		// now
																		// we
																		// will
																		// stick
																		// with
																		// one

	public BigDecimal getBaseCost(Currency requiredCurrency); // although for
																// now we will
																// stick with
																// one

	public String getReceiptLine();
}
