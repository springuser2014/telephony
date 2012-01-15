package war.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import war.shared.LoginResult;

@RemoteServiceRelativePath("security")
public interface SecurityService extends RemoteService {

    LoginResult login(String username, String password, boolean rememberMe);

    void logout();

}
