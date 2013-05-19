package telephony;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;

import telephony.core.guice.env.SystemPropertyEnvironemntNameResolver;
import telephony.core.guice.env.TelephonyCoreEnvironmentResolver;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.googlecode.flyway.core.Flyway;


/**
 * Do not annotate this class as @RunWith!
 */
public class BaseCoreTest {    

    private Injector injector;
    
    protected static PersistService persistService;

    /**
     * Initializes test context and injects beans, services etc.
     */
    @Before
    public void preEveryTest() {

    	List<AbstractModule> modules = new TelephonyCoreEnvironmentResolver()
    		.resolveWith(
    				new SystemPropertyEnvironemntNameResolver()
    		);
    	
        injector = Guice.createInjector(modules);
        injector.injectMembers(this);
    }
    
    /**
     * asd.
     */
    @BeforeClass
    public static void preTests() {
    	Flyway m = CoreTestHelper.migratorProvider();
    	m.clean();
    	m.init();
    	m.migrate();
    }
    
    /**
     * Starts Guice-persist JPA module.
     * @param service asd.
     */
    @Inject
    public void initializer(PersistService service) {
        service.start();
        persistService = service;
    }
}
