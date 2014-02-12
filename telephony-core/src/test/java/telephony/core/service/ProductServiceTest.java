package telephony.core.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
import telephony.core.entity.jpa.ProductStatus;
import telephony.core.entity.jpa.Store;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;


/**
 * @author Paweł Henek <pawelhenek@gmail.com>
 */
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
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllProducts() throws SessionServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Store store = storeService.findByLabel(username, sessionId, TestData.STORE1_LABEL);
		
		// when
		List<Product> lst = 
				productService.fetchAllProducts(
						username, sessionId, store.getId(), ProductStatus.IN_STORE
		);
		
		// then
		assertTrue("there should be 18 products in the given store", lst.size() == 18); 
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllProducers() {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		// when
		List<String> lst = productService.fetchAllProducers(username, sessionId);
		
		List<String> expected = new ArrayList<String>();
		expected.add("apple");
		expected.add("nokia");
		
		// then
		assertTrue("should contains 2 products", lst.size() == expected.size());
		assertTrue("exactly those 2 products", lst.containsAll(expected));
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchingAllModels() throws SessionServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		// when
		List<String> lst = productService.fetchAllModels(username, sessionId);
		
		List<String> expected = new ArrayList<String>();
		expected.add("iphone 4s");
		expected.add("6610s");
		expected.add("6600n");
		expected.add("iphone 3g");
		expected.add("iphone 5g");
		expected.add("3310");
		
		// then
		assertTrue("should return 6 different products models", lst.size() == 6);
		assertTrue("exactly those 6 products", lst.containsAll(expected));
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchingAllColors() {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		// when
		List<String> lst = productService.fetchAllColors(username, sessionId);
		List<String> expected = new ArrayList<String>();
		expected.add("black");
		expected.add("white");
		expected.add("blue");
		expected.add("red");
		
		// then
		assertTrue("should return 4 different colors", lst.size() == 4);
		assertTrue("exactly those 4 colors ", lst.containsAll(expected));
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchingAllIMEIsInUse() throws SessionServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		// when
		List<String> lst = productService.fetchAllImeiInUse(username, sessionId);

		// then
		assertTrue("should return 40 different IMEIs", lst.size() == 40);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchingProductByImeiAndStore() 
			throws SessionServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		String imei = "123456789000001";
		long storeId = 1L;
		
		// when
		Product prod = productService
				.fetchProductByImeiAndStoreId(username, sessionId, imei, storeId);
		
		// then
		assertTrue("should return appropriate product", 
				prod.getImei().contains(imei) && prod.getImei().length() == imei.length());
	}
	

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void movingProductsToAnotherStore() throws SessionServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		long movedFromStoreId = 1L;
		long moveToStoreId = 2L;
		
		Store store = storeService.findById(username, sessionId, moveToStoreId);
		
		ProductStatus productStatus = ProductStatus.IN_STORE;
		List<Product> productsToMove = productService
				.fetchAllProducts(username, sessionId, movedFromStoreId, productStatus);
		
		List<Product> produtsBeforeMove = productService
				.fetchAllProducts(username, sessionId, moveToStoreId, productStatus);
		
		long toMoveCount = productsToMove.size();
		long beforeMoved = produtsBeforeMove.size();
		
		// when
		productService.moveProducts(username, sessionId, store, productsToMove);	

		// then
		List<Product> productsAfterMove = productService
				.fetchAllProducts(username, sessionId, moveToStoreId, productStatus);
		
		long afterMoved = productsAfterMove.size();
		
		assertTrue("should move few products", beforeMoved + toMoveCount == afterMoved);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchAllProductsByCriteria() throws SessionServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		ProductQueryCriteria criteria = new ProductQueryCriteria(); 		
		criteria.setImei("123456789000001");
		
		// when
		List<Product> products = productService
				.fetchAllProductsByCriteria(username, sessionId, criteria);
		
		// then
		assertTrue("should return one product", products.size() == 1);
		
		assertTrue("should fetch product with given IMEI", 
				products != null && products.get(0).getImei().contains("123456789000001")); 
	}
	
}
