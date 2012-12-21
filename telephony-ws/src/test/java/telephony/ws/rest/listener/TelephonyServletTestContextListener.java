package telephony.ws.rest.listener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.guice.TelephonyCoreServicesModule;
import telephony.ws.Config;
import telephony.ws.guice.TelephonyServletModule;


/**
 * Defining which guice's modules are used in application
 */
public class TelephonyServletTestContextListener extends GuiceServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Injector getInjector() {
        logger.debug("Injector configuration");

        return Guice.createInjector(
                getJpaModule(),
                getCoreServicesModule(),
                getServletModule()
        );
    }

    protected JpaPersistModule getJpaModule() {
        return new JpaPersistModule(Config.PERSISTENCE);
    }

    protected TelephonyCoreServicesModule getCoreServicesModule() {
        return new TelephonyCoreServicesModule();
    }

    protected TelephonyServletModule getServletModule() {
        return new TelephonyServletModule();
    }
}
