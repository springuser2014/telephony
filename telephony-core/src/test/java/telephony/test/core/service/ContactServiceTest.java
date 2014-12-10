package telephony.test.core.service;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import telephony.core.query.filter.ContactFilterCriteria;
import telephony.core.query.filter.ContactFilterCriteriaBuilder;
import telephony.core.service.ContactService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.*;
import telephony.core.service.dto.request.ContactAddRequest;
import telephony.core.service.dto.request.ContactDeleteRequest;
import telephony.core.service.dto.request.ContactEditRequest;
import telephony.core.service.dto.request.ContactFetchRequest;
import telephony.core.service.dto.response.ContactEditResponse;
import telephony.core.service.dto.response.ContactFetchResponse;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.BaseCoreTest;
import telephony.test.core.data.TestData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String label = "adam";
		ContactFilterCriteria filters = ContactFilterCriteriaBuilder.contactFilterCriteria()
				.withLabel(label)
				.build();

		ContactFetchRequest request = new ContactFetchRequest();
		request.setSessionId(TestData.USER1_SESSIONID);
		request.setUsername(TestData.USER1_NAME);
		request.setFilters(filters);

		ContactFetchResponse response = contactService.fetch(request);
		ContactDto dto = response.getContacts().get(0);
		long countAfter = -1, countBefore = contactService.count(session);

		ContactDeleteRequest req = new ContactDeleteRequest();
		req.setContactToDelete(dto.getId());
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
	public void addingContact() throws SessionServiceException, ContactServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String label = "pawelhenek";
		String details = "contact details";

		AddressDto addressDto = new AddressDto();
		addressDto.setAddressLine1("Wypoczynkowa 20B");
		addressDto.setZipCode("44-100");
		addressDto.setCity("Rybnik");
		addressDto.setCountry("POL");
		addressDto.setAddressLine2("");

		ContactDto contactDto = new ContactDto();
		contactDto.setDetails(details);
		contactDto.setLabel(label);
		contactDto.setAddress(addressDto);

		ContactAddRequest dto = new ContactAddRequest(session, contactDto);
		
		// when
		contactService.add(dto);
				
		// then
		ContactFilterCriteria filters = ContactFilterCriteriaBuilder.contactFilterCriteria()
				.withDetails(details)
				.build();

		ContactFetchRequest request = new ContactFetchRequest();
		request.setSessionId(TestData.USER1_SESSIONID);
		request.setUsername(TestData.USER1_NAME);
		request.setFilters(filters);

		ContactFetchResponse response = contactService.fetch(request);

		assertEquals("should return this one element", response.getContacts().size(), 1);
		assertTrue("should found new item", response.getContacts().get(0).getDetails().equals(details));
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void editingContact() throws SessionServiceException, ContactServiceException {
		
		// given
		String label = "leszek";
		String newDetails = "AFK AFK";

		ContactFilterCriteria filters = ContactFilterCriteriaBuilder.contactFilterCriteria()
				.withLabel(label)
				.build();

		ContactFetchRequest request = new ContactFetchRequest();
		request.setSessionId(TestData.USER1_SESSIONID);
		request.setUsername(TestData.USER1_NAME);
		request.setFilters(filters);

		ContactFetchResponse responseFetch1 = contactService.fetch(request);

		ContactEditDto contactToEditDto = new ContactEditDto();
		contactToEditDto.setId(responseFetch1.getContacts().get(0).getId());
		contactToEditDto.setDetails(newDetails);
		contactToEditDto.setLabel(label);
		contactToEditDto.addEmail("leszek2@gmail.com");
		contactToEditDto.addFax("1234567890");
		contactToEditDto.addPhoneNumberToRemove(new PhoneNumberDto("+48", "500600700"));

		contactToEditDto.addEmailToRemove("leszek@gmail.com");
		contactToEditDto.addPhoneNumberToRemove(new PhoneNumberDto("+48", "600700800"));
		contactToEditDto.addFaxToRemove("0161 999 8888");
		
		ContactEditRequest requestEdit = new ContactEditRequest();
		requestEdit.setSessionId(TestData.USER1_SESSIONID);
		requestEdit.setUsername(TestData.USER1_NAME);
		requestEdit.setContactToEdit(contactToEditDto);

		// when
		ContactEditResponse responseEdit = contactService.edit(requestEdit);
				
		// then
		ContactFilterCriteria filters2 = ContactFilterCriteriaBuilder.contactFilterCriteria()
				.withDetails(newDetails)
				.build();

		ContactFetchRequest request2 = new ContactFetchRequest();
		request2.setSessionId(TestData.USER1_SESSIONID);
		request2.setUsername(TestData.USER1_NAME);
		request2.setFilters(filters2);

		ContactFetchResponse responseFetch2 = contactService.fetch(request2);

		assertEquals(responseFetch1.getContacts().get(0).getId(), responseFetch2.getContacts().get(0).getId());

		assertTrue("edition should be successful", responseEdit.isSuccess());
		assertEquals("should find one entity", responseFetch1.getContacts().size(), 1);

		assertTrue("search should be successful", responseFetch1.isSuccess());
		assertEquals("should find one entity", responseFetch1.getContacts().size(), 1);

		assertTrue("search should be successful", responseFetch2.isSuccess());
		assertEquals("should find one entity", responseFetch2.getContacts().size(), 1);

		assertTrue("should found updatedContact label", responseFetch2.getContacts().get(0).getLabel().equals(label));
		assertTrue("should found updatedContact details", responseFetch2.getContacts().get(0).getDetails().equals(newDetails));
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllContacts() throws SessionServiceException, ContactServiceException {

		// given
		ContactFilterCriteria filters = ContactFilterCriteriaBuilder
				.contactFilterCriteria()
				.build();

		ContactFetchRequest dto = new ContactFetchRequest();
		dto.setFilters(filters);
		dto.setSessionId(TestData.USER1_SESSIONID);
		dto.setUsername(TestData.USER1_NAME);

		// when
		ContactFetchResponse lst = contactService.fetch(dto);

		// then		
		assertTrue("should found all items", lst.isSuccess());
		assertEquals("should contains all 3 contacts", lst.getContacts().size(), 3);
	}
}
