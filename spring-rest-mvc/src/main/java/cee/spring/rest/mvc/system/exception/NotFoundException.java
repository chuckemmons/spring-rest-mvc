/**
 *
 */
package cee.spring.rest.mvc.system.exception;

/**
 * @author chuck
 *
 */
public class NotFoundException extends Exception {
	private static final long serialVersionUID = 6914897230066137406L;

	private static final String FIND_ALL_NOT_FOUND_FORMAT = "Could not find %ss.";

	private static final String FIND_BY_NOT_FOUND_FORMAT = "Could not find %s by '%s' = '%s'.";

	public <T> NotFoundException(final String objectName, final String property, final Object value) {
		super(formatFindByPropertyEqualsValueMessage(objectName, property, value));
	}

	public <T> NotFoundException(final String objectName) {
		super(formatFindAllMessage(objectName));
	}

	public static String formatFindAllMessage(final String objectName) {
		return String.format(FIND_ALL_NOT_FOUND_FORMAT, objectName);
	}

	public static String formatFindByPropertyEqualsValueMessage(final String objectName, final String property,
			final Object value) {
		return String.format(FIND_BY_NOT_FOUND_FORMAT, objectName, property, value.toString());
	}


}
