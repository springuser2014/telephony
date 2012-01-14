package war.server.guice.module;

import com.google.inject.servlet.ServletModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.client.service.GreetingService;
import war.server.GreetingServiceImpl;
import war.server.core.configuration.Constant;


/**
 * Configuration of web applactions specifis modules : GuicePersist, GuiceServlet
 */
public class TelephonyServletModule extends ServletModule {

    private Logger logger = LoggerFactory.getLogger(getClass());

    protected void configureServlets() {

        logger.debug("TelephonyServletModule initialization");

        bindJpa();
        bindServlets();
        bindAuth();

        logger.debug("TelephonyServletModule is destroyed");
    }

    private void bindAuth() {

        // Authentication module
//        filter("/*").through(IniShiroFilter.class);
    }

    private void bindServlets() {

        logger.debug("TelephonyServletModule starts configuring servlets");

        serve("/adminDashboard/Greeting").with(GreetingServiceImpl.class);
        bind(GreetingService.class).to(GreetingServiceImpl.class);


        logger.debug("TelephonyServletModule ends configuring servlets");
    }

    private void bindJpa() {
        logger.debug("TelephonyServletModule starts configuring JPA module");

        install(new JPAModule(Constant.PERSISTENCE_UNIT_NAME));

        logger.debug("TelephonyServletModule ends configuring servlets");
    }
}
