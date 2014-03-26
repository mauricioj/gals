package br.ufscar.sigam.exception;

public class InvalidLoginException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidLoginException() { super(); }
	public InvalidLoginException(String message) { super(message); }
	public InvalidLoginException(String message, Throwable cause) { super(message, cause); }
	public InvalidLoginException(Throwable cause) { super(cause); }
}
