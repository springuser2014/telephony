package telephony.core.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.entity.jpa.Model;

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
public class ModelServiceTest extends BaseCoreTest {
	
	@Inject
	private ModelService modelService;
	

	private Logger logger = LoggerFactory.getLogger(getClass());
	

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void finding_model_by_label() {
		
		// given
		String label = "iphone 4s";
		
		// when
		Model model = modelService.findByLabel(label);
		
		
		// then
		assertNotNull( model );
	}

}
