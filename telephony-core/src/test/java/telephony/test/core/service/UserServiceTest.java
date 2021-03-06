package telephony.test.core.service;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import telephony.core.query.filter.UserFilterCriteria;
import telephony.core.query.filter.UserFilterCriteriaBuilder;
import telephony.core.service.RoleService;
import telephony.core.service.StoreService;
import telephony.core.service.UserService;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.UserAddDto;
import telephony.core.service.dto.UserEditDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;
import telephony.test.BaseCoreTest;
import telephony.test.core.data.TestData;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class UserServiceTest extends BaseCoreTest {
	
	@Inject
	UserService userService;
	
	@Inject
	RoleService roleService;

	@Inject
	StoreService storeService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void edit1() throws SessionServiceException, UserServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		UserEditRequest editReq = new UserEditRequest(session);
		UserEditDto editDto = new UserEditDto();
		editDto.setId(TestData.USER2_ID);
		editDto.setIsActive(true);
		editDto.addRoleToAdd(TestData.ROLE_SALESMAN_ID);
		editDto.addRoleToRemove(TestData.ROLE_BOSS_ID);
		editDto.setEmail("new@gmail.com");
		editReq.setUserDto(editDto);

		// when
		UserEditResponse editResp = userService.edit(editReq);

		// then
		assertNotNull(editResp);
		assertTrue(editResp.isSuccess());
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration" , "db/data" })
	public void addingNewUser() throws SessionServiceException, UserServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		UserAddDto dto = new UserAddDto();
		dto.setEmail("any@mail.com");
		dto.setPassword1("somePa$$word");
		dto.setSessionId(null);
		dto.setSessionValidity(null);
		dto.setIsActive(true);
		dto.addRoleToAdd(TestData.ROLE_SALESMAN_ID);
		UserAddRequest request = new UserAddRequest(session);
		request.setUserDto(dto);
		long countBefore = userService.count(session);

		// when
		UserAddResponse resp = userService.add(request);
		long countAfter = userService.count(session);
		
		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
		assertEquals(countAfter - countBefore, 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration" , "db/data" })
	public void deletingUser1()
			throws SessionServiceException, UserServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		UserDeleteRequest request = new UserDeleteRequest(session);
		request.setUserId(TestData.USER1_ID);
		long countBefore = userService.count(session);
		
		// when
		UserDeleteResponse deleteResponse = userService.delete(request);
		long countAfter = userService.count(session);

		// then
		assertTrue(deleteResponse.isSuccess());
		assertEquals(countBefore - countAfter, 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingAllUsers()
			throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		UserFilterCriteria filters = UserFilterCriteriaBuilder.userFilterCriteria()
				.withPage(0).withPerPage(100)
				.build();
		UsersFetchRequest fetchRequest = new UsersFetchRequest(session);
		fetchRequest.setFilters(filters);

		// when
		UsersFetchResponse fetchResponse = userService.fetch(fetchRequest);
		
		// then
		assertNotNull(fetchResponse);
		assertEquals("Should fetch all 4 users", fetchResponse.getUsers().size(), 4);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void editingRoles() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		UserEditRoleRequest editRoleRequest = new UserEditRoleRequest(session);
		editRoleRequest.addRoleToAdd(TestData.ROLE_BOSS_ID);
		editRoleRequest.addRoleToAdd(TestData.ROLE_SHOP_MANAGER_ID);
		editRoleRequest.addRoleToDelete(TestData.ROLE_SALESMAN_ID);
		editRoleRequest.setUserId(TestData.USER1_ID);

		// when
		UserEditRoleResponse editRoleResponse = userService.editRoles(editRoleRequest);

		// then
		assertNotNull(editRoleResponse);
		assertTrue(editRoleResponse.isSuccess());
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void editingStores() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		UserEditStoreRequest editStoreRequest = new UserEditStoreRequest(session);
		editStoreRequest.addStoreToAdd(TestData.STORE2_ID);
		editStoreRequest.addStoreToDelete(TestData.STORE1_ID);
		editStoreRequest.setUserId(TestData.USER_BOSS_ID);

		// when
		UserEditStoreResponse editStoreResponse = userService.editStores(editStoreRequest);

		// then
		assertNotNull(editStoreResponse);
		assertTrue(editStoreResponse.isSuccess());
	}
}
