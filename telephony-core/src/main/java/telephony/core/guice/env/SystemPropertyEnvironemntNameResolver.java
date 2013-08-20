package telephony.core.guice.env;

/**
 * asd.
 */
public class SystemPropertyEnvironemntNameResolver implements EnvironmentNameResolver {

	@Override
	public String getEnvironmentProperty() {
		return System.getProperty("environment") != null ? System.getProperty("environment") : "PRODUCTION" ;
	}
}
