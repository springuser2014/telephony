package war.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import war.server.core.entity.User;

import java.util.List;

@RemoteServiceRelativePath("Greeting")
public interface GreetingService extends RemoteService {

    List<User> greetServer() throws IllegalArgumentException;
}
