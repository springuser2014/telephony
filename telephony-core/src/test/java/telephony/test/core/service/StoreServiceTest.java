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

import telephony.core.service.dto.StoreDto;
import telephony.core.service.dto.request.StoreAddRequest;
import telephony.core.service.dto.request.StoreDeleteRequest;
import telephony.core.service.dto.request.StoreEditRequest;
import telephony.core.service.dto.request.StoreFetchRequest;
import telephony.core.service.dto.response.StoreAddResponse;
import telephony.core.service.dto.response.StoreEditResponse;
import telephony.core.service.dto.response.StoreFetchResponse;
import telephony.core.service.exception.StoreServiceException;
import telephony.test.BaseCoreTest;
import telephony.core.service.RoleService;
import telephony.core.service.StoreService;
import telephony.core.service.UserService;
import telephony.test.core.data.TestData;
import telephony.core.query.filter.StoreFilterCriteria;
import telephony.core.query.filter.StoreFilterCriteriaBuilder;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.exception.SessionServiceException;

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
public class StoreServiceTest extends BaseCoreTest {

	// TODO : move to telephony.test.core.data.TestData
	private static final String STORE_NEW_LABEL = "rybnik";

	@Inject
	private StoreService storeService;
	
	@Inject
	private UserService userService;

	@Inject
	private RoleService roleService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllStores() throws SessionServiceException, StoreServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		StoreFilterCriteria filters = StoreFilterCriteriaBuilder.storeFilterCriteria().build();
		StoreFetchRequest request = new StoreFetchRequest(session);
		request.setFilters(filters);

		// when
		StoreFetchResponse resp = storeService.fetch(request);
		
		// then
		assertNotNull(resp);
		assertEquals(resp.getStores().size(), 2);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void addNewStore1() throws SessionServiceException, StoreServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		StoreFilterCriteria filters = StoreFilterCriteriaBuilder.storeFilterCriteria().build();
		long nbBefore = storeService.count(session);

		// when
		StoreAddRequest reqAdd = new StoreAddRequest(session);
		StoreDto dto = new StoreDto();
		dto.setLabel("Rybnik");
		reqAdd.setStore(dto);
		StoreAddResponse respAdd = storeService.add(reqAdd);
		long nbAfter = storeService.count(session);

		// then
		assertNotNull(respAdd);
		assertTrue(respAdd.isSuccess());
		assertTrue("should count one more store", nbAfter - nbBefore == 1);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void editStore1() throws SessionServiceException, StoreServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		StoreFilterCriteria filters1 = StoreFilterCriteriaBuilder.storeFilterCriteria()
				.withStoreId(TestData.STORE1_ID).build();
		StoreFetchRequest reqFetch = new StoreFetchRequest(session);
		reqFetch.setFilters(filters1);
		StoreFetchResponse respFetch = storeService.fetch(reqFetch);
		long countBefore = storeService.count(session);

		// when
		StoreEditRequest reqEdit = new StoreEditRequest(session);
		StoreDto dto = respFetch.getStores().get(0);
		dto.setLabel(STORE_NEW_LABEL);
		reqEdit.setStoreDto(dto);
		StoreEditResponse respEdit = storeService.edit(reqEdit);

		respFetch = storeService.fetch(reqFetch);
		long countAfter = storeService.count(session);
		
		// then
		assertNotNull(respEdit);
		assertTrue(respEdit.isSuccess());

		assertEquals(respFetch.getStores().get(0).getLabel(), STORE_NEW_LABEL);
		assertTrue(countAfter - countBefore == 0);
	}

	@Test(expected = PersistenceException.class)
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void deleteStore1() throws SessionServiceException, StoreServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		StoreFilterCriteria filters = StoreFilterCriteriaBuilder.storeFilterCriteria()
				.withLabel(TestData.STORE_RACIBORZ_LABEL).build();

		StoreFetchRequest request = new StoreFetchRequest(session);
		request.setFilters(filters);
		StoreFetchResponse respFetch = storeService.fetch(request);

		// when
		StoreDeleteRequest reqDelete = new StoreDeleteRequest(session);
		reqDelete.setStoreId(respFetch.getStores().get(0).getStoreId());
		storeService.delete(reqDelete);

		// then
	}
	
}
