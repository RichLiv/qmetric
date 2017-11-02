/**
 * 
 */
package com.qmetric.model.dealrules;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.qmetric.goods.StockItem;
import com.qmetric.model.pricingmodels.Currency;

/**
 * @author Richard Livingstone
 *
 */
public abstract class XForPriceOfY extends UniqueDealRules {
	
	private int numberOfItemsBought;
	private int numberOfItemsCharged;
	private List<StockItem> discountedItem = new ArrayList<StockItem>();
	
	public XForPriceOfY(int x, int y, StockItem discountedItem) {
		this.numberOfItemsBought = x;
		this.numberOfItemsCharged = y;
		assert(x > 0);
		assert(y > 0);
		assert(x > y);
		assert(discountedItem != null);
		this.discountedItem.add(discountedItem);
	}
	
	public Collection<StockItem> getRelatedItems() {
		return this.discountedItem;
	};

	public BigDecimal getTotalDealSaving(Currency requiredCurrency, int numberOfApplicationsOfDeal) {
		return this.discountedItem.get(0).getPriceAtTill().getPriceInCents().multiply(BigDecimal.valueOf(this.numberOfItemsBought-this.numberOfItemsCharged)).multiply(BigDecimal.valueOf(numberOfApplicationsOfDeal));
	}

	public BigDecimal getBaseCost(Currency requiredCurrency) {
		return this.discountedItem.get(0).getCostOfSupply().getPriceInCents().multiply(BigDecimal.valueOf(this.numberOfItemsBought));
	}

	@Override
	public int compareTo(Object o) {
		return 0; // we don't care about the order of application in a larger
					// set for now
	}
}
