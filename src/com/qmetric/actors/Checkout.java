/**
 * Different kinds of checkout might lend themselves to different scenarios involving 
 * interactions with different hardware, actors, customers and perhaps directly with suppliers ultimately
 * although perhaps this is out of scope. Just mentioned to show it might be a consideration   
 */
package com.qmetric.actors;

import java.util.List;

import com.qmetric.audit.AuditLogger;
import com.qmetric.goods.ShoppingBasket;
import com.qmetric.model.dealrules.DealPackage;

/**
 * @author Richard Livingstone
 *
 */
public interface Checkout {
	/**
	 * Returns a receipt (and probably does lots more but for this exercise a receipt is enough
	 * 
	 * @param basket
	 * @param currentDeals
	 * @return
	 */
	public List<String> checkout(ShoppingBasket basket, List<DealPackage> currentDeals);

	/**
	 * @return
	 */
	public List<AuditLogger> getLoggers();
}
