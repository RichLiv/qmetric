/**
 * Thrown when audit logging fails. Use this class so we can catch at a higher level and do
 * something more uniform 
 */
package com.qmetric.exception;

/**
 * @author Richard Livingstone
 *
 */
public class AuditFailureException extends BaseException {
	/**
	 * @param msg
	 * @param origin
	 */
	public AuditFailureException(String msg, Throwable origin) {
		super(msg, origin);
	}

}
