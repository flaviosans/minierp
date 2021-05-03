package dev.flaviosantos.minierp.exception;

public class ClosedOrderException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ClosedOrderException() {
		super("Order is closed");
	}
}
