/**
 * 
 */
package com.qmetric.model.dealrules;

import java.util.ArrayList;
import java.util.List;

import com.qmetric.goods.ShoppingBasket;

/**
 * @author Richard Livingstone
 *
 */
public class OrderlyDealPackage implements DealPackage {
	// could order using TreeSet but more likely that explicit ordering will take place either due to basket contents 
	// or from store staff
	private List<DealRules> rulesForDeal = new ArrayList<DealRules>();

	public void addRules(DealRules deal) {
		this.rulesForDeal.add(deal);
	}
	
	/*
	 * Returns deals applied in order in which they were implemented
	 * Currently, this is not actually implemented but it could be used to implement whatever precanned algorithm we  want 
	 */
	public List<DealRules> getApplicableRules(ShoppingBasket b) {
		List<DealRules> ret = new ArrayList<DealRules>();
		ret.addAll(rulesForDeal); // TODO apply much more logic to this process 
		return ret;
	}

}
