package telephony.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.data.TestData;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.query.filter.StoreFilterCriteria;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;
import telephony.core.util.StringGeneratorImpl;

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
public class UserServiceTest extends BaseCoreTest {
	
	@Inject
	private UserService userService;
	
	@Inject
	private RoleService roleService;

	@Inject
	private StoreService storeService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testUpdate() throws SessionServiceException, UserServiceException {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		User userToEdit = userService.findByName(session, TestData.USER2_NAME);
		String newSessionId = new StringGeneratorImpl().nextSessionId();	
		
		assertTrue("the old sessionId and the new one should be different", 
				!newSessionId.equals(userToEdit.getSessionId()));
		
		// when
		userToEdit.setSessionId(newSessionId);
		userService.updateUser(session, userToEdit);
		
		// then
		User userAfterEdit = userService.findByName(session, TestData.USER2_NAME);
		
		assertTrue("after edit sessionId should be changed",
				userAfterEdit.getSessionId().equals(newSessionId));
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration" , "db/data" })
	public void testAddingNewUser() throws SessionServiceException, UserServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		User user = new User();
		user.setEmail("any@mail.com");
		user.setPassword("somePa$$word");
		user.setSessionId(null);
		user.setSessionValidity(null);
		user.setIsActive(true);
		
		// when
		userService.addUser(session, user);
		
		User addedUser = userService.findByName(session, "any@mail.com");
		
		// then
		assertTrue("Should create and return a new user", addedUser != null);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration" , "db/data" })
	public void testDeletingUser() 
			throws SessionServiceException, UserServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		User user = userService.findByName(session, TestData.USER4_NAME);
		long countAfter = -1, countBefore = userService.count(session);
		
		// when
		userService.deleteUserById(session, user);
		countAfter = userService.count(session);
		
		// then
		assertTrue("Should decreased number of users ", countBefore - countAfter == 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testFindingAllUsers() 
			throws SessionServiceException {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		List<User> lst = userService.find(session);
		
		// then
		assertTrue("Should find all 4 users", lst.size() == 4);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testFindingUsersByStoreId() 
			throws SessionServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long storeId = 2L; // TODO : change 'byStore' not 'byStoreId'
		
		// when
		List<User> lst = userService.findUsersByStoreId(session, storeId);
		
		// then
		assertTrue("asd", lst.size() == 2);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testAddingRolesToUser() 
			throws SessionServiceException, UserServiceException {
	
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String username2 = TestData.USER2_NAME;
				
		User user = userService.findByName(session, username2);
		List<Role> rolesToAdd = roleService.find(session, null);
		userService.getEntityManager().refresh(user);
		
		assertEquals(1, user.getRoles().size());
		// when
		userService.addRoles(session, user, rolesToAdd);
		

		// then
		assertEquals(rolesToAdd.size(), user.getRoles().size());		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testDeleteingRolesFromUser() 
			throws SessionServiceException, UserServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String username2 = TestData.USER2_NAME;
				
		User user = userService.findByName(session, username2);
		user.setRoles(new HashSet<Role>());
		
		// when
		userService.updateUser(session, user);
		
		// then
		User user2 = userService.findByName(session, username2);
		assertEquals(user2.getRoles().size(), 0);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testDeleteingRolesFromUserUsingDeleteRoles() 
			throws SessionServiceException, UserServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String username2 = TestData.USER2_NAME;
		User user = userService.findByName(session, username2);
		Set<Role> rolesToDelete = user.getRoles();
		
		// when
		userService.deleteRoles(session, user, rolesToDelete);
		
		// then
		User user2 = userService.findByName(session, username2);
		assertEquals(user2.getRoles().size(), 0);
	}
	

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testAssigningStoresToUser() 
			throws SessionServiceException, UserServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String username2 = TestData.USER2_NAME;
		StoreFilterCriteria sfc = StoreFilterCriteria.create();
		
		User user = userService.findByName(session, username2);
		List<Store> storesToAdd = storeService.find(session, sfc);
		userService.getEntityManager().refresh(user);
		
		assertEquals(2, user.getAllowedShops().size());
		// when
		userService.addStores(session, user, storesToAdd);
		
		// then
		assertEquals(storesToAdd.size(), user.getAllowedShops().size());
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testRemovingUserFromStore() 
			throws SessionServiceException, UserServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String username2 = TestData.USER2_NAME;
		
		User user = userService.findByName(session, username2);
		user.setAllowedShops(new HashSet<Store>());
		
		// when
		userService.updateUser(session, user);
		
		// then
		User user2 = userService.findByName(null, username2);
		assertEquals(0, user2.getAllowedShops().size());	
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testRemovingUserFromStoreUsingDeleteStores() 
			throws SessionServiceException, UserServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String username2 = TestData.USER2_NAME;
		
		User user = userService.findByName(session, username2);
		Set<Store> storesToDelete = user.getAllowedShops();
		
		// when
		userService.deleteStores(session, user, storesToDelete);
		
		// then
		User user2 = userService.findByName(session, username2);
		assertEquals(0, user2.getAllowedShops().size());	
	}

}
