/**
 * 
 */
package com.qmetric.goods;

import java.util.List;

import com.qmetric.model.pricingmodels.Currency;

/**
 * @author Richard Livingstone
 *
 */
public interface ShoppingBasket {
	public List<StockItem> getItems();
	public void addItemToBasket(StockItem item);
	public Currency getCurrency(); // 
	// + remove, modify etc
}
