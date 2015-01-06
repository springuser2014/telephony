package telephony.test.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import telephony.core.service.dto.SaleComplaintDto;
import telephony.core.service.dto.SaleComplaintEditDto;
import telephony.core.service.dto.request.ComplaintChangeStatusRequest;
import telephony.core.service.dto.request.ComplaintDeleteRequest;
import telephony.core.service.dto.request.ReportSaleComplaintRequest;
import telephony.core.service.dto.request.SaleComplaintEditRequest;
import telephony.core.service.dto.response.ComplaintChangeStatusResponse;
import telephony.core.service.dto.response.SaleComplaintEditResponse;
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
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		SaleComplaintDto complaint = new SaleComplaintDto();
		complaint.setDescription("aaa");
		complaint.setSaleId(TestData.SALE1_ID);
		complaint.setReportedDate(new Date());
		complaint.setStatus(ComplaintStatus.JUST_REPORTED);
		complaint.setTitle("bbb");
		complaint.setUniqueHash("qwertyuio12357890");

		long countBefore = complaintService.count(session);

		ReportSaleComplaintRequest reportRequest = new ReportSaleComplaintRequest(session);
		reportRequest.setComplaint(complaint);

		// when
		complaintService.report(reportRequest);
		long countAfter = complaintService.count(session);
		
		// then
		assertEquals(countAfter - countBefore, 1L);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void edit1() throws SessionServiceException {
	
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		SaleComplaintEditDto editComplaintDto = new SaleComplaintEditDto();
		editComplaintDto.setComplaintId(TestData.COMPLAINT7_ID);
		editComplaintDto.setReportedDate(new Date());
		editComplaintDto.setUniqueHash("qwertyuio12357890");

		SaleComplaintEditRequest editRequest = new SaleComplaintEditRequest(session);
		editRequest.setComplaint(editComplaintDto);

		// when
		SaleComplaintEditResponse resp = complaintService.editComplaint(editRequest);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void markAsInProgress() throws SessionServiceException {
		
		// when
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
 		ComplaintChangeStatusRequest request = new ComplaintChangeStatusRequest(session);
		request.setComplaintId(TestData.COMPLAINT7_ID);

		// when
		ComplaintChangeStatusResponse resp = complaintService.markAsInProgress(request);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void markAsAccepted() throws SessionServiceException {

		// when
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ComplaintChangeStatusRequest request = new ComplaintChangeStatusRequest(session);
		request.setComplaintId(TestData.COMPLAINT7_ID);

		// when
		ComplaintChangeStatusResponse resp = complaintService.markAsAccepted(request);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void markAsRejected() throws SessionServiceException {

		// when
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ComplaintChangeStatusRequest request = new ComplaintChangeStatusRequest(session);
		request.setComplaintId(TestData.COMPLAINT7_ID);

		// when
		ComplaintChangeStatusResponse resp = complaintService.markAsRejected(request);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void markAsResolved() throws SessionServiceException {

		// when
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ComplaintChangeStatusRequest request = new ComplaintChangeStatusRequest(session);
		request.setComplaintId(TestData.COMPLAINT7_ID);

		// when
		ComplaintChangeStatusResponse resp = complaintService.markAsResolved(request);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void delete1() throws SessionServiceException {

		// when
		SessionDto session = SessionDto.create(TestData.USER1_NAME,TestData.USER1_SESSIONID);
		ComplaintDeleteRequest deleteRequest = new ComplaintDeleteRequest(session);
		deleteRequest.setComplaintId(TestData.COMPLAINT7_ID);

		long countBefore = complaintService.count(session);

		// when
		complaintService.deleteComplaint(deleteRequest);
		long countAfter = complaintService.count(session);
		
		// then
		assertEquals(countBefore - countAfter, 1);
		
	}
}
