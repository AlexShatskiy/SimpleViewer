package com.shatskiy.repository.dao.exception;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Exception e) {
		super(e);
	}

	public DaoException(String message, Exception e) {
		super(message, e);
	}
}
