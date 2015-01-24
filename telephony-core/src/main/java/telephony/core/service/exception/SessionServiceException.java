package telephony.core.service.exception;

public class SessionServiceException extends Exception {

	private static final long serialVersionUID = 1057881870569061811L;

	public SessionServiceException() {}

	public SessionServiceException(String msg) {
		super(msg);
	}

	public SessionServiceException(Throwable e, String msg) {
		super(msg, e);
	}
}
