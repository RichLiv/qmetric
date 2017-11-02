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
		System.out.println(new StringBuffer("Log this to a debug file for developers: <").append(basket.toString())
				.append(">. For now, just print to stdout").toString());
	}

	public void logTestOutput(String out) {
		System.out.println(out);
	}

	public void logOutput(String out, Throwable e) {
		if (out != null)
			System.out.println(out);
		if (e != null)
			e.printStackTrace(System.err);
	}
}
