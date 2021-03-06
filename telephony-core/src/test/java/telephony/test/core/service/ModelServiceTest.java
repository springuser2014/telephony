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
import telephony.core.query.filter.ModelFilterCriteria;
import telephony.core.query.filter.ModelFilterCriteriaBuilder;
import telephony.core.service.ModelService;
import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.ModelDeleteRequest;
import telephony.core.service.dto.request.ModelEditRequest;
import telephony.core.service.dto.request.ModelFetchRequest;
import telephony.core.service.dto.response.ModelEditResponse;
import telephony.core.service.dto.response.ModelFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.BaseCoreTest;
import telephony.test.core.data.TestData;

import javax.persistence.PersistenceException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


/**
 * asd.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class ModelServiceTest extends BaseCoreTest {
	
	@Inject
	private ModelService modelService;

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingModelByLabel() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String label = "iphone 4s";
		ModelFilterCriteria filters = ModelFilterCriteriaBuilder.modelFilterCriteria()
			.withLabel(label)
			.withPage(0).withPerPage(100)
			.build();

		ModelFetchRequest request = new ModelFetchRequest(session);
		request.setFilters(filters);
		
		// when
		ModelFetchResponse responseFetch = modelService.fetch(request);
		
		// then
		assertNotNull(responseFetch);
		assertEquals(responseFetch.getModels().size(), 1);
		assertEquals(responseFetch.getModels().get(0).getLabel(), label);
		assertNotNull(responseFetch.getModels().get(0).getId());
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingById() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		long id = 1;
		ModelFilterCriteria filters = ModelFilterCriteriaBuilder.modelFilterCriteria()
			.withModelId(id)
			.withPage(0).withPerPage(100)
			.build();

		ModelFetchRequest request = new ModelFetchRequest(session);
		request.setFilters(filters);
		
		// when
		ModelFetchResponse responseFetch = modelService.fetch(request);
		
		// then
		assertNotNull(responseFetch);
		assertEquals(responseFetch.getModels().size(), 1);
		assertEquals(responseFetch.getModels().get(0).getLabel(), "6610s");
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingByIds() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		List<Long> ids = Arrays.asList(1L, 2L, 3L);
		ModelFilterCriteria filters = ModelFilterCriteriaBuilder.modelFilterCriteria()
			.withModelIds(ids)
			.withPage(0).withPerPage(100)
			.build();

		ModelFetchRequest request = new ModelFetchRequest(session);
		request.setFilters(filters);

		// when
		ModelFetchResponse responseFetch = modelService.fetch(request);
		
		// then
		assertNotNull(responseFetch);
		assertEquals(responseFetch.getModels().size(), 3);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void counting() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		long count = modelService.count(session);		
		
		// then
		assertEquals(count, 8);
	}
		
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void edit() throws SessionServiceException {
	
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id = 1;
		String newLabel = "newlabel";

		ModelFilterCriteria filters = ModelFilterCriteriaBuilder.modelFilterCriteria()
			.withModelId(id)
			.withPage(0).withPerPage(100)
			.build();

		ModelFetchRequest request = new ModelFetchRequest(session);
		request.setFilters(filters);

		ModelFetchResponse responseFetch1 = modelService.fetch(request);
		ModelDto dto = responseFetch1.getModels().get(0);
		dto.setLabel(newLabel);

		// when
		ModelEditRequest editRequest = new ModelEditRequest(session);
		editRequest.setModelDto(dto);

		ModelEditResponse editResponse = modelService.edit(editRequest);
		ModelFetchResponse responseFetch2 = modelService.fetch(request);
		
		// then
		assertNotNull(editResponse);
		assertTrue(editResponse.isSuccess());

		assertNotNull(responseFetch2);
		assertEquals(responseFetch2.getModels().size(), 1);
		assertEquals(responseFetch2.getModels().get(0).getLabel(), newLabel);
	}

	@Test(expected = PersistenceException.class)
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void remove_expectException() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long countBefore = modelService.count(session);
		ModelDeleteRequest request = new ModelDeleteRequest(session);
		request.setModelId(1L);
		
		// when
		modelService.delete(request);
		long countAfter = modelService.count(session);
		
		// then
		assertEquals(countBefore  - countAfter, 1);
	}

}
