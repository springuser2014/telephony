package telephony.core.guice.env;

/**
 * asd.
 */
public class SystemPropertyEnvironmentNameResolver implements EnvironmentNameResolver {

	@Override
	public String getEnvironmentProperty() {
		return System.getProperty("environment") != null 
				? System.getProperty("environment") 
				: "PRODUCTION";
	}
}
