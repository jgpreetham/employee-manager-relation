package au.com.challenge.exception;

/**
 * This class is a exception class thrown when invalid employee is created
 * 
 * @author Preetham
 *
 */
public class InvalidEmployeeException extends RuntimeException {
	public InvalidEmployeeException(String message) {
		super(message);
	}
}

