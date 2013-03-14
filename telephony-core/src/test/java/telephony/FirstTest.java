package telephony;

import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;

import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import telephony.core.dao.UsersDao;
import telephony.core.guice.TelephonyCoreServicesModule;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.googlecode.flyway.core.Flyway;


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
     * @param ds foo.
     */
    @Inject
    public void asd(DataSource ds) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
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

        usersDao.find();

        assertTrue(true);
    }


}
