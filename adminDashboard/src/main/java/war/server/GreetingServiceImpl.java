package war.server;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import war.client.GreetingService;
import war.server.guice.gilead.renewed.GuicePersistentRemoteServiceServlet;
import war.server.guice.TestClass;

@SuppressWarnings("serial")
@Singleton
public class GreetingServiceImpl extends GuicePersistentRemoteServiceServlet implements GreetingService {

    @Inject
    TestClass tc;

    @Inject
    public GreetingServiceImpl(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }

    public String greetServer() throws IllegalArgumentException {

       return tc.getName();
    }
}
