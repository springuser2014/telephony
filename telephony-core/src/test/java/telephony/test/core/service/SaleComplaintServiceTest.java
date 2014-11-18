package telephony.test.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.test.BaseCoreTest;
import telephony.core.service.ContactService;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.core.data.TestData;
import telephony.core.entity.enumz.ComplaintStatus;
import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.service.dto.SessionDto;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;


/**
 * asd.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class SaleComplaintServiceTest extends BaseCoreTest {
	
	@Inject
	private ContactService contactService;
	
	@Inject
	private SaleComplaintService complaintService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void report() throws SessionServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		SessionDto session = new SessionDto();
		session.setSessionId(sessionId);
		session.setUsername(username);
		
		Contact contact = contactService.findById(null, 1L); 
		SaleComplaint complaint = new SaleComplaint();
		complaint.setDescription("aaa");
		complaint.setItemId("123456789000000");
		complaint.setReportedDate(new Date());
		complaint.setStatus(ComplaintStatus.JUST_REPORTED);
		complaint.setTitle("bbb");
		complaint.setUniqueHash("qwertyuio12357890");
		complaint.setContact(contact);
		long countBefore = complaintService.count(session);

		// when
		complaintService.report(session, complaint);
		long countAfter = complaintService.count(session);
		
		// then
		assertEquals(countAfter - countBefore, 1L);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void update() {
	
		// given
		Date reportedDate = new Date();
		String uniqueHash = "qwertyuio12357890";
		
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		SessionDto session = new SessionDto();
		session.setSessionId(sessionId);
		session.setUsername(username);
 
		SaleComplaint complaint1 = complaintService.findById(7L);
		complaint1.setReportedDate(reportedDate);
		complaint1.setUniqueHash(uniqueHash);
		
		// when
		complaintService.update(session, complaint1);
		
		SaleComplaint complaint2 = complaintService.findById(7L);
		
		// then
		assertEquals(complaint2.getUniqueHash(), uniqueHash);
		assertEquals(complaint2.getReportedDate().getTime(), reportedDate.getTime());
	}


	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void updateCollection() {

		// given
		Date reportedDate = new Date();
		String uniqueHash1 = "qwertyuio12357890";
		String uniqueHash2 = "asdfghjk234567890";
		long complaintId1 = 7L, complaintId2 = 8L;
		
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		SessionDto session = new SessionDto();
		session.setSessionId(sessionId);
		session.setUsername(username);
 
		SaleComplaint complaint1 = complaintService.findById(complaintId1);
		complaint1.setReportedDate(reportedDate);
		complaint1.setUniqueHash(uniqueHash1);
		
		SaleComplaint complaint2 = complaintService.findById(complaintId2);
		complaint2.setReportedDate(reportedDate);
		complaint2.setUniqueHash(uniqueHash2);
		
		Collection<SaleComplaint> complaints = Arrays.asList(complaint1, complaint2); 
		
		// when
		complaintService.update(session, complaints);
		
		SaleComplaint complaint3 = complaintService.findById(complaintId1);
		SaleComplaint complaint4 = complaintService.findById(complaintId2);
		
		// then
		assertEquals(complaint3.getUniqueHash(), uniqueHash1);
		assertEquals(complaint4.getUniqueHash(), uniqueHash2);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void markAsInProgress() {
		
		// when
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		SessionDto session = new SessionDto();
		session.setSessionId(sessionId);
		session.setUsername(username);
 
		long complaintId = 7L;
		
		// when
		complaintService.markAsInProgress(session, complaintId);
		SaleComplaint complaint = complaintService.findById(complaintId);
		
		// then
		assertTrue(complaint.getStatus() == ComplaintStatus.IN_PROGRESS);
		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void markAsAccepted() {

		// when
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		SessionDto session = new SessionDto();
		session.setSessionId(sessionId);
		session.setUsername(username);
 
		long complaintId = 7L;
		
		// when
		complaintService.markAsAccepted(session, complaintId);
		SaleComplaint complaint = complaintService.findById(complaintId);
		
		// then
		assertTrue(complaint.getStatus() == ComplaintStatus.ACCEPTED);

	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void markAsRejected() {

		// when
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		SessionDto session = new SessionDto();
		session.setSessionId(sessionId);
		session.setUsername(username);
 
		long complaintId = 7L;
		
		// when
		complaintService.markAsRejected(session, complaintId);
		SaleComplaint complaint = complaintService.findById(complaintId);
		
		// then
		assertTrue(complaint.getStatus() == ComplaintStatus.REJECTED);
		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void markAsResolved() {

		// when
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		SessionDto session = new SessionDto();
		session.setSessionId(sessionId);
		session.setUsername(username);
 
		long complaintId = 7L;
		
		// when
		complaintService.markAsResolved(session, complaintId);
		SaleComplaint complaint = complaintService.findById(complaintId);
		
		// then
		assertTrue(complaint.getStatus() == ComplaintStatus.RESOLVED);

	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeById() {

		// when
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		SessionDto session = new SessionDto();
		session.setSessionId(sessionId);
		session.setUsername(username);
		
		long countBefore = complaintService.count(session);
		long complaintId = 7L;
		
		// when
		complaintService.removeById(session, complaintId);
		long countAfter = complaintService.count(session);
		
		// then
		assertEquals(countBefore - countAfter, 1);
		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeByIds() {
		// when
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		SessionDto session = new SessionDto();
		session.setSessionId(sessionId);
		session.setUsername(username);
		
		long countBefore = complaintService.count(session);
		long complaintId1 = 7L, complaintId2 = 8L;
		Collection<Long> ids = Arrays.asList(complaintId1, complaintId2);
		
		// when
		complaintService.removeByIds(session, ids);
		long countAfter = complaintService.count(session);
		
		// then
		assertEquals(countBefore - countAfter, 2);
		
	}
}
