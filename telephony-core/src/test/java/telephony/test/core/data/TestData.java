package telephony.test.core.data;

import telephony.core.util.StringGeneratorImpl;

/**
 * Helpers which handles references to data from .sql files.
 */
public final class TestData {

	private TestData() {}
	
	/**
	 * Some test data.
	 */
	public static final String USER1_NAME = "user1@gmail.com";
	public static final String USER1_PASSWORD = "rfaysdhaiufsiuf";	
	public static final String USER1_SESSIONID = "asdweasdweasdweasdweasdweasdwe21";
	
	public static final String USER2_NAME = "user2@gmail.com";
	public static final String USER2_PASSWORD = "sdaysdhaiufsiua";	
	public static final String USER2_SESSIONID = "asdweasdweasdweasdweasdweasdwe21";
	
	public static final String USER3_NAME = "boss@gmail.com";
	public static final String USER3_PASSWORD = "zwaysdhaiufsiko";	
	public static final String USER3_SESSIONID = "asdweasdweasdweasdweasdweasdwe21";
	
	public static final String USER4_NAME = "manager@gmail.com";
	public static final String USER4_PASSWORD = "wertsdhnbgfsiko";
	public static final String USER4_SESSIONID = "asdweasdweasdweasdweasdweasdwe21";
	
	public static final String STORE1_LABEL = "cieszyn";	
	public static final String STORE2_LABEL = "raciborz";
		
	public static final String CONTACT1_LABEL = "adam";
	public static final String CONTACT2_LABEL = "leszek";
	public static final String CONTACT3_LABEL = "grzegorz";
	
	public static final String SALE1_LABEL = "nowy rok cieszyn";
	public static final String SALE2_LABEL = "nowy rok raciborz";
	
	/**
	 * Generates random sessionId.
	 * @return Random sessionId.
	 */
	public static String randomSessionId() {
		StringGeneratorImpl g = new StringGeneratorImpl();		
		return g.nextSessionId();
	}	
	
}
