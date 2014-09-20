package telephony.core.service.dto;

import java.util.Date;

/**
 * asd.
 */
// TODO : replace using SessionDto
public class SessionBean {

	protected String username;
	protected String sessionId;
	protected Date validity;

	/**
	 * asd.
	 * @return asd.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * asd.
	 * @param username asd.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * asd.
	 * @return asd.
 	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * asd.
	 * @param sessionId ads.
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public Date getValidity() {
		return validity;
	}

	/**
	 * asd.
	 * @param validity asd.
	 */
	public void setValidity(Date validity) {
		this.validity = validity;
	}

	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
 	 * @param validity asd.
	 */
	public SessionBean(String username, String sessionId, Date validity) {
		super();
		this.username = username;
		this.sessionId = sessionId;
		this.validity = validity;
	}

	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 */
	public SessionBean(String username, String sessionId) {
		super();
		this.username = username;
		this.sessionId = sessionId;
	}

}
