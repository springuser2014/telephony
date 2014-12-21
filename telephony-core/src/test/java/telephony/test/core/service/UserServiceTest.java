package telephony.test.core.service;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import telephony.core.service.dto.UserDto;
import telephony.core.service.dto.request.UserAddRequest;
import telephony.core.service.dto.request.UserDeleteRequest;
import telephony.core.service.dto.request.UserEditRequest;
import telephony.core.service.dto.request.UsersFetchRequest;
import telephony.core.service.dto.response.UserAddResponse;
import telephony.core.service.dto.response.UserDeleteResponse;
import telephony.core.service.dto.response.UserEditResponse;
import telephony.core.service.dto.response.UsersFetchResponse;
import telephony.test.BaseCoreTest;
import telephony.core.service.RoleService;
import telephony.core.service.StoreService;
import telephony.core.service.UserService;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.core.data.TestData;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.query.filter.*;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.exception.UserServiceException;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

import javax.persistence.PersistenceException;

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
	public void update1() throws SessionServiceException, UserServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		UsersFetchRequest fetchReq = new UsersFetchRequest(session);
		UserFilterCriteria filters = UserFilterCriteriaBuilder.userFilterCriteria()
				.withEmail(TestData.USER2_NAME).build();
		fetchReq.setFilters(filters);
		UsersFetchResponse fetchResp = userService.fetch(fetchReq);

		// when
		UserDto dto = fetchResp.getUsers().get(0);
		dto.setIsActive(false);
		UserEditRequest editReq = new UserEditRequest(session);
		editReq.setUserDto(dto);
		UserEditResponse editResp = userService.edit(editReq);
		fetchResp = userService.fetch(fetchReq);
		
		// then
		assertNotNull(editResp);
		assertTrue(editResp.isSuccess());
		assertFalse(fetchResp.getUsers().get(0).getIsActive());
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration" , "db/data" })
	public void addingNewUser() throws SessionServiceException, UserServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		UserDto dto = new UserDto();
		dto.setEmail("any@mail.com");
		dto.setPassword("somePa$$word");
		dto.setSessionId(null);
		dto.setSessionValidity(null);
		dto.setIsActive(true);
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

	@Test(expected = PersistenceException.class)
	@FlywayTest(locationsForMigrate = { "db/migration" , "db/data" })
	public void deletingUser1()
			throws SessionServiceException, UserServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		UserDeleteRequest request = new UserDeleteRequest(session);
		request.setUserId(TestData.USER1_ID);
		
		// when
		UserDeleteResponse deleteResponse = userService.delete(request);

		// then
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingAllUsers()
			throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		UserFilterCriteria filters = UserFilterCriteriaBuilder.userFilterCriteria().build();
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
	public void editingRoles() {
		assertTrue(true); // TODO
	}


}
