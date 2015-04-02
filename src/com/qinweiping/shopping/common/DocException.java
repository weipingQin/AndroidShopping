package com.qinweiping.shopping.common;

/**
 * An exception class that will be thrown when WeiboAPI calls are failed.<br>
 * In case the Weibo server returned HTTP error code, you can get the HTTP
 * status code using getStatusCode() method.
 */
@SuppressWarnings("serial")
public class DocException extends Exception {
	private int statusCode = -1;

	public DocException(String msg) {
		super(msg);
	}

	public DocException(Exception cause) {
		super(cause);
	}

	public DocException(String msg, int statusCode) {
		super(msg);
		this.statusCode = statusCode;
	}

	public DocException(String msg, Exception cause) {
		super(msg, cause);
	}

	public DocException(String msg, Exception cause, int statusCode) {
		super(msg, cause);
		this.statusCode = statusCode;

	}

	public int getStatusCode() {
		return this.statusCode;
	}
}
