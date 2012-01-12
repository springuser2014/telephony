package war.server.guice.module;


import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import war.server.core.dao.IUserDao;
import war.server.core.dao.UserDao;
import war.server.guice.TestClass;

/**
 * Guice DI module configuration
 */
public class TelephonyServerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TestClass.class).in(Singleton.class);

        bind(IUserDao.class).to(UserDao.class);
    }
}
