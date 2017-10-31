/**
 * 
 */
package com.qmetric.goods;

import java.util.List;

/**
 * @author Richard Livingstone
 *
 */
public interface ShoppingBasket {
	public List<StockItem> getItems();
	public void addItemToBasket(StockItem item);
	
	// + remove, modify etc
}
