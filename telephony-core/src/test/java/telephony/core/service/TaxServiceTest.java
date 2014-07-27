package telephony.core.service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.entity.jpa.Tax;

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
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void add_tax() {
		
		// given
		Tax tax = new Tax();
		tax.setFrom(new DateTime().withDate(2010, 01, 01).toDate());
		tax.setTo(new DateTime().withDate(2014, 12, 31).toDate());
		tax.setRate(123.45);		
		long taxesBefore = taxService.count();
		
		// when
		taxService.addTax(tax);
		long taxesAfter = taxService.count();
		
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
		// when
		Tax tax = taxService.findById(id);
		
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

		// when
		Collection<Tax> taxes = taxService.findInDateRange(from, to);
		
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

		// when
		Collection<Tax> taxes = taxService.findInDateRange(from, to);
		
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

		// when
		Collection<Tax> taxes = taxService.findInDateRange(from, to);
		
		// then
		assertEquals(taxes.size(), 2);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void search_tax_in_date_range4() {
		
		// given
		Date from = null;
		Date to = null;

		// when
		Collection<Tax> taxes = taxService.findInDateRange(from, to);
		
		// then
		assertEquals(taxes.size(), 7);		
	}
	


	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void update_tax() {
		
		// given
		long id = 2;
		Tax taxBefore = taxService.findById(id);
		double rateBefore = taxBefore.getRate();
		double rateAfter = rateBefore * 1.5;

		// when
		taxBefore.setRate(rateAfter);
		Tax taxAfter = taxService.update(taxBefore);
		
		// then
		assertTrue(taxAfter.getRate() == rateAfter);
	}
	

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void delete_tax() {
		
		// given
		long id = 2;
		Tax taxToDelete = taxService.findById(id);
		long before = taxService.count();
		
		// when
		taxService.delete(taxToDelete);
		long after = taxService.count();
		
		// then
		assertEquals(before - after, 1);		
	}


}
