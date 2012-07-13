package telephony.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import telephony.server.core.configuration.Constant;
import telephony.shared.LoginResult;

@RemoteServiceRelativePath(SecurityRPCService.PATH)
public interface SecurityRPCService extends RemoteService {

    public static final String PATH = "security";
    public static final String FULL_SERVICE_URL = Constant.APPLICATION_URL_PREFIX + PATH;

    public LoginResult login(String username, String password, boolean rememberMe);

    public void logout();

}
