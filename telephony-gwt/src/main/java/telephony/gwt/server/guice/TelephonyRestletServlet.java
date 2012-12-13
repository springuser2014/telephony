package telephony.gwt.server.guice;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.restlet.Application;
import org.restlet.Context;

@Singleton
public class TelephonyRestletServlet extends org.restlet.ext.servlet.ServerServlet {

    @Inject
    private Injector injector;


    protected Application createApplication(Context parentContext) {
        Application app = injector.getInstance(Application.class);

        app.setContext(parentContext.createChildContext());

        return app;
    }

}
