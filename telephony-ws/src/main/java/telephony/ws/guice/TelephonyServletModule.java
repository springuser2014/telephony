package telephony.ws.guice;


import com.google.inject.servlet.ServletModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.ws.Application;
import telephony.ws.servlet.TelephonyRestletServlet;

import java.util.HashMap;
import java.util.Map;


/**
 * Configuration of web applactions specifis modules : GuicePersist, GuiceServlet
 */
public class TelephonyServletModule extends ServletModule {

    private Logger logger = LoggerFactory.getLogger(getClass());

    protected void configureServlets() {

        logger.debug("TelephonyServletModule initialization");

        bindServlets();
        bindAuth();

        logger.debug("TelephonyServletModule is destroyed");
    }

    private void bindAuth() {

//        filter("/*").through(GuiceShiroFilter.class);
    }

    private void bindServlets() {

        logger.debug("TelephonyServletModule starts configuring Restlet services");

        bind(org.restlet.Application.class).to(Application.class);

        Map<String, String> restparams = bindRestletParams();
        serve("/rest/*").with(TelephonyRestletServlet.class, restparams);

        logger.debug("TelephonyServletModule ends configuring servlets");
    }

    private Map<String, String> bindRestletParams() {
        Map<String, String> options = new HashMap();
        options.put("org.restlet.application", Application.class.toString());

        return options;
    }
}
