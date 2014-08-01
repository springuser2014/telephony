package telephony.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.entity.jpa.Model;
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
public class ModelServiceTest extends BaseCoreTest {
	
	@Inject
	private ModelService modelService;

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingModelByLabel() {
		
		// given
		String label = "iphone 4s";
		
		// when
		Model model = modelService.findByLabel(label);		
		
		// then
		assertNotNull(model);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingById() {
		
		// given
		long id = 1;
		String expected = "6610s";
		
		// when
		Model model = modelService.findById(id);
		
		// then
		assertNotNull(model);
		assertEquals(model.getLabel(), expected);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void countingModels() {
		
		// given
		
		// when
		long count = modelService.count();		
		
		// then
		assertEquals(count, 6);
	}

}
