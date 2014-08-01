package telephony.core.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.entity.jpa.Producer;

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
public class ProducerServiceTest extends BaseCoreTest {
	
	@Inject
	private ProducerService producerService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingProducerByLabel() {
		
		// given
		String label = "nokia";
		
		// when
		Producer producer = producerService.findByLabel(label);
		
		// then
		assertNotNull(producer);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingById() {
		
		// given
		long id = 1;
		String expected = "nokia";
		
		// when
		Producer producer = producerService.findById(id);
		
		// then
		assertNotNull(producer);
		assertEquals(producer.getLabel(), expected);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void countingProducers() {
		
		// given
		
		// when
		long count = producerService.count();
		
		// then
		assertEquals(count, 2);
	}

}
