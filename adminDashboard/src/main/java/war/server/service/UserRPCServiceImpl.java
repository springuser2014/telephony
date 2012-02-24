package war.server.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.client.service.UserRPCService;
import war.server.core.entity.User;
import war.server.core.service.interfaces.UserService;
import war.server.gilead.GuicePersistentRemoteServiceServlet;

import java.util.List;

@SuppressWarnings("serial")
@Singleton
public class UserRPCServiceImpl extends GuicePersistentRemoteServiceServlet implements UserRPCService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private UserService userService;

    @Inject
    public UserRPCServiceImpl(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }

    public List<User> fetchAllUsers() {
        logger.debug("UserRPCServiceImpl.fetchAllUsers starts");

        List<User> res = userService.findAllUsers();

        return res;
    }
}
