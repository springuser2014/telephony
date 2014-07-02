package telephony.ws.servlet;

import org.restlet.Application;
import org.restlet.Context;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;


/**
 * asd.
 */
@SuppressWarnings("serial")
@Singleton
public class TelephonyRestletServlet extends org.restlet.ext.servlet.ServerServlet {

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

        app.setContext(parentContext.createChildContext());

        return app;
    }

}
