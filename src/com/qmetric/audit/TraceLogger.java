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

	/* (non-Javadoc)
	 * @see com.qmetric.audit.AuditLogger#logSale(com.qmetric.goods.ShoppingBasket)
	 */
	@Override
	public void logSale(ShoppingBasket basket) throws AuditFailureException {
		System.out.println(new StringBuffer("Log this to a debug file for developers: <").append(basket.toString())
				.append(">. For now, just print to stdout").toString());
	}

	/**
	 * @param out
	 */
	public void logTestOutput(String out) {
		System.out.println(out);
	}

	/**
	 * @param out
	 * @param e
	 */
	public void logOutput(String out, Throwable e) {
		if (out != null)
			System.out.println(out);
		if (e != null)
			e.printStackTrace(System.err);
	}
}
