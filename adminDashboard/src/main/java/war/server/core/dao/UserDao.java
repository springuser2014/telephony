package war.server.core.dao;


import war.server.core.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {


//    Injector injector = Guice.createInjector(new TelephonyServerModule());

    public UserDao() {
//        PersistentBeanManager manager = injector.getInstance(PersistentBeanManager.class);
    }

    public List<User> findAll() {
        return new ArrayList<User>();
    }
}

