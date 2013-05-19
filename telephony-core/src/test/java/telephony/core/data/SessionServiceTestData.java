package telephony.core.data;

import telephony.core.util.StringGeneratorImpl;

/**
 * asd.
 * @author pahe
 */
public final class SessionServiceTestData {
	
	private SessionServiceTestData() {
		
	}
	
	/**
	 * asd.
	 */
	public static final String USER1_NAME = "user1@gmail.com";
	public static final String USER1_PASSWORD = "rfaysdhaiufsiuf";	
	
	public static final String USER2_NAME = "user2@gmail.com";
	public static final String USER2_PASSWORD = "sdaysdhaiufsiua";	
	
	public static final String USER3_NAME = "boss@gmail.com";
	public static final String USER3_PASSWORD = "zwaysdhaiufsiko";	
	
	public static final String USER4_NAME = "manager@gmail.com";
	public static final String USER4_PASSWORD = "wertsdhnbgfsiko";
	
	/**
	 * Generates random sessionId.
	 * @return Random sessionId.
	 */
	public static String randomSessionId() {
		StringGeneratorImpl g = new StringGeneratorImpl();		
		return g.nextSessionId();
	}	
	
}
