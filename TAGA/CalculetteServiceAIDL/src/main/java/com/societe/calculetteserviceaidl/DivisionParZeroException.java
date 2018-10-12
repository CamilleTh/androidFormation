package com.societe.calculetteserviceaidl;

public class DivisionParZeroException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DivisionParZeroException(String message) {
		super(message);
	}
}
