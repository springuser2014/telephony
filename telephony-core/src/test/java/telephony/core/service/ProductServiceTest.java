package telephony.core.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import telephony.BaseCoreTest;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/META-INF/context.xml" })
//@TestExecutionListeners( {
//	DependencyInjectionTestExecutionListener.class,
//    FlywayDBUnitTestExecutionListener.class 
//})
//@FlywayTest
public class ProductServiceTest extends BaseCoreTest {
	
	@Inject
	private ProductService productService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration" } )
	public void contactasd() {
		assertTrue(true);
	}
}
