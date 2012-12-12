package telephony.gwt.server.guice.listener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.gwt.server.guice.module.HandlersModule;
import telephony.gwt.server.guice.module.TelephonyServerModule;
import telephony.gwt.server.guice.module.TelephonyServletModule;


/**
 * Defining which guice's modules are used in application
 */
public class TelephonyServletContextListener extends GuiceServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Injector getInjector() {
        logger.debug("Injector configuration");

        return Guice.createInjector(
                new TelephonyServerModule(),
                new TelephonyServletModule(),

                new HandlersModule()
        );
    }
}
