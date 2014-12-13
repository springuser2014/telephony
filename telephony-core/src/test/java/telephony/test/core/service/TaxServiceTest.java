package telephony.test.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.test.BaseCoreTest;
import telephony.core.service.TaxService;
import telephony.test.core.data.TestData;
import telephony.core.entity.jpa.Tax;
import telephony.core.service.dto.SessionDto;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

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
	public void add_tax() {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		Tax tax = new Tax();
		tax.setFrom(new DateTime().withDate(2010, 01, 01).toDate());
		tax.setTo(new DateTime().withDate(2014, 12, 31).toDate());
		tax.setRate(123.45);		
		long taxesBefore = taxService.count(session);
		
		// when
		taxService.add(session, tax);
		long taxesAfter = taxService.count(session);
		
		// then
		assertEquals(taxesAfter - taxesBefore, 1);		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void search_tax_by_id() {
		
		// given
		long id = 2;
		Date from = new DateTime()
						.withDate(2000, 1, 1)
						.withHourOfDay(0)
						.withMinuteOfHour(0)
						.withSecondOfMinute(0)
						.withMillisOfSecond(0).toDate();

		Date to = new DateTime()
						.withDate(2010, 1, 1)
						.withHourOfDay(0)
						.withMinuteOfHour(0)
						.withSecondOfMinute(0)
						.withMillisOfSecond(0).toDate();
		
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		Tax tax = taxService.findById(session, id);
		
		// then
		assertNotNull(tax);
		assertEquals(tax.getFrom().getTime(), from.getTime());
		assertEquals(tax.getTo().getTime(), to.getTime());
		assertTrue(tax.getRate().equals(15.0d));
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void search_tax_in_date_range1() {
		
		// given
		Date from = new DateTime()
					.withDate(1999, 1, 1)
					.withHourOfDay(0)
					.withMinuteOfHour(0)
					.withSecondOfMinute(0)
					.withMillisOfSecond(0).toDate();

		Date to = new DateTime()
					.withDate(2014, 1, 1)
					.withHourOfDay(0)
					.withMinuteOfHour(0)
					.withSecondOfMinute(0)
					.withMillisOfSecond(0).toDate();
		
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		// when
		Collection<Tax> taxes = taxService.findInDateRange(session, from, to);
		
		// then
		assertEquals(taxes.size(), 5);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void search_tax_in_date_range2() {
		
		// given
		Date from = new DateTime()
					.withDate(2013, 1, 1)
					.withHourOfDay(0)
					.withMinuteOfHour(0)
					.withSecondOfMinute(0)
					.withMillisOfSecond(0).toDate();

		Date to = null;
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		Collection<Tax> taxes = taxService.findInDateRange(session, from, to);
		
		// then
		assertEquals(taxes.size(), 4);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void search_tax_in_date_range3() {
		
		// given
		Date from = null;

		Date to = new DateTime()
					.withDate(2012, 1, 1)
					.withHourOfDay(0)
					.withMinuteOfHour(0)
					.withSecondOfMinute(0)
					.withMillisOfSecond(0).toDate();
		
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		// when
		Collection<Tax> taxes = taxService.findInDateRange(session, from, to);
		
		// then
		assertEquals(taxes.size(), 2);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void search_tax_in_date_range4() {
		
		// given
		Date from = null;
		Date to = null;
		
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		// when
		Collection<Tax> taxes = taxService.findInDateRange(session, from, to);
		
		// then
		assertEquals(taxes.size(), 7);		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void update_tax() {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id = 2;
		Tax taxBefore = taxService.findById(session, id);
		double rateBefore = taxBefore.getRate();
		double rateAfter = rateBefore * 1.5;

		// when
		taxBefore.setRate(rateAfter);
		Tax taxAfter = taxService.update(session, taxBefore);
		
		// then
		assertTrue(taxAfter.getRate() == rateAfter);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void delete_tax() {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id = 2;
		Tax taxToDelete = taxService.findById(session, id);
		long before = taxService.count(session);
		
		// when
		taxService.remove(session, taxToDelete);
		long after = taxService.count(session);
		
		// then
		assertEquals(before - after, 1);		
	}
}