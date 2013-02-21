package telephony;

import static org.junit.Assert.assertTrue;

import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import telephony.core.dao.interfaces.UsersDao;
import telephony.core.guice.TelephonyCoreServicesModule;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@RunWith(JukitoRunner.class)
public class FirstTest {

    @Inject
    private UsersDao usersDao;

    private Injector injector;

    /**
     * asd.
     */
    @Before
    public void pre() {
        injector = Guice.createInjector(
                new JpaPersistModule(ConfigTests.PERSISTENCE_TEST),
                new TelephonyCoreServicesModule()
        );

        injector.injectMembers(this);
    }

    /**
     * asd.
     * @param service asd.
     */
    @Inject
    public void initializer(PersistService service) {
        service.start();
    }

    /**
     * asd.
     */
    @Test
    public void asd() {

        usersDao.findAll();

        assertTrue(true);
    }


}
