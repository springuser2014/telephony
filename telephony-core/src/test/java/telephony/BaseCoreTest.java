package telephony;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.google.inject.persist.PersistService;


/**
 * Do not annotate this class as @RunWith!
 */
public class BaseCoreTest {    	

	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseCoreTest.class);

    public static Injector injector;
    
    public static PersistService persistService;
    
	/**
	 * www.
	 */
	@Before
	public void initializer() {
		injector.injectMembers(this);
	}

}
