package telephony.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.data.TestData;
import telephony.core.entity.jpa.User;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;
import telephony.core.util.StringGenerator;
import telephony.core.util.StringGeneratorImpl;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class UserServiceTest extends BaseCoreTest {
	
	@Inject
	private UserService userService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testUpdate() throws SessionServiceException, UserServiceException {

		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		User userToEdit = userService.findByName(TestData.USER2_NAME);
		String newSessionId = new StringGeneratorImpl().nextSessionId();	
		
		assertTrue("the old sessionId and the new one should be different", 
				!newSessionId.equals(userToEdit.getSessionId()));
		
		// when
		userToEdit.setSessionId(newSessionId);
		userService.updateUser(username, sessionId, userToEdit);
		
		// then
		User userAfterEdit = userService.findByName(TestData.USER2_NAME);
		
		assertTrue("after edit sessionId should be changed",
				userAfterEdit.getSessionId().equals(newSessionId));
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration" , "db/data" })
	public void testAddingNewUser() throws SessionServiceException, UserServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		User creator = userService.findByName(TestData.USER1_NAME);
		User user = new User();
		user.setId(5L);
		user.setEmail("any@mail.com");
		user.setPassword("somePa$$word");
		user.setCreator(creator);
		user.setCreatedAt(new Date());
		user.setSessionId(null);
		user.setSessionValidity(null);
		
		// when
		User addedUser = userService.addUser(username, sessionId, user);
		
		// then
		assertTrue("Should create and return a new user", addedUser != null);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration" , "db/data" })
	public void testDeletingUser() 
			throws SessionServiceException, UserServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		User user = userService.findByName(TestData.USER4_NAME);
		long countAfter, countBefore = userService.count();
		
		// when
		userService.deleteUserById(username, sessionId, user);		
		countAfter = userService.count();
		
		// then
		assertTrue("Should decreased number of users ", countBefore - countAfter == 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testFindingAllUsers() 
			throws SessionServiceException {

		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		
		// when
		List<User> lst = userService.findAllUsers(username, sessionId);
		
		// then
		assertTrue("Should find all 4 users", lst.size() == 4);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testFindingUsersByStoreId() 
			throws SessionServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		long storeId = 2L; // TODO : change 'byStore' not 'byStoreId'
		
		// when
		List<User> lst = userService.findUsersByStoreId(username, sessionId, storeId);
		
		// then
		assertTrue("asd", lst.size() == 2);
	}	
	
}


