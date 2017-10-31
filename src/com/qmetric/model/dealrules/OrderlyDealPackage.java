/**
 * 
 */
package com.qmetric.model.dealrules;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Richard Livingstone
 *
 */
public final class OrderlyDealPackage implements DealPackage {
	// ordering is via equals() so we would make DealRules Comparable and implement in whatever way we chose
	// for next bit
	SortedSet<DealRules> rulesForDeal = new TreeSet<DealRules>();

	public void addRules(DealRules deal) {
		this.rulesForDeal.add(deal);
	}
	
	/*
	 * To do - fix this so that it compares the value for money of each deal in the list and returns the best value
	 * or whatever rule the store wants to implement (searchandising, in ecommerce terms, to push certain deals) 
	 */
	public DealRules getApplicableRule() {
		return rulesForDeal.size() == 0 ? null : rulesForDeal.first();
	}
}
