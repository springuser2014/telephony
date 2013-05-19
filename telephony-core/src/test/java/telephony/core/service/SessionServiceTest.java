package telephony.core.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.jukito.JukitoRunner;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import telephony.BaseCoreTest;
import telephony.core.dao.UsersDao;
import telephony.core.data.SessionServiceTestData;
import telephony.core.service.bean.Session;

import com.google.inject.Inject;

/**
 * asd.
 * @author pahe
 *
 */
@RunWith(JukitoRunner.class)
public class SessionServiceTest extends BaseCoreTest {
	
	@Inject
	private SessionService sessionService;
	
	private SessionService sessionServiceMock;
	
	private UsersDao usersDaoMock;
	
	@AfterClass
	public static void postTest() {
		persistService.stop();
	}
	
	/**
	 * asd.
	 */
	@Test
	public void sessionInitializationAndDestroying() {
		
		// given 
		String username = SessionServiceTestData.USER3_NAME;
		String password = SessionServiceTestData.USER3_PASSWORD;
		
		// when
		Session session = sessionService.init(username, password);
		
		// then
		assertNotNull(session);
	}
	
	/**
	 * asd.
	 */
	@Test
	public void sessionRefreshing() {
		
		// given 
		String username = SessionServiceTestData.USER4_NAME;
		String password = SessionServiceTestData.USER4_PASSWORD;
		Session sessionToRefresh = sessionService.init(username, password);
		
		
		// when 
		Session refreshedSession = sessionService.refresh(sessionToRefresh);
		
		// then
		assertNotNull(refreshedSession);		
	}
	
	/**
	 * asd.
	 */
	@Test
	public void sessionInitializationMockTest() {

		// given
		String username = SessionServiceTestData.USER1_NAME;
		String password = SessionServiceTestData.USER1_PASSWORD;
		String sessionId = SessionServiceTestData.randomSessionId();
		
		// TODO: move mock definition to *Module
		this.sessionServiceMock = mock(SessionService.class);		
		when(this.sessionServiceMock.init(username , password))
			.thenReturn(new Session(username, sessionId));
		
		// when
		Session session = this.sessionServiceMock.init(username, password);
				
		// then
		assertNotNull(session);
	}
	
	/**q
	 * asd.
	 */
	@Test
	public void sessionValidation1() {
		
		// given 
		String username = SessionServiceTestData.USER1_NAME;
		String password = SessionServiceTestData.USER1_PASSWORD;
		
		// when 
		Session sessionToValidate = sessionService.init(username, password);	
		boolean validated = sessionService.validate(sessionToValidate);
		
		// then
		assertTrue("should return true for being session", validated);
	}
	
	/**
	 * asd.
	 */
	@Test
	public void sessionValidation2() {
		
		// given 
		String username = SessionServiceTestData.USER2_NAME;
		String password = SessionServiceTestData.USER2_PASSWORD;
		
		sessionService.setSessionValidity(new Integer(-60 * 60 * 24));
		Session sessionToValidate = sessionService.init(username, password);		
				
		// when 
		boolean validated = sessionService.validate(sessionToValidate);
		
		// then
		assertFalse("should return false for expired session", validated);
	}
	


}
