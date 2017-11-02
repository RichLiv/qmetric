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
		if (other == null) {
			return false;
		}
		if (!(other instanceof DealRules)) {
			return false;
		}
		// these should be unique for each deal type
		return getReceiptLine().equals(((DealRules) other).getReceiptLine());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getReceiptLine().hashCode();
	}

	/* Implement depending on how you want deals to be ordered in their application
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public abstract int compareTo(Object other);

}
