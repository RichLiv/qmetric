/**
 * 
 */
package com.qmetric.model.dealrules;

import java.util.SortedSet;

/**
 * @author Richard Livingstone
 *
 */
public class DealPackage {
	SortedSet<DealRules> rulesForDeal;

	public SortedSet<DealRules> getRulesForDeal() {
		return rulesForDeal;
	}
}
