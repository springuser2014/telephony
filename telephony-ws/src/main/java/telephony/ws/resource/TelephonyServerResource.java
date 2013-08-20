package telephony.ws.resource;

import java.util.List;

import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.guice.env.SystemPropertyEnvironemntNameResolver;
import telephony.core.guice.env.TelephonyCoreEnvironmentResolver;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public abstract class TelephonyServerResource extends ServerResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * asd.
	 */
	public TelephonyServerResource() {
		
		logger.info("Loading Guice modules..");
		
		List<AbstractModule> modules = new TelephonyCoreEnvironmentResolver()
		.resolveWith(
				new SystemPropertyEnvironemntNameResolver()
		);
		
		for (AbstractModule am : modules) {
			logger.info("Defined module : " + am.getClass().getName());
		}
		
		Injector inj = Guice.createInjector(modules);
		
		inj.injectMembers(this);
	}

}
