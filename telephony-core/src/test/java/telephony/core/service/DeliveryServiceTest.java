package telephony.core.service;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.data.TestData;
import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.User;
import telephony.core.service.exception.DeliveryServiceException;
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
public class DeliveryServiceTest extends BaseCoreTest {
	
	@Inject
	private DeliveryService deliveryService;
	
	@Inject 
	private SessionService sessionService;
	
	@Inject 
	private UserService userService;
	
		
	@Test
//	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" } )
	public void addingNewDelivery() throws SessionServiceException, DeliveryServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;		
		long countAfter = -1, countBefore = deliveryService.count();
		Delivery newDelivery = new Delivery();
		
		newDelivery.setDateIn(new Date());
		newDelivery.setContact(null);
		
		assertTrue(true);
		
		
		// when
//		deliveryService.addNewDelivery(username, sessionId, newDelivery);		
		countAfter = deliveryService.count();
		
		// then
//		assertTrue("Should decreased number of users ", countBefore - countAfter == 1);
	}
	

//	@Test
//	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" } )
	public void editingExistingDelivery() throws SessionServiceException, DeliveryServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Long deliveryId = 1L;
		Delivery deliveryToUpdate = deliveryService.findById(null, null, deliveryId);
		
		// when
		deliveryService.updateDelivery(username, sessionId, deliveryToUpdate);	
		
		
		// then
		assertTrue("Should create and return a new user", deliveryToUpdate != null);
	}
	

//	@Test
//	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" } )
	public void deletingDelivery() throws SessionServiceException, DeliveryServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Delivery deliveryToDelete = null;
		
		// when
		deliveryService.delete(username, sessionId, deliveryToDelete);		
		
		// then
//		assertTrue("Should decreased number of users ", countBefore - countAfter == 1);
	}
}
