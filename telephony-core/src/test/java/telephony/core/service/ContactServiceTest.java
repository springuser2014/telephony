package telephony.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
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
import telephony.core.entity.jpa.Sale;
import telephony.core.query.filter.ContactFilterCriteria;
import telephony.core.query.filter.ContactFilterCriteriaBuilder;
import telephony.core.service.dto.ContactDto;
import telephony.core.service.dto.ContactEditDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.exception.ContactServiceException;
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
public class ContactServiceTest extends BaseCoreTest {
	
	@Inject
	private ContactService contactService;
	
	@Inject 
	private SessionService sessionService;
	
		
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testDeletingContact() 
			throws SessionServiceException, ContactServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String label = "adam";
		
		Contact contactToDelete = contactService.findByLabel(session, label);
		long countAfter = -1, countBefore = contactService.count(session);

		ContactDeleteRequest req = new ContactDeleteRequest();
		req.setContactToDelete(contactToDelete.getId());
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);

		// when
		contactService.delete(req);
		
		// then
		countAfter = contactService.count(session);
		assertTrue("should decreased number of given elements", (countBefore - countAfter) == 1);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void addingNewContact() throws SessionServiceException, ContactServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String label = "pawelhenek";
		
		Contact newContact = new Contact();
		newContact.setDeliveries(new HashSet<Delivery>());
		newContact.setDetails("contact details");
		
		newContact.setLabel(label);
		newContact.setSales(new HashSet<Sale>());
		
		ContactDto contactBean = new ContactDto(); 
		contactBean.setDetails("contact details");
		contactBean.setLabel(label);
		
		ContactAddRequestDto dto = new ContactAddRequestDto(session, contactBean);
		
		// when
		contactService.add(dto);
				
		// then
		Contact addedContact = contactService.findByLabel(session, label);
		assertTrue("should found new item", addedContact != null);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void updatingContact() throws SessionServiceException, ContactServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String label = "leszek";
		String newDetails = "AFK AFK";
		Contact contactToUpdate = contactService.findByLabel(session, label);
		contactToUpdate.setDetails(newDetails);
		ContactEditDto contactToEditDto = new ContactEditDto();
		contactToEditDto.setId(contactToUpdate.getId());
		contactToEditDto.setDetails(newDetails);
		contactToEditDto.setLabel(label);
		
		ContactEditRequest req = new ContactEditRequest();

		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setContactToEdit(contactToEditDto);

		// when
		contactService.edit(req);
				
		// then
		Contact updatedContact = contactService.findByLabel(session, label);
		assertTrue("should found updated item", updatedContact.getDetails().contains(newDetails));
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllContacts() throws SessionServiceException, ContactServiceException {
		
		// given
		ContactFilterCriteria filters = ContactFilterCriteriaBuilder.contactFilterCriteria().build();
		ContactFetchRequestDto dto = new ContactFetchRequestDto();
		dto.setFilters(filters);
		dto.setSessionId(TestData.USER1_SESSIONID);
		dto.setUsername(TestData.USER1_NAME);
		
		// when
		List<Contact> lst = contactService.find(dto);

		// then		
		assertTrue("should found all items", lst.size() == 0);
	}
	
}
