/**
 * 
 */
package com.qmetric.model.dealrules;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

import com.qmetric.goods.StockItem;
import com.qmetric.model.pricingmodels.Currency;

/**
 * @author Richard Livingstone
 *
 */
public abstract class XItemsWithDiscountY extends UniqueDealRules {
	private int numberOfItemsBought;
	public int getNumberOfItemsBought() {
		return numberOfItemsBought;
	}

	public int getDiscountInCents() {
		return discountInCents;
	}

	private int discountInCents;
	private Collection<StockItem> relatedItems;
	
	/**
	 * @param x - Number of items for discount if all the same 
	 * @param y - discount in cents
	 * @param relatedItems - either a single item to be used with x as the number for which discount is to be applied,
	 * or a set of possible different items, in which case x is ignored
	 */
	public XItemsWithDiscountY(int x, int y, Collection<StockItem> relatedItems) {
		this.numberOfItemsBought = x;
		this.discountInCents = y;
		this.relatedItems = relatedItems;
		assert(x > 0);
		assert(y > 0);
		assert(relatedItems.size() > 0); // if 1, always discount this item
	}

	/*
	 * getRelatedItems needs to return a set of arbitrary items (can all be different)
	 * @see com.qmetric.model.dealrules.DealRules#getRelatedItems()
	 */
	public Collection<StockItem> getRelatedItems() {
		return this.relatedItems;
	};

	public BigDecimal getTotalDealSaving(Currency requiredCurrency, int numberOfApplicationsOfDeal) {
		return BigDecimal.valueOf(this.discountInCents); // fixed discount in this case
	}

	public BigDecimal getBaseCost(Currency requiredCurrency) {
		assert(getRelatedItems().size() > 0);
		float totalCost = 0.00F;
		for (StockItem nextItem : getRelatedItems()) {
			totalCost += nextItem.getPriceAtTill().getPriceInCents().floatValue(); // currency conversions maybe if necessary
		}
		if (relatedItems.size() == 1) {
			totalCost *= numberOfItemsBought;
		}
		return BigDecimal.valueOf(totalCost).setScale(0, RoundingMode.HALF_UP);
	}
}
