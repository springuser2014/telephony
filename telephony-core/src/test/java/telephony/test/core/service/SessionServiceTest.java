package telephony.test.core.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.core.service.dto.request.SessionDetailsRequest;
import telephony.core.service.dto.response.SessionDetailsResponse;
import telephony.test.BaseCoreTest;
import telephony.core.service.SessionService;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.core.data.TestData;
import telephony.core.service.dto.SessionDto;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class SessionServiceTest extends BaseCoreTest {
		
	@Inject
	private SessionService sessionService;
	
	private SessionService sessionServiceMock;

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void sessionInitialization() throws SessionServiceException {
		
		// given 
		String username = TestData.USER_BOSS_NAME;
		String password = TestData.USER_BOSS_PASSWORD;
		
		// when
		sessionService.setSessionValidity(30 * 60 * 1000); // TODO move to setup method
		SessionDto session = sessionService.init(username, password);
		
		// then
		assertNotNull("For exisiting user should return session object", session);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void sessionRefreshing() throws SessionServiceException {

		// given 
		String username = TestData.USER_MANAGER_NAME;
		String password = TestData.USER_MANAGER_PASSWORD;
		sessionService.setSessionValidity(30 * 60 * 1000); // TODO move to setup method
		SessionDto sessionToRefresh = sessionService.init(username, password);		
		
		// when 
		SessionDto refreshedSession = sessionService.refresh(sessionToRefresh);
		
		// then
		boolean isValidAfterRefresh = 
				sessionToRefresh
				.getValidity()
				.before(refreshedSession.getValidity());
		assertTrue("Refreshed session should have longer lifetime", isValidAfterRefresh);		
	}
	
	/**
	 * asd.
	 * @throws SessionServiceException 
	 */
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void sessionInitializationMockTest() throws SessionServiceException {

		// given
		String username = TestData.USER1_NAME;
		String password = TestData.USER1_PASSWORD;
		String sessionId = TestData.randomSessionId();
		Date validity = new Date();
		
		// TODO: move mock definition to *Module
		this.sessionServiceMock = mock(SessionService.class);		
		when(this.sessionServiceMock.init(username , password))
			.thenReturn(new SessionDto(username, sessionId, validity));
		
		// when
		SessionDto session = this.sessionServiceMock.init(username, password);
				
		// then
		assertNotNull("Should return initialized session's object", session);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void sessionValidation1() throws SessionServiceException {
		
		// given 
		String username = TestData.USER1_NAME;
		String password = TestData.USER1_PASSWORD;
		
		// when 
		SessionDto sessionToValidate = sessionService.init(username, password);	
		boolean validated = sessionService.validate(sessionToValidate);
		
		// then
		assertTrue("should return true for being session", validated);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void sessionValidation2() throws SessionServiceException {
		
		// given 
		String username = TestData.USER2_NAME;
		String password = TestData.USER2_PASSWORD;
		
		sessionService.setSessionValidity(new Integer(-60 * 60 * 24)); // TODO move to setup method
		SessionDto sessionToValidate = sessionService.init(username, password);		
				
		// when 
		boolean validated = sessionService.validate(sessionToValidate);
		
		// then
		assertFalse("Should return false for expired session", validated);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void sessionDestroying() throws SessionServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String password = TestData.USER1_PASSWORD;
		sessionService.setSessionValidity(new Integer(30 * 60 * 1000));
		SessionDto sessionToDelete = sessionService.init(username, password);
		
		// when
		boolean destroyed = sessionService.destroy(sessionToDelete);
		
		// then
		assertTrue("Should destroy active session", destroyed);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void sessionDetailsFetching() throws SessionServiceException {

		// given
		String username = TestData.USER1_NAME;
		String password = TestData.USER1_PASSWORD;
		sessionService.setSessionValidity(new Integer(30 * 60 * 1000)); // TODO move to setup method
		SessionDto sessionToDelete = sessionService.init(username, password);
		SessionDetailsRequest detailsRequest = new SessionDetailsRequest(sessionToDelete);

		// when
		SessionDetailsResponse resp = sessionService.fetchDetails(detailsRequest);

		// then
		assertNotNull("Should destroy active session", resp);
		assertTrue(resp.isSuccess());
		assertEquals(resp.getDetails().getRoles().get(0).getLabel(), "salesman");
	}
}

