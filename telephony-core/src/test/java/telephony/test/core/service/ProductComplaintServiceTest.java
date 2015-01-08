package telephony.test.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.core.query.filter.ProductComplaintFilterCriteria;
import telephony.core.query.filter.ProductComplaintFilterCriteriaBuilder;
import telephony.core.service.dto.ProductComplaintDto;
import telephony.core.service.dto.ProductComplaintEditDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.test.BaseCoreTest;
import telephony.core.service.ContactService;
import telephony.core.service.ProductComplaintService;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.core.data.TestData;
import telephony.core.entity.enumz.ComplaintStatus;
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
public class ProductComplaintServiceTest extends BaseCoreTest {

	@Inject
	private ContactService contactService;

	@Inject
	private ProductComplaintService complaintService;

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data"})
	public void fetchDetails1() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ProductComplaintDetailsFetchRequest fetchRequest = new ProductComplaintDetailsFetchRequest(session);
		fetchRequest.setComplaintId(TestData.COMPLAINT1_ID);

		// when
		ProductComplaintDetailsFetchResponse resp = complaintService.fetchDetails(fetchRequest);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
		assertEquals(resp.getDetailedComplaint().getComments().size(), 2);
	}


	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data"})
	public void fetch1() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ProductComplaintFilterCriteria filters = ProductComplaintFilterCriteriaBuilder.productComplaintFilterCriteria()
				.withProductId(TestData.PRODUCT1_ID)
				.build();

		ProductComplaintFetchRequest fetchRequest = new ProductComplaintFetchRequest(session);
		fetchRequest.setFilters(filters);

		// when
		ProductComplaintFetchResponse resp = complaintService.fetch(fetchRequest);

		// then
		assertNotNull(resp);
		assertEquals(resp.getComplaints().size(), 1);
		assertEquals(resp.getComplaints().get(0).getDescription(), TestData.COMPLAINT1_DESC);
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data"})
	public void report() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ReportProductComplaintRequest complaintRequest = new ReportProductComplaintRequest(session);
		long countBefore = complaintService.count(session);

		ProductComplaintDto complaintDto = new ProductComplaintDto();
		complaintDto.setDescription("aaa");
		complaintDto.setReportedDate(new Date());
		complaintDto.setStatus(ComplaintStatus.JUST_REPORTED);
		complaintDto.setTitle("bbb");
		complaintDto.setUniqueHash("qwertyuio12357890");
		complaintDto.setProductId(TestData.PRODUCT1_ID);
		complaintRequest.setComplaint(complaintDto);

		// when
		ReportComplaintResponse resp = complaintService.report(complaintRequest);
		long countAfter = complaintService.count(session);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
		assertEquals(countAfter - countBefore, 1L);
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data"})
	public void update1() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		ProductComplaintEditDto editDto = new ProductComplaintEditDto();
		editDto.setComplaintId(TestData.COMPLAINT1_ID);
		editDto.setDescription("nowy opis");

		ProductComplaintEditRequest editRequest = new ProductComplaintEditRequest(session);
		editRequest.setComplaint(editDto);

		// when
		ProductComplaintEditResponse resp = complaintService.editComplaint(editRequest);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
	}


	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data"})
	public void markAsInProgress() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ComplaintChangeStatusRequest changeStatusRequest = new ComplaintChangeStatusRequest(session);
		changeStatusRequest.setComplaintId(TestData.COMPLAINT1_ID);

		// when
		ComplaintChangeStatusResponse resp = complaintService.markAsInProgress(changeStatusRequest);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data"})
	public void markAsAccepted() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ComplaintChangeStatusRequest changeStatusRequest = new ComplaintChangeStatusRequest(session);
		changeStatusRequest.setComplaintId(TestData.COMPLAINT1_ID);

		// when
		ComplaintChangeStatusResponse resp = complaintService.markAsAccepted(changeStatusRequest);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());

	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data"})
	public void markAsRejected() throws SessionServiceException {


		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ComplaintChangeStatusRequest changeStatusRequest = new ComplaintChangeStatusRequest(session);
		changeStatusRequest.setComplaintId(TestData.COMPLAINT1_ID);

		// when
		ComplaintChangeStatusResponse resp = complaintService.markAsRejected(changeStatusRequest);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());

	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data"})
	public void markAsResolved() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ComplaintChangeStatusRequest changeStatusRequest = new ComplaintChangeStatusRequest(session);
		changeStatusRequest.setComplaintId(TestData.COMPLAINT1_ID);

		// when
		ComplaintChangeStatusResponse resp = complaintService.markAsResolved(changeStatusRequest);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());

	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data"})
	public void delete1() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ComplaintDeleteRequest deleteRequest = new ComplaintDeleteRequest(session);
		deleteRequest.setComplaintId(TestData.COMPLAINT1_ID);

		long countBefore = complaintService.count(session);

		// when
		complaintService.deleteComplaint(deleteRequest);
		long countAfter = complaintService.count(session);

		// then
		assertEquals(countBefore - countAfter, 1);

	}
}
