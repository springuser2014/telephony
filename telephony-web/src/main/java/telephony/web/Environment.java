package telephony.web;

public enum Environment {
	
	PROD("prod"), TEST("test");
	
	private String env ;
	
	private Environment(String env) {
		this.env = env;
	}
	
	public static Environment getForString(String env) {
		
		if (env.equals(PROD.env))
			return PROD;
		else if (env.equals(TEST.env))
			return TEST;
		else 
			return TEST;
		//else 
		//	throw new RuntimeException("Wrong Environment passed!");
	}
}
