package war.server.guice;


import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Guice DI module configuration
 */
public class TelephonyServerModule extends AbstractModule {
    @Override
    protected void configure() {


        bind(TestClass.class).in(Singleton.class);

//        bind(PersistentBeanManager.class).toProvider(PersistentBeanManagerProvider.class);
    }
}
