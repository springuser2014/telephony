package telephony;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import telephony.core.dao.UsersDao;
import telephony.core.guice.env.SystemPropertyEnvironemntNameResolver;
import telephony.core.guice.env.TelephonyCoreEnvironmentResolver;
import telephony.core.service.UserService;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;


/**
 * asd.
 */
@RunWith(JukitoRunner.class)
public class FirstTest {

    @Inject
    private UsersDao usersDao;
    
    @Inject
    private UserService userService;

    private Injector injector;

    /**
     * asd.
     */
    @Before
    public void pre() {

    	List<AbstractModule> modules = new TelephonyCoreEnvironmentResolver()
    		.resolveWith(
    				new SystemPropertyEnvironemntNameResolver()
    		);
    	
        injector = Guice.createInjector(modules);
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

        usersDao.find();

        assertTrue(true);
    }

    /**
     * asd2.
     */
    @Test
    public void asd2() {

    	assertEquals(userService.findAllUsers().size() , 4);        
    }


}
