package pl.alex.exceptions;

public class PersonAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PersonAlreadyExistsException(String message) {
		super(message);
	}
	
	

}
