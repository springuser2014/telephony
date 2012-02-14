package war.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import war.server.core.configuration.Constant;
import war.server.core.entity.User;

import java.util.List;

@RemoteServiceRelativePath(UserRPCService.PATH)
public interface UserRPCService extends RemoteService {

    public static final String PATH = "user";
    public static final String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;

    List<User> fetchAllUsers();
}
