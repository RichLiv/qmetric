/**
 * 
 */
package com.qmetric.goods;

import java.util.Collection;
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
	public int numberOfMatches(Collection<StockItem> dealComponents); // will allow us to work out how many times a deal could be applied to a basket  
	// + remove, modify etc
}
