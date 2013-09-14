package telephony.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
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
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class StoreServiceTest extends BaseCoreTest {

	// TODO : move
	private static final String STORE_NEW_LOCATION = "rybnik";

	@Inject
	private StoreService storeService;
	
	@Inject
	private UserService userService;

	@Inject
	private RoleService roleService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testFetchAllStores() throws SessionServiceException {

		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		// when
		List<Store> stores = storeService.fetchAllStores(username, sessionId);
		
		// then
		assertTrue("should return 2 stores", stores.size() == 2);
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testingAddingNewStore() throws SessionServiceException {

		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		List<Store> storesBeforeAdd = storeService.fetchAllStores(username, sessionId);
		Store store = new Store();
		store.setCreatedAt(new Date());
		User creator = userService.findByName(TestData.USER2_NAME);
		store.setCreator(creator);
		store.setLabel("Rybnik");
		long nbBefore = storeService.count();
		
		//store.setUsers(users);
		//store.setRoles(roles);
		
		// when
		Store addedStore = storeService.add(username, sessionId, store);
		List<Store> storesAfterAdd = storeService.fetchAllStores(username, sessionId);
		long nbAfter = storeService.count();
		
		// then
		assertEquals("should return one more store ", storesAfterAdd.size() - storesBeforeAdd.size(), 1);
		assertEquals("check the name of added store", store.getLabel(), addedStore.getLabel());
		assertTrue("should count one more store", nbAfter - nbBefore == 1);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testingEditingExisitingStore() throws SessionServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Store storeToEdit = storeService.findByLabel(username, sessionId, TestData.STORE1_LABEL);
		long countBefore = storeService.count();
		
		// when
		storeToEdit.setLabel(STORE_NEW_LOCATION);
		Store editedStore = storeService.edit(username, sessionId, storeToEdit);
		Store searchEditedStore = storeService.findByLabel(username, sessionId, STORE_NEW_LOCATION);
		long countAfter = storeService.count();
		
		// then
		assertTrue("number of stores shouldn't be changed", countAfter - countBefore == 0);
		assertTrue("a new store is successfuly saved", searchEditedStore != null);
		
	}
	
	// TODO : add editing store users
	

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testingDeletingStore() throws SessionServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Store storeToDelete = storeService.findByLabel(username, sessionId, TestData.STORE1_LABEL);
		long countBefore = storeService.count();
		long countAfter = 0;
		
		// when
		storeService.delete(username, sessionId, storeToDelete);
		countAfter = storeService.count();
		
		// then
		assertTrue("there should be one store less", (countAfter - countBefore) == -1);
	}
	

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testingSettingRequiredRoles() throws SessionServiceException, RoleServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Store store = storeService.findByLabel(username, sessionId, TestData.STORE1_LABEL);
		List<Role> roles = roleService.fetchAll(username, sessionId);
		
		// when
		storeService.setRequiredRoles(username, sessionId, store, roles);
		
		// then
		List<Role> reqRoles = storeService.getRequestRoles(username, sessionId, store);
		
		assertEquals("number of submitted and fetched roles should be the same", roles.size(), reqRoles.size());
		
		assertTrue("all fetched elements should be the same as the submitted ones ", reqRoles.containsAll(roles));
	}	
	
}
