package war.server.listeners;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.server.guice.TelephonyServerModule;
import war.server.guice.TelephonyServletModule;


/**
 * Defining which guice's modules are used in application
 */
public class TelephonyServletContextListener extends GuiceServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Injector getInjector() {
        logger.debug("Injector configuration");

        return Guice.createInjector(
                new TelephonyServletModule(),
                new TelephonyServerModule()
        );
    }
}
