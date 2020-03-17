/**
 *
 */
package cee.spring.rest.mvc.system.exception;

/**
 * @author chuck
 *
 */
public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = -3238524023719384397L;


	public ApplicationException(final String message) {
		super(message);
	}

	public ApplicationException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
