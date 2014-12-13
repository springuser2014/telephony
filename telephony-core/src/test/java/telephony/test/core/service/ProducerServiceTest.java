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
import telephony.core.entity.jpa.Producer;
import telephony.core.query.filter.ProducerFilterCriteria;
import telephony.core.query.filter.ProducerFilterCriteriaBuilder;
import telephony.core.service.ProducerService;
import telephony.core.service.dto.ProducerDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.ProducerDeleteRequest;
import telephony.core.service.dto.request.ProducerEditRequest;
import telephony.core.service.dto.request.ProducersFetchRequest;
import telephony.core.service.dto.response.ProducersFetchResponse;
import telephony.core.service.exception.ProducerServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.BaseCoreTest;
import telephony.test.core.data.TestData;
import telephony.test.core.data.TestDataBuilder;

import javax.persistence.PersistenceException;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

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
public class ProducerServiceTest extends BaseCoreTest {
	
	@Inject
	private ProducerService producerService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void counting() {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		long count = producerService.count(session);
		
		// then
		assertEquals(count, 4);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingProducerByLabel() throws ProducerServiceException, SessionServiceException {
		
		// given
		String label = "nokia";
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ProducerFilterCriteria filters = ProducerFilterCriteriaBuilder.producerFilterCriteria()
				.withLabel(label)
				.build();

		ProducersFetchRequest request = new ProducersFetchRequest(session);
		request.setFilters(filters);

		
		// when
		ProducersFetchResponse fetchProducers = producerService.fetch(request);
		
		// then
		assertNotNull(fetchProducers);
		assertEquals(fetchProducers.getProducers().size(), 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllProducers() throws ProducerServiceException, SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ProducersFetchRequest request = new ProducersFetchRequest(session);

		// when
		ProducersFetchResponse fetchProducers = producerService.fetch(request);

		// then
		assertNotNull(fetchProducers);
		assertEquals(fetchProducers.getProducers().size(), 4);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingById() throws ProducerServiceException, SessionServiceException {
		
		// given
		long id = 1;
		ProducerFilterCriteria filters = ProducerFilterCriteriaBuilder.producerFilterCriteria()
				.withProducerId(id).build();

		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ProducersFetchRequest request = new ProducersFetchRequest(session);
		request.setFilters(filters);
		
		// when
		ProducersFetchResponse resp = producerService.fetch(request);

		// then
		assertNotNull(resp);
		assertEquals(resp.getProducers().size(), 1);
		assertEquals(resp.getProducers().get(0).getLabel(), "nokia");
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingByIds() throws ProducerServiceException, SessionServiceException {
		
		// given
		long id1 = 1, id2 = 2;
		Collection<Long> ids = Arrays.asList(id1, id2);
		ProducerFilterCriteria filters = ProducerFilterCriteriaBuilder.producerFilterCriteria()
				.withProducerIds(ids).build();

		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ProducersFetchRequest request = new ProducersFetchRequest(session);
		request.setFilters(filters);
		
		// when
		ProducersFetchResponse resp = producerService.fetch(request);
		
		// then
		assertNotNull(resp);
		assertEquals(resp.getProducers().size(), 2);
		assertEquals(resp.getProducers().get(0).getLabel(), "nokia");
		assertEquals(resp.getProducers().get(1).getLabel(), "apple");
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void edit() throws ProducerServiceException, SessionServiceException {
	
		// given
		long id = 1;
		String newLabel = "newlabel";
		ProducerFilterCriteria filters = ProducerFilterCriteriaBuilder.producerFilterCriteria()
				.withProducerId(id).build();

		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID, TestDataBuilder.getFutureDate());
		ProducersFetchRequest request = new ProducersFetchRequest(session);
		request.setFilters(filters);
		ProducersFetchResponse respFetch = producerService.fetch(request);

		// when
		ProducerDto dto =  respFetch.getProducers().get(0);
		dto.setLabel(newLabel);
		ProducerEditRequest editRequest = new ProducerEditRequest(session);
		editRequest.setProducerDto(dto);

		producerService.edit(editRequest);
		respFetch = producerService.fetch(request);

		// then
		assertThat(respFetch).isNotNull();
		assertThat(respFetch.getProducers().get(0).getLabel()).isEqualTo(newLabel);
	}

	@Test(expected = PersistenceException.class) 
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void delete_expectConstraint() throws ProducerServiceException, SessionServiceException {
		
		// given
		long id = 1;
		SessionDto dto = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID, TestDataBuilder.getFutureDate());
		ProducerDeleteRequest request = new ProducerDeleteRequest(dto);
		request.setProducerId(id);

		// when
		producerService.delete(request);
		
		// then
		// exception should arise
	}

}
