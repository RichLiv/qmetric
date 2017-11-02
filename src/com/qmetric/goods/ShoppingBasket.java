/**
 * 
 */
package com.qmetric.goods;

import java.util.List;

import com.qmetric.model.dealrules.DealRules;
import com.qmetric.model.pricingmodels.Currency;

/**
 * @author Richard Livingstone
 *
 */
public interface ShoppingBasket {
	public List<StockItem> getItems();

	public void addItemToBasket(StockItem item);

	public Currency getCurrency(); //

	/**
	 * @param deal
	 * @return Number of times this deal could be applied to this basket
	 */
	public int numberOfMatches(DealRules deal); 
	// + remove, modify etc
}
