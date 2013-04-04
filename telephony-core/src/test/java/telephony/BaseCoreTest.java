package telephony;

import java.util.List;

import org.junit.Before;

import telephony.core.guice.env.SystemPropertyEnvironemntNameResolver;
import telephony.core.guice.env.TelephonyCoreEnvironmentResolver;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.googlecode.flyway.core.Flyway;


/**
 * asd.
 * 
 * Please do not not annotate this class as @RunWith
 */
public class BaseCoreTest {
    
    @Inject 
    private Flyway migrator;

    private Injector injector;

    /**
     * asd.
     */
    @Before
    public void pre() {

    	List<AbstractModule> modules = new TelephonyCoreEnvironmentResolver()
    		.resolveWith(
    				new SystemPropertyEnvironemntNameResolver()
    		);
    	
        injector = Guice.createInjector(modules);
        injector.injectMembers(this);
    }
    

    /**
     * asd.
     * @param service asd.
     */
    @Inject
    public void initializer(PersistService service) {
        service.start();
    }
}
