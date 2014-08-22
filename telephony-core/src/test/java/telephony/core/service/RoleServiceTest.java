package telephony.core.service;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

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
import telephony.core.query.filter.RoleFilterCriteria;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;

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
public class RoleServiceTest extends BaseCoreTest {
	
	
	@Inject
	private RoleService roleService;

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchAllRoles() throws SessionServiceException {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		RoleFilterCriteria filters = RoleFilterCriteria.create();
		
		// when
		List<Role> roles = roleService.find(session, filters);
		
		// then
		assertTrue("should return 3 roles", roles.size() == 3);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void addingNewRole() throws SessionServiceException, RoleServiceException {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		RoleFilterCriteria filters = RoleFilterCriteria.create();
		
		Role newRole = new Role();
		newRole.setName("nowa rola");
		newRole.setStore(new HashSet<Store>());
		newRole.setUsers(new HashSet<User>());
		
		// when
		List<Role> lstBefore = roleService.find(session, filters);
		roleService.add(session, newRole);
		
		// then
		List<Role> lstAfter = roleService.find(session, filters);
		assertTrue("should return new role", lstAfter.size() - lstBefore.size() == 1);
	}
	

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void deletingExisitingRole() throws SessionServiceException, RoleServiceException {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		Role roleToDelete = roleService.findByLabel(session, "salesman");
		long countBefore = roleService.count(session);
		
		// when
		roleService.remove(session, roleToDelete);
		long countAfter = roleService.count(session);
		
		// then
		assertTrue("should delete given role", countBefore - countAfter == 1);
	}
}
