package wnsud9771.model;

public class DataNotFoundException extends RuntimeException{
	
	public DataNotFoundException() {
		
	}
	
	public DataNotFoundException (String message) {
		super(message);
	}
}
