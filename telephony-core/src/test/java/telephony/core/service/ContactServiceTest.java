package telephony.core.service;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
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
import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class ContactServiceTest extends BaseCoreTest {
	
	@Inject
	private ContactService contactService;
	
	@Inject 
	private SessionService sessionService;
	
		
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void deletingContact() 
			throws SessionServiceException, ContactServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		String label = "adam";
		Contact contactToDelete = contactService.findByLabel(username, sessionId, label);
		long countAfter = -1, countBefore = contactService.count();

		// when
		contactService.deleteContact(username, sessionId, contactToDelete);

		// then
		countAfter = contactService.count();
		assertTrue("should decreased number of given elements", (countBefore - countAfter) == 1);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void addingNewContact() throws SessionServiceException, ContactServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		String label = "pawelhenek";
		
		Contact newContact = new Contact();
		newContact.setDeliveries(new HashSet<Delivery>());
		newContact.setDetails("contact details");
		
		newContact.setLabel(label);
		newContact.setSales(new HashSet<Sale>());
		
		// when
		contactService.addNewContact(username, sessionId, newContact);
				
		// then
		Contact addedContact = contactService.findByLabel(username, sessionId, label);
		assertTrue("should found new item", addedContact != null);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void updatingContact() throws SessionServiceException, ContactServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		String label = "leszek";
		String newDetails = "AFK AFK";
		Contact contactToUpdate = contactService.findByLabel(username, sessionId, label);
		contactToUpdate.setDetails(newDetails);
		
		// when
		contactService.updateContact(username, sessionId, contactToUpdate);
				
		// then
		Contact updatedContact = contactService.findByLabel(username, sessionId, label);
		assertTrue("should found updated item", updatedContact.getDetails().contains(newDetails));
	}
	
	
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllContacts() throws SessionServiceException, ContactServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		// when
		List<Contact> lst = contactService.fetchAll(username, sessionId);
				
		// then		
		assertTrue("should found all items", lst.size() == 3);
	}
	

}
