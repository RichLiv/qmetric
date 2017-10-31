/**
 * Base class for exception hierarchy 
 */
package com.qmetric.exception;

@SuppressWarnings("serial")
public class BaseException extends Throwable {
	public BaseException(String msg, Throwable origin) {
		super(msg, origin);
	}

}
