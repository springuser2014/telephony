package telephony.ws.guice.env;

import java.util.Arrays;
import java.util.List;

import telephony.core.guice.env.TelephonyCoreEnvironmentResolver;

import com.google.inject.AbstractModule;

/**
 * asd.
 */
public class TelephonyWebServicesEnvironmentResolver extends TelephonyCoreEnvironmentResolver {
	
	/**
	 * asd.
	 * @return foo.
	 */
	protected List<AbstractModule> getEnvironments() {
		
		return Arrays.asList(
				new TelephonyWebServicesTestModule(), 
				new TelephonyWebServicesProductionModule()
		);
	}

}
