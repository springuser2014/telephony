package war.server;


import com.google.inject.Inject;
import war.client.GreetingService;
import war.server.guice.TestClass;

@SuppressWarnings("serial")
public class GreetingServiceImpl implements GreetingService {

//    Injector injector = Guice.createInjector(new TelephonyServerModule());

    TestClass tc;

    @Inject
    public GreetingServiceImpl(TestClass tc) {
        this.tc = tc;
//        PersistentBeanManager manager = injector.getInstance(PersistentBeanManager.class);
//        setBeanManager(manager);
    }

    public String greetServer() throws IllegalArgumentException {

       return tc.getName();
    }
}
