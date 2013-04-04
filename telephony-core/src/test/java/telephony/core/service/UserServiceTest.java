package telephony.core.service;

import static org.junit.Assert.assertEquals;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import telephony.BaseCoreTest;

import com.google.inject.Inject;


/**
 * asd.
 */
@RunWith(JukitoRunner.class)
public class UserServiceTest extends BaseCoreTest {

    @Inject
    private UserService userService;
    
    /**
     * asd2.
     */
    @Test
    public void asd2() {

    	assertEquals(userService.findAllUsers().size() , 4);        
    }
    
    @Test
	public void asd() {
		assertEquals(false, false);
	}


}
