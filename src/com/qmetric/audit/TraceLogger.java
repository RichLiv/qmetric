/**
 * 
 */
package com.qmetric.audit;

import com.qmetric.exception.AuditFailureException;
import com.qmetric.goods.ShoppingBasket;

/**
 * @author Richard Livingstone
 *
 */
public class TraceLogger implements AuditLogger {

	@Override
	public void logSale(ShoppingBasket basket) throws AuditFailureException {
		System.out.println(new StringBuffer("Log this to a debug file for developers: <").append(basket.toString()).append(">. For now, just print to stdout").toString());
	}
}
