package telephony.core.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
import telephony.core.entity.jpa.Producer;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Store;
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
	
	private Product getProductB() {
		Product p = new Product();
		p.setColor("niebieski");
		p.setImei("123451234512345");
		p.setModel(getNokia3310());
		
		return p;
	}
	
	private Model getNokia3310() {
		
		Model model = modelService.findByLabel("3310");
		
		return model;
	}
	
	private Model getIphone4S() {
		
		Model model = modelService.findByLabel("iphone 4s");
		
		return model;
	}

	private Product getProductA() {
		Product p = new Product();
		p.setColor("zielony");
		p.setImei("098760987609876");
		p.setModel(getIphone4S());
		
		return p;
	}
		
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void addingNewDelivery() 
			throws SessionServiceException, DeliveryServiceException, ContactServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Contact contact = contactService.findByLabel(username, sessionId, "leszek");
		Store store = storeService.findByLabel(username, sessionId, TestData.STORE1_LABEL);

		long deliveriesAfter = -1, deliveriesBefore = deliveryService.count();
		long productsAfter = -1, productsBefore = productService.count();		
		
		Delivery newDelivery = new Delivery();
		
		newDelivery.setDateIn(new Date());
		newDelivery.setLabel("asd asd");
		
		List<Product> products = new ArrayList<Product>();
		products.add(getProductA());
		products.add(getProductB());
		
		try {
			// when  
			deliveryService.addNewDelivery(
					username, sessionId, 
					newDelivery, products, 
					store.getId(), contact.getId()
			);
			
		} catch (Exception e) {
			int i = 0; 
			i = i + 1;
		}
		deliveriesAfter = deliveryService.count();
		productsAfter = productService.count();
	
		// then
		assertTrue("Should increased number of deliveries ", 
				deliveriesAfter - deliveriesBefore == 1);
		
		assertTrue("Should increased number of products ", productsAfter - productsBefore == 2);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void editingExistingDelivery() throws SessionServiceException, DeliveryServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Long deliveryId = 1L;
		Delivery deliveryToUpdate = deliveryService.findById(username, sessionId, deliveryId);
		
		// when
		deliveryToUpdate.setLabel("asd asd asd");
		deliveryService.updateDelivery(username, sessionId, deliveryToUpdate);
		
		Delivery updated = deliveryService.findById(username, sessionId, deliveryId);
		
		// then
		assertTrue("Should create and return a new user", 
				updated != null && updated.getLabel().contains("asd asd asd"));
	}
	

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void deletingDelivery() throws SessionServiceException, DeliveryServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Delivery deliveryToDelete = deliveryService.findById(username, sessionId, 1L);
		long countAfter = -1, countBefore = deliveryService.count();
		
		// when
		deliveryService.delete(username, sessionId, deliveryToDelete);		
		countAfter = deliveryService.count();
		
		// then
		assertTrue("Should decreased number of users ", countBefore - countAfter == 1);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllDeliveries() throws SessionServiceException, DeliveryServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		long count = deliveryService.count();
		
		// when
		List<Delivery> lst = deliveryService.fetchAllDeliveries(username, sessionId);	
		
		
		// then
		assertTrue("Should return exact number of deliveries ", 
				count == 5 && lst.size() == count);
	}
}
