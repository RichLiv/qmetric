/**
 * Base class for exception hierarchy 
 */
package com.qmetric.exception;

/**
 * @author Richard Livingstone
 *
 */
@SuppressWarnings("serial")
public class BaseException extends Throwable {
	/**
	 * @param msg
	 * @param origin
	 */
	public BaseException(String msg, Throwable origin) {
		super(msg, origin);
	}

}
