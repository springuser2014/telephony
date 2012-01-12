package war.server;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import war.client.GreetingService;
import war.server.guice.GuiceRemoteServiceServlet;
import war.server.guice.TestClass;

@SuppressWarnings("serial")
@Singleton
public class GreetingServiceImpl extends GuiceRemoteServiceServlet implements GreetingService {

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
