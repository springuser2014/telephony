package war.server.guice;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import war.client.GreetingService;
import war.server.GreetingServiceImpl;
import war.server.core.configuration.Constant;


public class TelephonyServletModule extends ServletModule {

    protected void configureServlets() {

        bindJpa();
        bindServlets();
    }

    private void bindServlets() {
        serve("/adminDashboard/Greeting").with(GuiceRemoteServiceServlet.class);
        bind(GreetingService.class).to(GreetingServiceImpl.class);
    }

    private void bindJpa() {

        install(new JpaPersistModule(Constant.PERSISTENCE_UNIT_NAME));
//        bind(GuiceDaoInitializer.class).asEagerSingleton();
        filter("/*").through(PersistFilter.class);
    }
}
