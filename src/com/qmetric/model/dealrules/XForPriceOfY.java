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
	/**
	 * @return
	 */
	public int getNumberOfItemsBought() {
		return numberOfItemsBought;
	}

	/**
	 * @return
	 */
	public int getNumberOfItemsCharged() {
		return numberOfItemsCharged;
	}

	private int numberOfItemsCharged;
	private List<StockItem> discountedItem = new ArrayList<StockItem>();
	
	/**
	 * @param x
	 * @param y
	 * @param discountedItem
	 */
	public XForPriceOfY(int x, int y, StockItem discountedItem) {
		this.numberOfItemsBought = x;
		this.numberOfItemsCharged = y;
		assert(x > 0);
		assert(y > 0);
		assert(x > y);
		assert(discountedItem != null);
		this.discountedItem.add(discountedItem);
	}
	
	/* (non-Javadoc)
	 * @see com.qmetric.model.dealrules.DealRules#getRelatedItems()
	 */
	public Collection<StockItem> getRelatedItems() {
		return this.discountedItem;
	};

	/* (non-Javadoc)
	 * @see com.qmetric.model.dealrules.DealRules#getTotalDealSaving(com.qmetric.model.pricingmodels.Currency, int)
	 */
	public BigDecimal getTotalDealSaving(Currency requiredCurrency, int numberOfApplicationsOfDeal) {
		return this.discountedItem.get(0).getPriceAtTill().getPriceInCents().multiply(BigDecimal.valueOf(this.numberOfItemsBought-this.numberOfItemsCharged)).multiply(BigDecimal.valueOf(numberOfApplicationsOfDeal));
	}

	/* (non-Javadoc)
	 * @see com.qmetric.model.dealrules.DealRules#getBaseCost(com.qmetric.model.pricingmodels.Currency)
	 */
	public BigDecimal getBaseCost(Currency requiredCurrency) {
		return this.discountedItem.get(0).getCostOfSupply().getPriceInCents().multiply(BigDecimal.valueOf(this.numberOfItemsBought));
	}

	/* (non-Javadoc)
	 * @see com.qmetric.model.dealrules.UniqueDealRules#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		return 0; // we don't care about the order of application in a larger
					// set for now
	}
}
