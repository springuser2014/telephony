package war.server;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import war.client.GreetingService;
import war.server.core.dao.IUserDao;
import war.server.core.entity.User;
import war.server.guice.gilead.renewed.GuicePersistentRemoteServiceServlet;

import java.util.List;

@SuppressWarnings("serial")
@Singleton
public class GreetingServiceImpl extends GuicePersistentRemoteServiceServlet implements GreetingService {

    @Inject
    private IUserDao userDao;

    @Inject
    public GreetingServiceImpl(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }

    public List<User> greetServer() throws IllegalArgumentException {

       return userDao.findAll();
    }
}
