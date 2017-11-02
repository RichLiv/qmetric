/**
 * Base for all forms of logging. Serializable may help to persist across a web service
 */
package com.qmetric.audit;

import java.io.Serializable;

import com.qmetric.exception.AuditFailureException;
import com.qmetric.goods.ShoppingBasket;

/**
 * @author Richard Livingstone
 *
 */
public interface AuditLogger extends Serializable {
	public void logSale(ShoppingBasket basket) throws AuditFailureException;
}
