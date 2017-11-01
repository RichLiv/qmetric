/**
 * 
 */
package com.qmetric.model.dealrules;

/**
 * @author Richard Livingstone
 *
 */
public abstract class UniqueDealRules implements DealRules {
	/*
	 * Required so we get this added uniquely to sets
	 */
	public boolean equals(Object other) {
		if (other == null) return false;
		if (!(other instanceof DealRules)) return false;
		// these should be unique
		return getReceiptLine().equals(((DealRules)other).getReceiptLine());
	}
	
	public int hashCode() {
		return getReceiptLine().hashCode();
	}
	@Override
	public int compareTo(Object o) {
		return 0; // we don't care about the order of application in a larger set for now
	}

}
