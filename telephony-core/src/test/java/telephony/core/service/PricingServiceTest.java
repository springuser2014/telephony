package telephony.core.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

import telephony.BaseCoreTest;

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
public class PricingServiceTest extends BaseCoreTest {
	
	@Inject
	private PricingService pricingService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void counting() {
		
		// given
		
		// when 
		long pricingsOfNumber = pricingService.count();
		
		// then
		assertEquals(pricingsOfNumber, 0);		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findByDateRange() {
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findByRateRange() {
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingById() {
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingByIds() {
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void update() {
		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void updateCollection() {
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void remove() {
		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeCollection() {
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeById() {
		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeCollectionById() {
		
	}
}
