package telephony.ws.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.guice.TelephonyCoreServicesModule;
import telephony.ws.Config;
import telephony.ws.guice.TelephonyServletModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;


/**
 * @author gam3r
 * Defining which guice's modules are used in application
 */
public class TelephonyServletContextListener extends GuiceServletContextListener {

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

    /**
     * asd.
     * @return asd.
     */
    protected JpaPersistModule getJpaModule() {
        return new JpaPersistModule(Config.PERSISTENCE);
    }

    /**
     * asd.
     * @return asd.
     */
    protected TelephonyCoreServicesModule getCoreServicesModule() {
        return new TelephonyCoreServicesModule();
    }

    /**
     * asd.
     * @return asd.
     */
    protected TelephonyServletModule getServletModule() {
        return new TelephonyServletModule();
    }
}
