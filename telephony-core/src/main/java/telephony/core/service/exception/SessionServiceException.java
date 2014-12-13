package telephony.core.service.exception;

/**
 * Represents SessionService's error object.
 */
public class SessionServiceException extends Exception {

	private static final long serialVersionUID = 1057881870569061811L;

	public SessionServiceException() {}

	public SessionServiceException(Throwable e, String msg) {
		super(msg, e);
	}
}
