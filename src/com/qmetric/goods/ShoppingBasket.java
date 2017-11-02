/**
 * 
 */
package com.qmetric.goods;

import java.io.Serializable;
import java.util.List;

import com.qmetric.model.dealrules.DealRules;
import com.qmetric.model.pricingmodels.Currency;

/**
 * @author Richard Livingstone
 *
 */
public interface ShoppingBasket extends Serializable {
	/**
	 * @return
	 */
	List<StockItem> getItems();

	/**
	 * @param item
	 */
	void addItemToBasket(StockItem item);

	/**
	 * @return
	 */
	Currency getCurrency(); //

	/**
	 * @param deal
	 * @return Number of times this deal could be applied to this basket
	 */
	int numberOfMatches(DealRules deal); 
	// + remove, modify etc
}
