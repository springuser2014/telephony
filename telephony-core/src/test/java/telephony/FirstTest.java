package telephony;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import telephony.core.dao.interfaces.UsersDao;
import telephony.core.guice.TelephonyCoreServicesModule;

import static org.junit.Assert.assertTrue;

@RunWith(JukitoRunner.class)
public class FirstTest {

    @Inject
    private UsersDao usersDao;

    Injector injector;

    @Before
    public void pre() {
        injector = Guice.createInjector(
                new JpaPersistModule(ConfigTests.PERSISTENCE_TEST),
                new TelephonyCoreServicesModule()
        );

        injector.injectMembers(this);
    }

    @Inject
    public void initializer(PersistService service) {
        service.start();
    }


    @Test
    public void asd() {

        usersDao.findAll();

        assertTrue(true);
    }


}
