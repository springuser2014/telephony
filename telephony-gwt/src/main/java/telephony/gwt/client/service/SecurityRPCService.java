package telephony.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import telephony.core.configuration.Constant;
import telephony.shared.gwtp.result.LoginResult;

/**
 * @todo Remove after migration to REST services
 */
@RemoteServiceRelativePath(SecurityRPCService.PATH)
public interface SecurityRPCService extends RemoteService {

    public static final String PATH = "security";
    public static final String FULL_SERVICE_URL = Constant.APPLICATION_URL_PREFIX + PATH;

    public LoginResult login(String username, String password, boolean rememberMe);

    public void logout();

}
