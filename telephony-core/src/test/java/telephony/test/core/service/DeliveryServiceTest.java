package telephony.test.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.test.BaseCoreTest;
import telephony.core.service.*;
import telephony.core.service.dto.ProductEditDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.core.data.TestData;
import telephony.core.entity.jpa.*;
import telephony.core.query.filter.DeliveryFilterCriteria;
import telephony.core.query.filter.DeliveryFilterCriteriaBuilder;
import telephony.core.service.dto.ProductDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.DeliveryServiceException;

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
public class DeliveryServiceTest extends BaseCoreTest {
	
	@Inject
	private DeliveryService deliveryService;
	
	@Inject 
	private SessionService sessionService;
	
	@Inject 
	private UserService userService;
	
	@Inject 
	private ContactService contactService;
	
	@Inject
	private StoreService storeService;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private ModelService modelService;
	
	// TODO : move to TestDataBuilder
	private Product getProductB() {
		Product p = new Product();
		p.setColor("niebieski");
		p.setImei("123451234512345");
		p.setModel(getNokia3310());
		p.setPricings(new ArrayList<Pricing>());
		p.setProductTaxes(new ArrayList<ProductTax>());
		p.setPriceIn(100.0);
		
		return p;
	}
	
	// TODO : move to TestDataBuilder
	private Model getNokia3310() {
		
		Model model = modelService.findByLabel(null, "3310");
		
		return model;
	}
	
	// TODO : move to TestDataBuilder
	private Model getIphone4S() {
		
		Model model = modelService.findByLabel(null, "iphone 4s");
		
		return model;
	}

	// TODO : move to TestDataBuilder
	private Product getProductA() {
		Product p = new Product();
		p.setColor("zielony");
		p.setImei("098760987609876");
		p.setModel(getIphone4S());
		p.setPricings(new ArrayList<Pricing>());
		p.setProductTaxes(new ArrayList<ProductTax>());
		p.setPriceIn(110.0);
		
		return p;
	}
		
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void addingNewDelivery() 
			throws SessionServiceException, DeliveryServiceException, ContactServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		Contact contact = contactService.findByLabel(session, "leszek");
		Store store = storeService.findByLabel(session, TestData.STORE1_LABEL);

		long deliveriesAfter = -1, deliveriesBefore = deliveryService.count(session);
		long productsAfter = -1, productsBefore = productService.count(session);		
		
		Delivery newDelivery = new Delivery();
		
		newDelivery.setDateIn(new Date());
		newDelivery.setLabel("asd asd");
		
		List<Product> products = new ArrayList<Product>();
		products.add(getProductA());
		products.add(getProductB());
		
		deliveryService.add(
				session, newDelivery, 
				products, store.getId(), 
				contact.getId()
		);
		deliveriesAfter = deliveryService.count(session);
		productsAfter = productService.count(session);
	
		// then
		assertTrue("Should increased number of deliveries ", 
				deliveriesAfter - deliveriesBefore == 1);
		
		assertTrue("Should increased number of products ", productsAfter - productsBefore == 2);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void editingExistingDelivery() throws SessionServiceException, DeliveryServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		Long deliveryId = 1L;
		Delivery deliveryToUpdate = deliveryService.findById(session, deliveryId);
		
		// when
		deliveryToUpdate.setLabel("asd asd asd");
		deliveryService.update(session, deliveryToUpdate);
		
		Delivery updated = deliveryService.findById(session, deliveryId);
		
		// then
		assertTrue("Should create and return a new user", 
				updated != null && updated.getLabel().contains("asd asd asd"));
	}
	

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void deletingDelivery() throws SessionServiceException, DeliveryServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		Delivery deliveryToDelete = deliveryService.findById(session, 1L);
		long countAfter = -1, countBefore = deliveryService.count(session);
		
		// when
		deliveryService.delete(session, deliveryToDelete);		
		countAfter = deliveryService.count(session);
		
		// then
		assertTrue("Should decreased number of users ", countBefore - countAfter == 1);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllDeliveries() throws SessionServiceException, DeliveryServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long count = deliveryService.count(session);
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria().build();
		
		// when
		List<Delivery> lst = deliveryService.find(session, filters);		
		
		// then
		assertTrue("Should return exact number of deliveries ", 
				count == 5 && lst.size() == count);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void addNewDelivery1() throws SessionServiceException, DeliveryServiceException, ParseException {
		
		// given
		SessionDto session = SessionDto.create()
				.setSessionId(TestData.USER1_SESSIONID)
				.setUsername(TestData.USER1_NAME);
				
		long deliveriesBefore = deliveryService.count(session);
		long productsBefore = productService.count(session);
		
		DeliveryAddRequest dto = new DeliveryAddRequest();
		dto.setSessionId(TestData.USER1_SESSIONID);
		dto.setUsername(TestData.USER1_NAME);
		
		Date priceTo = new DateTime().withDate(2015, 12, 31).withTime(06, 30, 0, 0).toDate();
		
		List<ProductDto> products = new ArrayList<ProductDto>();
		ProductDto p1 = new ProductDto();
		p1.setProducer("nokia");
		p1.setModel("3310");
		p1.setColor("black");
		p1.setImei("123456789000050");
		p1.setPriceFrom(new Date());
		p1.setPriceTo(priceTo);
		p1.setPriceIn(200.0d);
		p1.setTaxFrom(new Date());
		p1.setTaxTo(priceTo);
		p1.setTaxId(7L);
		
		products.add(p1);
		
		ProductDto p2 = new ProductDto();
		p2.setProducer("nokia");
		p2.setModel("3310");
		p2.setColor("black");
		p2.setImei("123456789000051");
		p2.setPriceFrom(new Date());
		p2.setPriceTo(priceTo);
		p2.setPriceIn(200.0d);
		p2.setTaxFrom(new Date());
		p2.setTaxTo(priceTo);
		p2.setTaxId(7L);
		
		products.add(p2);
		
		dto.setDateIn(new Date());
		dto.setStoreId(1L);
		dto.setContactId(1L);
		dto.setLabel("rrrr");
		dto.setProducts(products);
		
		// when
		DeliveryAddResponse resp = deliveryService.add(dto);
		
		long deliveriesAfter = deliveryService.count(session);
		long productsAfter = productService.count(session);

		// then
		assertTrue( resp.isSuccess() );
		assertEquals( deliveriesAfter - deliveriesBefore, 1);
		assertEquals( productsAfter - productsBefore, 2);		
	}

	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void addNewDelivery2() throws SessionServiceException, DeliveryServiceException, ParseException {
		
		// given
		SessionDto session = SessionDto.create()
				.setSessionId(TestData.USER1_SESSIONID)
				.setUsername(TestData.USER1_NAME);
				
		long deliveriesBefore = deliveryService.count(session);
		long productsBefore = productService.count(session);
		
		DeliveryAddRequest dto = new DeliveryAddRequest();
		dto.setSessionId(TestData.USER1_SESSIONID);
		dto.setUsername(TestData.USER1_NAME);
		
		Date priceTo = new DateTime().withDate(2015, 12, 31).withTime(06, 30, 0, 0).toDate();
		
		List<ProductDto> products = new ArrayList<ProductDto>();
		ProductDto p1 = new ProductDto();
		p1.setProducer("nokia");
		p1.setModel("SX99");
		p1.setColor("black");
		p1.setImei("123456789000050");
		p1.setPriceFrom(new Date());
		p1.setPriceTo(priceTo);
		p1.setPriceIn(200.0d);
		p1.setTaxFrom(new Date());
		p1.setTaxTo(priceTo);
		p1.setTaxId(7L);
		
		products.add(p1);
		
		ProductDto p2 = new ProductDto();
		p2.setProducer("sony");
		p2.setModel("firee");
		p2.setColor("black");
		p2.setImei("123456789000051");
		p2.setPriceFrom(new Date());
		p2.setPriceTo(priceTo);
		p2.setPriceIn(200.0d);
		p2.setTaxFrom(new Date());
		p2.setTaxTo(priceTo);
		p2.setTaxId(7L);
		
		products.add(p2);
		 
		dto.setDateIn(new Date());
		dto.setStoreId(1L);
		dto.setContactId(1L);
		dto.setLabel("rrrr");
		dto.setProducts(products);
		
		// when
		DeliveryAddResponse resp = deliveryService.add(dto);
		
		long deliveriesAfter = deliveryService.count(session);
		long productsAfter = productService.count(session);

		// then
		assertTrue( resp.isSuccess() );
		assertEquals( deliveriesAfter - deliveriesBefore, 1);
		assertEquals( productsAfter - productsBefore, 2);
		
	}

	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDetailsById() throws SessionServiceException, DeliveryServiceException, ParseException {
		
		// given
		long deliveryId = 1;
		
		// when 
		DeliveryDetailsRequest req = new DeliveryDetailsRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setDeliveryId(deliveryId);
		
		DeliveryDetailsResponse resp = deliveryService.fetchDetails(req);
		
		// then
		assertEquals(resp.getDelivery().getLabel(), "nowy rok cieszyn 1");
		assertEquals(resp.getDelivery().getProducts().size(), 8);		
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDeliveries1() throws SessionServiceException, DeliveryServiceException {
		
		// given
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
				.withMinNumberOfProducts(1)
				.withMaxNumberOfProducts(10)
				.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setFilters(filters);		
		
		// when
		DeliveriesFetchResponse lst = deliveryService.findDeliveries(req);
		
		// then
		assertEquals(lst.getDeliveries().size(), 5);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDeliveries2() throws SessionServiceException, DeliveryServiceException {
		
		// given
		Date deliveryDateStart = new DateTime()
			.withDate(2010, 1, 1)
			.withTime(0, 0, 0, 0).toDate();

		Date deliveryDateEnd = new DateTime()
			.withDate(2013, 12, 31)
			.withTime(0, 0, 0, 0).toDate();
		
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
				.withDeliveryDateStart(deliveryDateStart)
				.withDeliveryDateEnd(deliveryDateEnd)
				.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setFilters(filters);		
		
		// when
		DeliveriesFetchResponse lst = deliveryService.findDeliveries(req);
		
		// then
		assertEquals(lst.getDeliveries().size(), 3);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDeliveries3() throws SessionServiceException, DeliveryServiceException {
		
		// given
		String label = "nowy rok cieszyn 1";
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
				.withLabel(label)
				.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setFilters(filters);		
		
		// when
		DeliveriesFetchResponse lst = deliveryService.findDeliveries(req);
		
		// then
		assertEquals(lst.getDeliveries().size(), 1);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDeliveries4() throws SessionServiceException, DeliveryServiceException {
		
		// given
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
				.withMinNumberOfProducts(7)
				.withMaxNumberOfProducts(9)
				.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setFilters(filters);		
		
		// when
		DeliveriesFetchResponse lst = deliveryService.findDeliveries(req);
		
		// then
		assertEquals(lst.getDeliveries().size(), 4);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDeliveries5() throws SessionServiceException, DeliveryServiceException {
		
		// given
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
				.withSumFrom(500.0d)
				.withSumTo(900.0d)
				.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setFilters(filters);		
		
		// when
		DeliveriesFetchResponse lst = deliveryService.findDeliveries(req);
		
		// then
		assertEquals(lst.getDeliveries().size(), 4);		
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDeliveries6() throws SessionServiceException, DeliveryServiceException {
		
		// given
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
				.withSumFrom(500.0d)
				.withSumTo(900.0d)
				.withMinNumberOfProducts(7)
				.withMaxNumberOfProducts(9)
				.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setFilters(filters);
		
		// when
		DeliveriesFetchResponse lst = deliveryService.findDeliveries(req);
		
		// then
		assertEquals(lst.getDeliveries().size(), 4);		
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void editDelivery1() throws SessionServiceException, DeliveryServiceException, ParseException {
		
		// given
		DeliveryEditRequest req = new DeliveryEditRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		
		req.setId(1L);
		req.setContactId(2L);
		req.setStoreId(2L);
		
		req.setLabel("nowy label");
		
		Date d = new Date();
		
		ProductDto productAdd = new ProductDto();
		productAdd.setColor("green");
		productAdd.setImei("123456789000099");
		productAdd.setModel("3310");
		productAdd.setProducer("nokia");
		productAdd.setPriceIn(200.0d);
		productAdd.setPriceFrom(d);
		productAdd.setPriceTo(null);
		productAdd.setTaxId(6L);
		productAdd.setTaxFrom(d);
		productAdd.setTaxTo(null);
		
		req.addProductToAdd(productAdd);
		
		req.addProductToDelete(3L);
		
		ProductEditDto productToEdit = new ProductEditDto();
		productToEdit.setId(2L);
		productToEdit.setModel("3310");
		productToEdit.setProducer("nokia");
		productToEdit.setPrice(300.0d);
		productToEdit.setPriceIn(110.0d);
		
		productToEdit.setTaxId(6L);
		
		req.addProductToEdit(productToEdit);
				
		// when

		DeliveryEditResponse response = deliveryService.edit(req);
		// then
		assertTrue(response.isSuccess());		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void editDelivery2() throws SessionServiceException, DeliveryServiceException, ParseException {
		
		// given
		DeliveryEditRequest req = new DeliveryEditRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		
		req.setId(1L);
		req.setContactId(2L);
		req.setStoreId(2L);
		
		req.setLabel("nowy label");
		
		Date d = new Date();
		
		ProductDto productAdd1 = new ProductDto();
		productAdd1.setColor("green");
		productAdd1.setImei("123456789000099");
		productAdd1.setModel("2000");
		productAdd1.setProducer("nokia");
		productAdd1.setPriceIn(200.0d);
		productAdd1.setPriceFrom(d);
		productAdd1.setPriceTo(null);
		productAdd1.setTaxId(6L);
		productAdd1.setTaxFrom(d);
		productAdd1.setTaxTo(null);
		
		req.addProductToAdd(productAdd1);
		
		ProductDto productAdd2 = new ProductDto();
		productAdd2.setColor("green");
		productAdd2.setImei("123456789000100");
		productAdd2.setModel("xyz");
		productAdd2.setProducer("sony");
		productAdd2.setPriceIn(200.0d);
		productAdd2.setPriceFrom(d);
		productAdd2.setPriceTo(null);
		productAdd2.setTaxId(6L);
		productAdd2.setTaxFrom(d);
		productAdd2.setTaxTo(null);
		
		req.addProductToAdd(productAdd2);
		
		req.addProductToDelete(3L);
		
		ProductEditDto productToEdit1 = new ProductEditDto();
		productToEdit1.setId(2L);
		productToEdit1.setModel("desire y");
		productToEdit1.setProducer("htc");
		productToEdit1.setPrice(300.0d);
		productToEdit1.setPriceIn(110.0d);
		productToEdit1.setTaxId(6L);
		
		req.addProductToEdit(productToEdit1);
		
		ProductEditDto productToEdit2 = new ProductEditDto();
		productToEdit2.setId(4L);
		productToEdit2.setModel("q10");
		productToEdit2.setProducer("blackberry");
		productToEdit2.setPrice(400.0d);
		productToEdit2.setPriceIn(160.0d);
		
		productToEdit2.setTaxId(6L);
		
		req.addProductToEdit(productToEdit2);
		
		// when
		DeliveryEditResponse response = deliveryService.edit(req);
		
		// then
		assertTrue(response.isSuccess());		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void deleteDelivery1() throws SessionServiceException, DeliveryServiceException, ParseException {
		
		// given
		SessionDto session = SessionDto.create()
				.setUsername(TestData.USER1_NAME)
				.setSessionId(TestData.USER1_SESSIONID);
		
		long countBefore = deliveryService.count(session);
		
		DeliveryDeleteRequest req = new DeliveryDeleteRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setDeliveryId(1L);

		// when
		DeliveryDeleteResponse response = deliveryService.delete(req);
		long countAfter = deliveryService.count(session);
		
		// then
		assertTrue(response.isSuccess());
		assertEquals( countBefore - countAfter, 1);
	}
	
	
	
}
