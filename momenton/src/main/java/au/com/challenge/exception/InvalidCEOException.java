package au.com.challenge.exception;

/**
 * This class is a exception class thrown when invalid CEO is created
 * 
 * @author Preetham
 *
 */
public class InvalidCEOException extends RuntimeException {
	public InvalidCEOException(String message) {
		super(message);
	}
}
