package telephony.ws.pre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.guice.TelephonyCoreServicesModule;
import telephony.ws.guice.TelephonyServletModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;


/**
 * @author gam3r
 * Defining which guice's modules are used in application
 */
public class TelephonyServletTestContextListener extends GuiceServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Injector getInjector() {
        logger.info("Injector configuration");

        return Guice.createInjector(
                getJpaModule(),
                getCoreServicesModule(),
                getServletModule()
        );
    }

    /**
     * foo bar.
     * @return foo bar.
     */
    protected JpaPersistModule getJpaModule() {
        return new JpaPersistModule(TestsConfig.PERSISTENCE);
    }

    /**
     * foo bar.
     * @return foo bar.
     */
    protected TelephonyCoreServicesModule getCoreServicesModule() {
        return new TelephonyCoreServicesModule();
    }

    /**
     * foo bar.
     * @return foo bar.
     */
    protected TelephonyServletModule getServletModule() {
        return new TelephonyServletModule();
    }
}
