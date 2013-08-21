package telephony.ws.resource;

import java.util.List;

import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.guice.env.SystemPropertyEnvironmentNameResolver;
import telephony.ws.guice.env.TelephonyWebServicesEnvironmentResolver;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public abstract class TelephonyServerResource extends ServerResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static boolean persistServiceInitialized = false;
	
	/**
	 * asd. 
	 * @return asd.
	 */
	public static boolean isPersistServiceInitialized() {
		return persistServiceInitialized;
	}

	/**
	 * asd.
	 * @param persistServiceInitialized asd.
	 */
	public static void setPersistServiceInitialized(
			boolean persistServiceInitialized) {
		TelephonyServerResource.persistServiceInitialized = persistServiceInitialized;
	}

	/**
	 * asd.
	 */
	public TelephonyServerResource() {
		
		logger.info("Loading Guice modules..");
		
		List<AbstractModule> modules = new TelephonyWebServicesEnvironmentResolver()
		.resolveWith(
				new SystemPropertyEnvironmentNameResolver()
		);
		
		for (AbstractModule am : modules) {
			logger.info("Defined module : " + am.getClass().getName());
		}
		
		Injector inj = Guice.createInjector(modules);
		
		inj.injectMembers(this);
	}
	
    /**
     * asd.
     * @param persistService asd.
     *  
     */
    @Inject
	protected void init(final PersistService persistService) {
    	
    	if (!isPersistServiceInitialized()) {
    		persistService.start();
    		setPersistServiceInitialized(true);
    	}
    }
}
