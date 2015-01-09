package telephony.ws.servlet;

import org.restlet.Application;
import org.restlet.Context;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * asd.
 */
@SuppressWarnings("serial")
@Singleton
public class TelephonyRestletServlet extends org.restlet.ext.servlet.ServerServlet {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private Injector injector;

    /**
     * asd.
     * @param parentContext asd.
     * @return asd.
     */
    @Override
    protected Application createApplication(Context parentContext) {
        Application app = injector.getInstance(Application.class);

        logger.info("Application class impl");
        logger.info(app.getClass().toString());

        app.setContext(parentContext.createChildContext());

        return app;
    }

}
