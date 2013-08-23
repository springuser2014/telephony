package telephony.ws.guice;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.TelephonyApplication;
import telephony.ws.servlet.TelephonyRestletServlet;

import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.ServletModule;


/**
 * Configuration of web applactions specifis modules : GuicePersist, GuiceServlet
 */
public class TelephonyServletModule extends ServletModule {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void configureServlets() {

        logger.debug("TelephonyServletModule initialization");

        bindServlets();
        bindLogger();

        logger.debug("TelephonyServletModule is destroyed");
    }

    // TODO: remove, it doesnt work
    /**
     * asd.
     */
    protected void bindLogger() {
        bindListener(Matchers.any(), new SLF4JTypeListener());
    }


    /**
     * asd.
     */
    private void bindServlets() {

        logger.debug("TelephonyServletModule starts configuring Restlet services");

        bind(org.restlet.Application.class).to(TelephonyApplication.class);

        Map<String, String> restparams = bindRestletParams();
        serve("/rest/*").with(TelephonyRestletServlet.class, restparams);

        logger.debug("TelephonyServletModule ends configuring servlets");
    }

    /**
     * asd.
     * @return asd.
     */
    private Map<String, String> bindRestletParams() {
        Map<String, String> options = new HashMap<String, String>();
        options.put("org.restlet.application", TelephonyApplication.class.toString());
        options.put("org.restlet.clients", "HTTP");
        
        return options;
    }
}
