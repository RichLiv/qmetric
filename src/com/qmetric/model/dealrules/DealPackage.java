/**
 * 
 */
package com.qmetric.model.dealrules;

import java.io.Serializable;
import java.util.List;

import com.qmetric.goods.ShoppingBasket;

/**
 * @author Richard Livingstone
 *
 */
public interface DealPackage extends Serializable {
	/**
	 * @param deal
	 */
	void addRules(DealRules deal);

	/**
	 * @param b
	 * @return
	 */
	List<DealRules> getApplicableRules(ShoppingBasket b);
}
