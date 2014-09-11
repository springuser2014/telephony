package telephony.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.joda.time.DateTime;
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
import telephony.core.query.filter.ProductFilterCriteria;
import telephony.core.service.dto.ProductFetchRequest;
import telephony.core.service.dto.ProductFetchResponse;
import telephony.core.service.dto.Session;
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
	public void findingProductsByStore() throws SessionServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		Store store = storeService.findByLabel(session, TestData.STORE1_LABEL);
		
		// when
		List<Product> products = productService.findByStore(session, store);
		
		// then
		assertTrue("should found 24 items", products.size() == 24);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllProducts() throws SessionServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		Store store = storeService.findByLabel(session, TestData.STORE1_LABEL);
		
		// when
		List<Product> lst =  productService.fetchAllProducts(
			session, store.getId(), ProductStatus.IN_STORE
		);
		
		// then
		assertTrue("there should be 18 products in the given store", lst.size() == 18); 
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllProducersInUse() {
		
		// given		
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		// when
		List<String> lst = productService.fetchAllProducersInUse(session);
		
		List<String> expected = new ArrayList<String>();
		expected.add("apple");
		expected.add("nokia");
		
		// then
		assertEquals("should contains 2 ", new Integer(2), new Integer(lst.size()));
		assertTrue("should contains 2 producers in use", lst.size() == expected.size());
		assertTrue("exactly those 2 producers in use", lst.containsAll(expected));
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchingAllModels() throws SessionServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		List<String> lst = productService.fetchAllModels(session);
		
		List<String> expected = new ArrayList<String>();
		expected.add("iphone 4s");
		expected.add("6610s");
		expected.add("6600n");
		expected.add("iphone 3g");
		expected.add("iphone 5g");
		expected.add("3310");
		
		// then
		assertEquals("should return 6 different products models", 6, lst.size());
		assertTrue("exactly those 6 products", lst.containsAll(expected));
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchingAllColors() {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		List<String> lst = productService.fetchAllColors(session);
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
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		List<String> lst = productService.fetchAllImeiInUse(session);

		// then
		assertEquals(lst.size(), 42);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchingProductByImeiAndStore() 
			throws SessionServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String imei = "123456789000001";
		long storeId = 1L;
		
		// when
		Product prod = productService
				.fetchProductByImeiAndStoreId(session, imei, storeId);
		
		// then
		assertTrue("should return appropriate product", 
				prod.getImei().contains(imei) && prod.getImei().length() == imei.length());
	}
	

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void movingProductsToAnotherStore() throws SessionServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		long movedFromStoreId = 1L;
		long moveToStoreId = 2L;
		
		Store store = storeService.findById(session, moveToStoreId);
		
		ProductStatus productStatus = ProductStatus.IN_STORE;
		List<Product> productsToMove = productService
				.fetchAllProducts(session, movedFromStoreId, productStatus);
		
		List<Product> produtsBeforeMove = productService
				.fetchAllProducts(session, moveToStoreId, productStatus);
		
		long toMoveCount = productsToMove.size();
		long beforeMoved = produtsBeforeMove.size();
		
		// when
		productService.moveProducts(session, store, productsToMove);	

		// then
		List<Product> productsAfterMove = productService
				.fetchAllProducts(session, moveToStoreId, productStatus);
		
		long afterMoved = productsAfterMove.size();
		
		assertTrue("should move few products", beforeMoved + toMoveCount == afterMoved);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchAllProductsByCriteria1() throws SessionServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		ProductFilterCriteria criteria = ProductFilterCriteria.create(); 		
		criteria.setImei("123456789000001");
		
		// when
		List<Product> products = productService
				.findByCriteria(session, criteria);
		
		// then
		assertTrue("should return one product", products.size() == 1);
		
		assertTrue("should fetch product with given IMEI", 
				products != null && products.get(0).getImei().contains("123456789000001")); 
	}	
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingById() {
		
		// given
		long id = 1;
		
		// when
		Product product = productService.findById(null, id);
				
		// then
		assertNotNull(product);
		assertEquals(product.getImei(), "123456789000000");
		assertEquals(product.getColor(), "black");
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingByIds() {
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id1 = 1, id2 = 2;
		Collection<Long> coll = Arrays.asList(id1, id2);
		
		// when
		Collection<Product> products = productService.findById(session, coll);
				
		// then
		assertNotNull(products);
		assertEquals(products.size(), 2);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findByIMEI() throws SessionServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String imei = "123456789000047";
		
		// when
		Product product = productService.findByIMEI(session, imei);
		
		// then
		assertNotNull(product);
		assertEquals(product.getColor(), "white");
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void update() throws SessionServiceException {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String imei = "123456789000444";
		long id = 1;
		Product product = productService.findById(session, id);
		
		// when
		product.setImei(imei);
		productService.update(session, product);
		
		Product prod = productService.findByIMEI(session, imei);
		
		// then
		assertNotNull(prod);
		assertEquals(product.getColor(), "black");		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void updateCollection() {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		long id1 = 1, id2 = 2;
		Product p1 = productService.findById(session, id1);
		Product p2 = productService.findById(session, id2);
		String imei1 = "123456789000888";
		String imei2 = "123456789000777";
		Product changed1 = null;
		Product changed2 = null;
		
		// when
		p1.setImei(imei1);
		p2.setImei(imei2);
		Collection<Product> coll = Arrays.asList(p1, p2);
		productService.updateCollection(session, coll);
		
		// then
		changed1 = productService.findByIMEI(session, imei1);
		changed2 = productService.findByIMEI(session, imei2);
		
		assertNotNull(changed1);
		assertNotNull(changed2);
		assertEquals(p1.getColor(), "black");
		assertEquals(p2.getColor(), "black");
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void remove() {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id = 1;
		Product p = productService.findById(session, id);
		long countBefore = productService.count(session);
		
		// when
		productService.remove(session, p);
		
		// then
		long countAfter = productService.count(session);
		assertEquals(countBefore - countAfter, 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeCollection() {
	
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id1 = 1, id2 = 2;
		Product p1 = productService.findById(session, id1);
		Product p2 = productService.findById(session, id2);
		long countBefore = productService.count(session);
		Collection<Product> coll = Arrays.asList(p1, p2);
		
		// when
		productService.removeCollection(session, coll);
		
		// then
		long countAfter = productService.count(session);
		assertEquals(countBefore - countAfter, 2);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeById() {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id = 1;
		long countBefore = productService.count(session);
		
		// when
		productService.removeById(session, id);
		
		// then
		long countAfter = productService.count(session);
		assertEquals(countBefore - countAfter, 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeCollectionById() {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id1 = 1, id2 = 2;
		long countBefore = productService.count(session);
		Collection<Long> coll = Arrays.asList(id1, id2);
		
		// when
		productService.removeCollectionByIds(session, coll);
		
		// then
		long countAfter = productService.count(session);
		assertEquals(countBefore - countAfter, 2);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findProducts1() throws SessionServiceException {

		// given
		ProductFilterCriteria filters = ProductFilterCriteria.create()
				.setColor("white")
				.setProducer("nokia");
				
		
		ProductFetchRequest request = new ProductFetchRequest();
		request.setSessionId(TestData.USER1_SESSIONID);
		request.setUsername(TestData.USER1_NAME);
		request.setFiltersCriteria(filters);
		
		// when
		ProductFetchResponse resp = productService.find(request);
		
		// then
		assertEquals(resp.getProducts().size(), 4);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findProducts2() throws SessionServiceException {

		// given
		Date deliveryDateStart = new DateTime().withDate(2012, 1, 1).withTime(0, 0, 0, 0).toDate();
		Date deliveryDateEnd = new DateTime().withDate(2012, 12, 31).withTime(0, 0, 0, 0).toDate();
		
		ProductFilterCriteria filters = ProductFilterCriteria.create()
				.setDeliveryDateStart(deliveryDateStart)
				.setDeliveryDateEnd(deliveryDateEnd);
				
		ProductFetchRequest request = new ProductFetchRequest();
		request.setSessionId(TestData.USER1_SESSIONID);
		request.setUsername(TestData.USER1_NAME);
		request.setFiltersCriteria(filters);
		
		// when
		ProductFetchResponse resp = productService.find(request);
		
		// then
		assertEquals(resp.getProducts().size(), 18);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findProducts3() throws SessionServiceException {

		// given
		ProductFilterCriteria filters = ProductFilterCriteria.create()
				.setColor("black")
				.setProducer("nokia");
				
		
		ProductFetchRequest request = new ProductFetchRequest();
		request.setSessionId(TestData.USER1_SESSIONID);
		request.setUsername(TestData.USER1_NAME);
		request.setFiltersCriteria(filters);
		
		// when
		ProductFetchResponse resp = productService.find(request);
		
		// then
		assertEquals(resp.getProducts().size(), 12);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findProducts4() throws SessionServiceException {

		// given
		ProductFilterCriteria filters = ProductFilterCriteria.create()
				.setImei("123456789000004");
				
		
		ProductFetchRequest request = new ProductFetchRequest();
		request.setSessionId(TestData.USER1_SESSIONID);
		request.setUsername(TestData.USER1_NAME);
		request.setFiltersCriteria(filters);
		
		// when
		ProductFetchResponse resp = productService.find(request);
		
		// then
		assertEquals(resp.getProducts().size(), 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findProducts5() throws SessionServiceException {

		// given
		ProductFilterCriteria filters = ProductFilterCriteria.create()
				.setStatus(ProductStatus.SOLD);
				
		ProductFetchRequest request = new ProductFetchRequest();
		request.setSessionId(TestData.USER1_SESSIONID);
		request.setUsername(TestData.USER1_NAME);
		request.setFiltersCriteria(filters);
		
		// when
		ProductFetchResponse resp = productService.find(request);
		
		// then
		assertEquals(resp.getProducts().size(), 10);
	}

}
