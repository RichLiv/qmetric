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
public class AccountingLedgerAuditLogger implements AuditLogger {

	/* (non-Javadoc)
	 * @see com.qmetric.audit.AuditLogger#logSale(com.qmetric.goods.ShoppingBasket)
	 */
	@Override
	public void logSale(ShoppingBasket basket) throws AuditFailureException {
		System.out.println(new StringBuffer("Log this to the accounting ledger back end: <").append(basket.toString())
				.append(">. For now, just print to stdout").toString());
	}

}
