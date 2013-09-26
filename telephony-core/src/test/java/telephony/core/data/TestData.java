package telephony.core.data;

import telephony.core.util.StringGeneratorImpl;

/**
 * Class helps to handle references to data from .sql files.
 * @author Paweł Henek <pawelhenek@gmail.com>
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
	
	/**
	 * Generates random sessionId.
	 * @return Random sessionId.
	 */
	public static String randomSessionId() {
		StringGeneratorImpl g = new StringGeneratorImpl();		
		return g.nextSessionId();
	}	
	
}