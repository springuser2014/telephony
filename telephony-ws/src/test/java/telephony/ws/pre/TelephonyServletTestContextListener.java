package telephony.ws.pre;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.guice.env.EnvironmentNameResolver;
import telephony.core.guice.env.SystemPropertyEnvironmentNameResolver;
import telephony.core.guice.env.TelephonyCoreEnvironmentResolver;
import telephony.ws.guice.env.TelephonyWebServicesEnvironmentResolver;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;


/**
 * Defining which guice's modules are used in application.
 */
public class TelephonyServletTestContextListener extends GuiceServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Injector getInjector() {
        logger.info("Injector configuration");
        
        List<AbstractModule> abstractModules = new TelephonyWebServicesEnvironmentResolver()
        	.resolveWith(
        		new EnvironmentNameResolver() {
					
					@Override
					public String getEnvironmentProperty() {
						return "TEST";
					}
				}
        );
        
        for (AbstractModule am : abstractModules) {
        	logger.info("Defined module : " + am.getClass().getName());
        }
        
        return Guice.createInjector(abstractModules);
    }
}
