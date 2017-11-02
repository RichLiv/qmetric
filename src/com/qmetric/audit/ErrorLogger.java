/**
 * Error logger, which could use Log4J, SLF4J, LogBack, or more sophisticated distributed and enterprise logging
 * such as SNMP
 */
package com.qmetric.audit;

import com.qmetric.exception.AuditFailureException;
import com.qmetric.goods.ShoppingBasket;

/**
 * @author Richard Livingstone
 *
 */
public class ErrorLogger implements AuditLogger {
	@Override
	public void logSale(ShoppingBasket basket) throws AuditFailureException {
		System.out.println(new StringBuffer("Log this to file using logback or similar <").append(basket.toString())
				.append(">. For now, just print to stdout").toString());
	}

}
