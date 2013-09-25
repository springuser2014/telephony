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
import telephony.core.entity.jpa.Sale;
import telephony.core.service.exception.SaleServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners( {
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class SaleServiceTest extends BaseCoreTest {
	
	@Inject
	SaleService saleService;
	
	@Inject
	SessionService sessionService;
	
	@Inject
	UserService userService;
	
	@Inject
	StoreService storeService;
	
	@Inject
	ProductService productService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" } )
	public void fetchingAllSale() throws SessionServiceException, SaleServiceException {

		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		long count = saleService.count();
		
		// when
		List<Sale> lst = saleService.findAllSales(username, sessionId);
		
		// then
		assertTrue("Should return exact number of sales", 
				count == 2 && lst.size() == count);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" } )
	public void deletingSale() {

		// given
		
		// when
		
		// then
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" } )
	public void addingNewSale() {

		// given
		
		// when
		
		// then
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" } )
	public void editingExistingSale() {

		// given
		
		// when
		
		// then
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" } )
	public void findById() throws SessionServiceException, SaleServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Long saleId = 1L;
		
		// when
		Sale sale = saleService.findById(username, sessionId, saleId);
		
		// then
		assertTrue("should not be null ", sale != null);
		assertTrue("should have appropriate label", sale.getLabel().contains("nowy rok cieszyn"));
	}

}
