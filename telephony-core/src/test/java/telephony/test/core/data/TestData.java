package telephony.test.core.data;

import telephony.core.util.StringGeneratorImpl;

/**
 * Helpers which handles references to data from .sql files.
 */
public final class TestData {


	public static final Long DELIVERY2_ID = 2L;
	public static final Long PRODUCT7_ID = 7L;
	public static final Long PRODUCT8_ID = 8L;
	public static final Long TAX6_ID = 6L;
	public static final Long PRODUCT_8_ID = 8L;
	public static final Long TAX7_ID = 7L;
	public static final Long DELIVERY1_ID = 1L;
	public static final Long PRODUCT9_ID = 9L;

	private TestData() {}
	
	/**
	 * Some test data.
	 */
	public static final Long USER1_ID = 1L;
	public static final String USER1_NAME = "user1@gmail.com";
	public static final String USER1_PASSWORD = "rfaysdhaiufsiuf";	
	public static final String USER1_SESSIONID = "asdweasdweasdweasdweasdweasdwe21";

	public static final Long USER2_ID = 2L;
	public static final String USER2_NAME = "user2@gmail.com";
	public static final String USER2_PASSWORD = "sdaysdhaiufsiua";	
	public static final String USER2_SESSIONID = "asdweasdweasdweasdweasdweasdwe21";

	public static final Long USER_BOSS_ID = 3L;
	public static final String USER_BOSS_NAME = "boss@gmail.com";
	public static final String USER_BOSS_PASSWORD = "zwaysdhaiufsiko";	
	public static final String USER_BOSS_SESSIONID = "asdweasdweasdweasdweasdweasdwe21";

	public static final Long USER_MANAGER_ID = 4L;
	public static final String USER_MANAGER_NAME = "manager@gmail.com";
	public static final String USER_MANAGER_PASSWORD = "wertsdhnbgfsiko";
	public static final String USER_MANAGER_SESSIONID = "asdweasdweasdweasdweasdweasdwe21";
	
	public static final String STORE_CIESZYN_LABEL = "cieszyn";	
	public static final String STORE_RACIBORZ_LABEL = "raciborz";

	public static final Long STORE1_ID = 1L;
	public static final Long STORE2_ID = 2L;
		
	public static final String CONTACT1_LABEL = "adam";
	public static final String CONTACT2_LABEL = "leszek";
	public static final String CONTACT3_LABEL = "grzegorz";
	
	public static final String SALE1_LABEL = "nowy rok cieszyn";
	public static final String SALE2_LABEL = "nowy rok raciborz";

	public static final String ROLE_SALESMAN_LABEL = "salesman";
	public static final String ROLE_BOSS_LABEL = "boss";
	public static final String ROLE_SHOP_MANAGER_LABEL = "shop_manager";

	public static final Long ROLE_SALESMAN_ID = 1L;
	public static final Long ROLE_BOSS_ID = 2L;
	public static final Long ROLE_SHOP_MANAGER_ID = 3L;

	public static final Long PRODUCT1_ID = 1l;
	public static final String PRODUCT1_IMEI = "123456789000000";
	public static final String PRODUCT1_COLOR = "black";

	public static final Long PRODUCT5_ID = 5l;
	public static final String PRODUCT5_IMEI = "123456789000004";

	public static final Long PRODUCT2_ID = 2l;
	public static final String PRODUCT2_IMEI = "123456789000001";
	public static final String PRODUCT2_COLOR = "black";

	public static final Long PRODUCT48_ID = 48l;
	public static final String PRODUCT48_IMEI = "123456789000047";
	public static final String PRODUCT48_COLOR = "white";

	public static final Long PRODUCT3_ID = 3l;
	public static final Long PRODUCT4_ID = 4l;

	public static final Long PRODUCT12_ID = 12L;

	public static final Long PRODUCT29_ID = 29L;

	public static final Long PRODUCER_NOKIA_ID = 1l;
	public static final String PRODUCER_NOKIA_LABEL = "nokia";

	public static final Long PRODUCER_APPLE_ID = 2l;
	public static final String PRODUCER_APPLE_LABEL = "apple";

	public static final Long PRODUCER_SAMSUNG_ID = 3l;
	public static final String PRODUCER_SAMSUNG_LABEL = "samsung";

	public static final Long PRODUCER_HTC_ID = 4l;
	public static final String PRODUCER_HTC_LABEL = "htc";

	public static final Long MODEL1_ID = 1l;
	public static final String MODEL1_LABEL = "6610s";

	public static final Long MODEL2_ID = 2l;
	public static final String MODEL2_LABEL = "6600n";

	public static final Long MODEL3_ID = 3l;
	public static final String MODEL3_LABEL = "3310";

	public static final Long MODEL4_ID = 4l;
	public static final String MODEL4_LABEL = "iphone 4s";

	public static final Long MODEL5_ID = 5l;
	public static final String MODEL5_LABEL = "iphone 5g";

	public static final Long MODEL6_ID = 6l;
	public static final String MODEL6_LABEL = "iphone 3g";

	public static final Long MODEL7_ID = 7l;
	public static final String MODEL7_LABEL = "iphone 6g";

	public static final Long MODEL8_ID = 8l;
	public static final String MODEL8_LABEL = "iphone 7g";

	public static final String COLOR_BLACK = "black";
	public static final String COLOR_WHITE = "white";
	public static final String COLOR_BLUE = "blue";
	public static final String COLOR_RED = "red";

	public static final String SALE_CIESZYN_LABEL = "nowy rok cieszyn";
	public static final String SALE_RACIBORZ_LABEL = "nowy rok raciborz";

	public static final Long SALE1_ID = 1L;
	public static final Long SALE2_ID = 2L;

	public static final Long DELIVERY7_ID = 7L;

	public static final Long CONTACT1_ID = 1L;
	public static final Long CONTACT2_ID = 2L;

	public static final Long COMPLAINT1_ID = 1L;
	public static final String COMPLAINT1_DESC = "Description of product complaint 1";

	public static final Long COMPLAINT7_ID = 7L;
	public static final String COMPLAINT7_DESC = "Description of sale complaint 1";
	
	/**
	 * Generates random sessionId.
	 * @return Random sessionId.
	 */
	public static String randomSessionId() {
		StringGeneratorImpl g = new StringGeneratorImpl();		
		return g.nextSessionId();
	}


}
