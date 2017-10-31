/**
 * Thrown when audit logging fails. Use this class so we can catch at a higher level and do
 * something more uniform 
 */
package com.qmetric.exception;

public class AuditFailureException extends BaseException {
	public AuditFailureException(String msg, Throwable origin) {
		super(msg, origin);
	}

}
