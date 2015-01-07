package telephony.test.core.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.core.service.dto.AnonymousComplaintCommentDto;
import telephony.core.service.dto.BaseComplaintCommentDto;
import telephony.core.service.dto.ComplaintCommentDto;
import telephony.core.service.dto.request.AnonymousComplaintCommentRequest;
import telephony.core.service.dto.request.ComplaintCommentRequest;
import telephony.core.service.dto.response.AnonymousComplaintCommentResponse;
import telephony.core.service.dto.response.ComplaintCommentResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.BaseCoreTest;
import telephony.core.service.ComplaintCommentService;
import telephony.core.service.ProductComplaintService;
import telephony.test.core.data.TestData;
import telephony.core.entity.jpa.ComplaintComment;
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
public class ComplaintCommentServiceTest extends BaseCoreTest {

	@Inject
	private ProductComplaintService productComplaintService;
	
	@Inject
	private ComplaintCommentService complaintCommentService;

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void commentComplaintAsLogged() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME,TestData.USER1_SESSIONID);
		ComplaintCommentRequest commentRequest = new ComplaintCommentRequest(session);
		ComplaintCommentDto cc = new ComplaintCommentDto();
		cc.setAuthor("pawel");
		cc.setComment("aaaa");
		cc.setComplaintId(TestData.COMPLAINT1_ID);
		commentRequest.setComplaintComment(cc);

		long countBefore = complaintCommentService.count(session);

		// when
		ComplaintCommentResponse resp = complaintCommentService.comment(commentRequest);
		long countAfter = complaintCommentService.count(session);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
		assertEquals(countAfter - 1, countBefore);
	}


	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void commentComplaintAsAnonymousClient() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME,TestData.USER1_SESSIONID);

		AnonymousComplaintCommentRequest commentRequest = new AnonymousComplaintCommentRequest();
		AnonymousComplaintCommentDto cc = new AnonymousComplaintCommentDto();
		cc.setAuthor("pawel");
		cc.setComment("aaaa");
		cc.setHashUnique("ABC123456789000002");
		long countBefore = complaintCommentService.count(session);
		commentRequest.setComplaintComment(cc);

		// when
		AnonymousComplaintCommentResponse resp = complaintCommentService.comment(commentRequest);
		long countAfter = complaintCommentService.count(session);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
		assertEquals(countAfter - 1, countBefore);
	}

}
