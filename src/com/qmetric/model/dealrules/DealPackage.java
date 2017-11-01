/**
 * 
 */
package com.qmetric.model.dealrules;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.qmetric.goods.ShoppingBasket;

/**
 * @author Richard Livingstone
 *
 */
public interface DealPackage {
	public void addRules(DealRules deal);
	public List<DealRules> getApplicableRules(ShoppingBasket b);
}
