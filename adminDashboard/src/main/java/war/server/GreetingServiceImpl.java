package war.server;


import com.google.inject.Inject;
import war.client.GreetingService;
import war.server.guice.TestClass;

@SuppressWarnings("serial")
public class GreetingServiceImpl implements GreetingService {

    TestClass tc;

    @Inject
    public GreetingServiceImpl(TestClass tc) {
        this.tc = tc;

    }

    public String greetServer() throws IllegalArgumentException {

       return tc.getName();
    }
}
