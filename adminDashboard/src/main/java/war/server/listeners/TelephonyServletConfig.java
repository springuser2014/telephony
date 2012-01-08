package war.server.listeners;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import war.client.GreetingService;
import war.server.GreetingServiceImpl;
import war.server.guice.GuiceRemoteServiceServlet;
import war.server.guice.TelephonyServerModule;


public class TelephonyServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new TelephonyServerModule(),
                new ServletModule() {

                    @Override
                    protected void configureServlets() {
                        install(new JpaPersistModule("telephony"));

                        serve("/adminDashboard/Greeting").with(GuiceRemoteServiceServlet.class);

                        bind(GreetingService.class).to(GreetingServiceImpl.class);
                        filter("/*").through(PersistFilter.class);
                    }

                });
    }
}
