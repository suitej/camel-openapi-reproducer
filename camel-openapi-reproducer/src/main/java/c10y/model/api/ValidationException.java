/**
 * 
 */
package c10y.model.api;

/**
 * 
 * @author Jean-Marc Reynaud
 *
 */
public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1184938112745215626L;

	/**
	 * @param message
	 */
	public ValidationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ValidationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
