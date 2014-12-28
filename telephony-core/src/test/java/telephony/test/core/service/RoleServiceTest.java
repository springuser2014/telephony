package telephony.test.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.core.service.dto.RoleDto;
import telephony.core.service.dto.request.RoleAddRequest;
import telephony.core.service.dto.request.RoleDeleteRequest;
import telephony.core.service.dto.request.RoleFetchRequest;
import telephony.core.service.dto.response.RoleAddResponse;
import telephony.core.service.dto.response.RoleDeleteResponse;
import telephony.core.service.dto.response.RoleFetchResponse;
import telephony.test.BaseCoreTest;
import telephony.core.service.RoleService;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.core.data.TestData;
import telephony.core.query.filter.RoleFilterCriteria;
import telephony.core.query.filter.RoleFilterCriteriaBuilder;

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
	public void fetchingAllRoles() throws SessionServiceException, RoleServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		RoleFilterCriteria filters = RoleFilterCriteriaBuilder
				.roleFilterCriteria()
				.build();
		RoleFetchRequest fetchRequest = new RoleFetchRequest(session);
		fetchRequest.setFilters(filters);

		// when
		RoleFetchResponse fetchResponse = roleService.fetch(fetchRequest);

		// then
		assertNotNull(fetchResponse);
		assertEquals("should return 3 roles", fetchResponse.getRoles().size(), 3);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void addRole() throws SessionServiceException, RoleServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		RoleAddRequest request = new RoleAddRequest(session);
		RoleDto dto = new RoleDto();
		dto.setLabel("nowa rola");
		request.setRoleDto(dto);
		long countBefore = roleService.count(session);

		// when
		RoleAddResponse response = roleService.add(request);
		long countAfter = roleService.count(session);

		// then
		assertNotNull(response);
		assertTrue("should return new role", countAfter - countBefore == 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void deletingExistingRole() throws SessionServiceException, RoleServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		RoleDeleteRequest request = new RoleDeleteRequest(session);
		request.setRoleId(TestData.ROLE_SALESMAN_ID);
		long countBefore = roleService.count(session);

		// when
		RoleDeleteResponse resp = roleService.delete(request);
		long countAfter = roleService.count(session);
		
		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
		assertTrue("should delete given role", countBefore - countAfter == 1);
	}
}
