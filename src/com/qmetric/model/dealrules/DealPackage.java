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
public interface DealPackage {
	public void addRules(DealRules deal);
	public DealRules getApplicableRule();
}
