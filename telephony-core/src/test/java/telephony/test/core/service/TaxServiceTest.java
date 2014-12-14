package telephony.test.core.service;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import telephony.core.query.filter.TaxFilterCriteria;
import telephony.core.query.filter.TaxFilterCriteriaBuilder;
import telephony.core.service.TaxService;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.TaxDto;
import telephony.core.service.dto.request.TaxAddRequest;
import telephony.core.service.dto.request.TaxDeleteRequest;
import telephony.core.service.dto.request.TaxEditRequest;
import telephony.core.service.dto.request.TaxFetchRequest;
import telephony.core.service.dto.response.TaxEditResponse;
import telephony.core.service.dto.response.TaxFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.BaseCoreTest;
import telephony.test.core.data.TestData;
import telephony.test.core.data.TestDataBuilder;

import java.util.Date;

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
public class TaxServiceTest extends BaseCoreTest {
	
	@Inject
	private TaxService taxService;

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void addTax() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		TaxDto taxDto = new TaxDto();

		taxDto.setFrom(new DateTime().withDate(2010, 01, 01).toDate());
		taxDto.setTo(new DateTime().withDate(2014, 12, 31).toDate());
		taxDto.setRate(123.45);
		long taxesBefore = taxService.count(session);
		
		// when
		TaxAddRequest request = new TaxAddRequest(session);
		request.setTaxDto(taxDto);
		taxService.add(request);
		long taxesAfter = taxService.count(session);
		
		// then
		assertEquals(taxesAfter - taxesBefore, 1);		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingById() throws SessionServiceException {
		
		// given
		long id = 2;
		Date from = TestDataBuilder.getDate(2000,1,1);
		Date to = TestDataBuilder.getDate(2010,1,1);
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		TaxFilterCriteria filters = TaxFilterCriteriaBuilder.taxFilterCriteria()
				.withTaxId(id).build();

		TaxFetchRequest request = new TaxFetchRequest(session);
		request.setFilters(filters);

		// when
		TaxFetchResponse resp = taxService.fetch(request);
		
		// then
		assertNotNull(resp);
		assertEquals(resp.getTaxes().get(0).getFrom().getTime(), from.getTime());
		assertEquals(resp.getTaxes().get(0).getTo().getTime(), to.getTime());
		assertEquals(resp.getTaxes().get(0).getRate(), 15.0d, 0.001);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingTaxInDateRange1() throws SessionServiceException {

		// given
		Date from = TestDataBuilder.getDate(1999, 1, 1);
		Date to = TestDataBuilder.getDate(2014, 1, 1);

		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		TaxFilterCriteria filters = TaxFilterCriteriaBuilder.taxFilterCriteria()
				.withTaxDateStart(from)
				.withTaxDateEnd(to).build();

		// when
		TaxFetchRequest request = new TaxFetchRequest(session);
		request.setFilters(filters);
		TaxFetchResponse resp = taxService.fetch(request);

		// then
		assertNotNull(resp);
		assertEquals(resp.getTaxes().size(), 5);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingTaxInDateRange2() throws SessionServiceException {

		// given
		Date from = TestDataBuilder.getDate(2013,1,1);
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		TaxFilterCriteria filters = TaxFilterCriteriaBuilder.taxFilterCriteria()
				.withTaxDateStart(from).build();

		// when
		TaxFetchRequest request = new TaxFetchRequest(session);
		request.setFilters(filters);
		TaxFetchResponse resp = taxService.fetch(request);

		// then
		assertNotNull(resp);
		assertEquals(resp.getTaxes().size(), 5);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingTaxInDateRange3() throws SessionServiceException {

		// given
		Date from = null;
		Date to = TestDataBuilder.getDate(2012,1,1);
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		TaxFilterCriteria filters = TaxFilterCriteriaBuilder.taxFilterCriteria()
				.withTaxDateEnd(to)
				.withTaxDateStart(from)
				.build();
		TaxFetchRequest request = new TaxFetchRequest(session);
		request.setFilters(filters);

		// when
		TaxFetchResponse resp = taxService.fetch(request);

		// then
		assertNotNull(resp);
		assertEquals(resp.getTaxes().size(), 2);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingTaxInDateRange4() throws SessionServiceException {

		// given
		Date from = null;
		Date to = null;
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		TaxFilterCriteria filters = TaxFilterCriteriaBuilder.taxFilterCriteria()
				.withTaxDateStart(from).withTaxDateEnd(to).build();
		TaxFetchRequest request = new TaxFetchRequest(session);
		request.setFilters(filters);

		// when
		TaxFetchResponse resp = taxService.fetch(request);

		// then
		assertNotNull(resp);
		assertEquals(resp.getTaxes().size(), 7);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void edit1() throws SessionServiceException {

		// given
		long taxId = 2;
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		TaxFilterCriteria filters = TaxFilterCriteriaBuilder.taxFilterCriteria()
				.withTaxId(taxId).build();
		TaxFetchRequest request = new TaxFetchRequest(session);
		request.setFilters(filters);
		TaxFetchResponse resp1 = taxService.fetch(request);
		TaxDto dto = resp1.getTaxes().get(0);
		double rateBefore = dto.getRate();
		double rateAfter = rateBefore * 1.5;

		// when
		dto.setRate(rateAfter);
		TaxEditRequest editRequest = new TaxEditRequest(session);
		editRequest.setTaxDto(dto);
		TaxEditResponse resp2 = taxService.edit(editRequest);
		resp1 = taxService.fetch(request);

		// then
		assertNotNull(resp2);
		assertTrue(resp2.isSuccess());

		assertNotNull(resp1);
		assertEquals(resp1.getTaxes().get(0).getRate(), rateAfter, 0.01);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void delete1() throws SessionServiceException {

		// given
		long taxId = 2;
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		TaxFilterCriteria filters = TaxFilterCriteriaBuilder.taxFilterCriteria()
				.withTaxId(taxId).build();
		TaxFetchRequest request = new TaxFetchRequest(session);
		request.setFilters(filters);

		TaxFetchResponse taxToDelete = taxService.fetch(request);
		long before = taxService.count(session);

		TaxDeleteRequest deleteRequest = new TaxDeleteRequest(session);
		deleteRequest.setTaxId(taxToDelete.getTaxes().get(0).getId());

		// when
		taxService.delete(deleteRequest);
		long after = taxService.count(session);

		// then
		assertEquals(before - after, 1);
	}
}
