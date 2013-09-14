package telephony.core.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.data.TestData;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Store;
import telephony.core.service.exception.SessionServiceException;

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
public class ProductServiceTest extends BaseCoreTest {
	
	@Inject
	private ProductService productService;
	
	@Inject
	private StoreService storeService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testingFindingProductsByStore() throws SessionServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Store store = storeService.findByLabel(username, sessionId, TestData.STORE1_LABEL);
		
		// when
		List<Product> products = productService.findByStore(username, sessionId, store);
		
		// then
		assertTrue("should found 24 items", products.size() == 24);
	}
}
