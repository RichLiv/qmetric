/**
 * 
 */
package com.qmetric.goods;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Livingstone
 *
 */
public class InStoreShoppingBasket implements ShoppingBasket {
	
	private List<StockItem> basketContents = new ArrayList<StockItem>();
	public InStoreShoppingBasket() {}
	public List<StockItem> getItems() { return this.basketContents;}
	public void addItemToBasket(StockItem item) { this.basketContents.add(item); };
}
