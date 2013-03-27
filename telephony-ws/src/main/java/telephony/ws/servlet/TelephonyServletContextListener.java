package telephony.ws.servlet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.guice.env.SystemPropertyEnvironemntNameResolver;
import telephony.ws.guice.env.TelephonyWebServicesEnvironmentResolver;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;


/**
 * Defining which guice's modules are used in application.
 */
public class TelephonyServletContextListener extends GuiceServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Injector getInjector() {
        logger.info("Injector configuration");
        
        List<AbstractModule> abstractModules = new TelephonyWebServicesEnvironmentResolver()
        	.resolveWith(
        		new SystemPropertyEnvironemntNameResolver()
        );
        
        return Guice.createInjector(abstractModules);
    }
}
