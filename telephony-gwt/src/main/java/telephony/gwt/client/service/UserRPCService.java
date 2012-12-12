package telephony.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import telephony.core.configuration.Constant;
import telephony.core.entity.User;

import java.util.List;


/**
 * @todo Remove after migration to REST services
 */
@RemoteServiceRelativePath(UserRPCService.PATH)
public interface UserRPCService extends RemoteService {

    public static final String PATH = "user";
    public static final String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;

    public List<User> fetchAllUsers();
}
