package war.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import war.server.core.configuration.Constant;
import war.shared.LoginResult;

@RemoteServiceRelativePath(SecurityRPCService.PATH)
public interface SecurityRPCService extends RemoteService {

    public static final String PATH = "security";
    public static final String FULL_SERVICE_URL = Constant.APPLICATION_URL_PREFIX + PATH;

    LoginResult login(String username, String password, boolean rememberMe);

    void logout();

}
