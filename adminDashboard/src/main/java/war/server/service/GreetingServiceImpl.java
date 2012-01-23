package war.server.service;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.client.service.GreetingService;
import war.server.gilead.GuicePersistentRemoteServiceServlet;
import war.server.core.dao.interfaces.UsersDao;
import war.server.core.entity.User;

import java.util.List;

@SuppressWarnings("serial")
@Singleton
public class GreetingServiceImpl extends GuicePersistentRemoteServiceServlet implements GreetingService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private UsersDao userDao;

    @Inject
    public GreetingServiceImpl(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }

    @RequiresRoles("admin")
    public List<User> greetServer() throws IllegalArgumentException {

      logger.debug("greetServer method invoked");

       return userDao.findAll();
    }
}
