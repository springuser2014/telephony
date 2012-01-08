package war.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("Greeting")
public interface GreetingService extends RemoteService {

    String greetServer() throws IllegalArgumentException;
}
