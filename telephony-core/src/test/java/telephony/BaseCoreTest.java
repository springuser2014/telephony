package telephony;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.google.inject.persist.PersistService;


/**
 * Do not annotate this class as @RunWith!
 */
public class BaseCoreTest {    	

	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseCoreTest.class);

    private static Injector injector;
    
    private static PersistService persistService;
    
	/**
	 * Initialize Guice-managed tests classes members.
	 */
	@Before
	public void initializer() {
		getInjector().injectMembers(this);
	}

	/**
	 * Gets injector instance.
	 * @return tests injector.
	 */
	public static Injector getInjector() {
		return injector;
	}

	/**
	 * Sets injector instance.
	 * @param injector tests instance.
	 */
	public static void setInjector(Injector injector) {
		BaseCoreTest.injector = injector;
	}

	/**
	 * Sets JPA Persistence service.
	 * @return persistence service.
	 */
	public static PersistService getPersistService() {
		return persistService;
	}

	/**
	 * Gets JPA Persistence service.
	 * @param persistService persistence service.
	 */
	public static void setPersistService(PersistService persistService) {
		BaseCoreTest.persistService = persistService;
	}

}
