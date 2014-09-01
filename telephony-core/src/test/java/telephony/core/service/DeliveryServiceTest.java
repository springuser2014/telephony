package telephony.core.service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.data.TestData;
import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Model;
import telephony.core.entity.jpa.Pricing;
import telephony.core.entity.jpa.Producer;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductTax;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.Tax;
import telephony.core.query.filter.DeliveryFilterCriteria;
import telephony.core.service.dto.DeliveryAddRequest;
import telephony.core.service.dto.DeliveryAddResponse;
import telephony.core.service.dto.ProductBean;
import telephony.core.service.dto.Session;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.DeliveryServiceException;
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
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
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
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
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
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
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
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long count = deliveryService.count(session);
		DeliveryFilterCriteria filters = DeliveryFilterCriteria.create();
		
		// when
		List<Delivery> lst = deliveryService.find(session, filters);		
		
		// then
		assertTrue("Should return exact number of deliveries ", 
				count == 5 && lst.size() == count);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void addNewDelivery() throws SessionServiceException, DeliveryServiceException {
		
		// given
		Session session = Session.create()
				.sessionId(TestData.USER1_SESSIONID)
				.username(TestData.USER1_NAME);
				
		long deliveriesBefore = deliveryService.count(session);
		long productsBefore = productService.count(session);
		
		DeliveryAddRequest dto = new DeliveryAddRequest();
		dto.setSessionId(TestData.USER1_SESSIONID);
		dto.setUsername(TestData.USER1_NAME);
		
		Date priceTo = new DateTime().withDate(2015, 12, 31).withTime(06, 30, 0, 0).toDate();
		
		List<ProductBean> products = new ArrayList<ProductBean>();
		ProductBean p1 = new ProductBean();
		p1.setProducer("Nokia");
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
		dto.setDateIn(new Date());
		dto.setStoreId(1L);
		dto.setContactId(1L);
		dto.setLabel("rrrr");
		dto.setProducts(products);
		
		// when
		try {
		DeliveryAddResponse resp = deliveryService.add(dto);
		
		long deliveriesAfter = deliveryService.count(session);
		long productsAfter = productService.count(session);

		// then
		assertTrue( resp.isSuccess() );
		assertEquals( deliveriesAfter - deliveriesBefore, 1);
		assertEquals( productsAfter - productsBefore, 1);
		
		} catch (Exception e) {
			long id = 1;
			long id2 = id + 1;
		}
	}
}
