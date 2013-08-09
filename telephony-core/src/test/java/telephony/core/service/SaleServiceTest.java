package telephony.core.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/META-INF/context.xml" })
//@TestExecutionListeners( {
//	DependencyInjectionTestExecutionListener.class,
//    FlywayDBUnitTestExecutionListener.class 
//})
//@FlywayTest
public class SaleServiceTest extends BaseCoreTest {
	
	@Inject
	private SaleService saleService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration" } )
	public void contactasd() {
		assertTrue(true);
	}
	


}
