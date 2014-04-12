package telephony.web;

public class EnvironmentResolver {
	
	public static Environment resolve() {
		String env = System.getProperty("env");
		
		return Environment.getForString(env);
	}
}
