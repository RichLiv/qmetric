/**
 * 
 */
package com.qmetric.goods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import com.qmetric.model.pricingmodels.Currency;

/**
 * @author Richard Livingstone
 *
 */
public class InStoreShoppingBasket implements ShoppingBasket {
	
	private List<StockItem> basketContents = new ArrayList<StockItem>();
	public InStoreShoppingBasket() {}
	public List<StockItem> getItems() { return this.basketContents;}
	public void addItemToBasket(StockItem item) { this.basketContents.add(item); }
	@Override
	public Currency getCurrency() {
		return Currency.GBP; // simplicity
	};
	
	public String toString() {
		StringBuffer ret = new StringBuffer("Shopping basket on ").append(new SimpleDateFormat("dd MM yyyy hh:mm").format(Calendar.getInstance().getTime())).append("\n");
		for (StockItem nextOne : getItems()) {
			ret.append(nextOne.toString()).append("\n");
		}
		return ret.toString();
	}
	@Override
	public int numberOfMatches(Collection<StockItem> dealComponents) {
		int count = 0;
		boolean keepLooking = true;
		List<StockItem> testContents = new ArrayList<StockItem>(basketContents);
		while (keepLooking) {
			if (testContents.containsAll(dealComponents)) {
				count++;
				testContents.removeAll(dealComponents);
			}
			else {
				keepLooking = false;
			}
		}
		return count;
	}
}
