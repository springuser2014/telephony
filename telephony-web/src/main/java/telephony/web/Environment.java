package telephony.web;

public enum Environment {
	
	PROD("prod"), TEST("test");
	
	private String env ;
	
	private Environment(String env) {
		this.env = env;
	}
	
	public static Environment getForString(String env) {
		
		if (PROD.env.equals(env))
			return PROD;
		else if (TEST.env.equals(env))
			return TEST;
		else 
			return TEST;
		//else 
		//	throw new RuntimeException("Wrong Environment passed!");
	}
}
