package telephony.core.service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class AbstractGenericServiceTest extends BaseCoreTest {
	
	@Inject
	private GenericService<Tax> genericService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/*
	 * TODO write more tests  for AbstractGenericService
	 */

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testCount() {
		
		// given
		Date from = new DateTime()
					.withDate(2002, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		Date to = new DateTime()
					.withDate(2012, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		long taxesBefore = genericService.count();
		Tax tax = new Tax();
		tax.setRate(17.0);
		tax.setFrom(from);
		tax.setTo(to);
		
		
		// when
		long taxesAfter = genericService.count();
		genericService.save(tax);
		
		// then
		assertEquals(taxesAfter - taxesBefore, 1);		
	}
}
