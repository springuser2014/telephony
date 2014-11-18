package telephony.test.core.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.test.BaseCoreTest;
import telephony.core.service.ComplaintCommentService;
import telephony.core.service.ProductComplaintService;
import telephony.test.core.data.TestData;
import telephony.core.entity.jpa.ComplaintComment;
import telephony.core.entity.jpa.ProductComplaint;
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
	public void commentComplaintAsLogged() {
		
		// given
		long complaintId = 1L;
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		SessionDto session = new SessionDto();
		session.setSessionId(sessionId);
		session.setUsername(username);
		
		ComplaintComment cc = new ComplaintComment();
		cc.setAuthor("pawel");
		cc.setContent("aaaa");
		cc.setReportedDate(new Date());
		long countBefore = complaintCommentService.count(session);
		
		// when
		complaintCommentService.comment(session, cc, complaintId);
		ProductComplaint complaintAfter = productComplaintService.findById(complaintId);
		long countAfter = complaintCommentService.count(session);
		
		// then
		assertEquals(complaintAfter.getComments().size(), 3);
		assertEquals(countAfter - 1, countBefore);
	}


	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void commentComplaintAsAnonymousClient() {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		String hash = "ABC123456789000002";
		ComplaintComment cc = new ComplaintComment();
		cc.setAuthor("pawel");
		cc.setContent("aaaa");
		cc.setReportedDate(new Date());
		long countBefore = complaintCommentService.count(session);
		
		// when
		complaintCommentService.comment(hash, cc);
		long countAfter = complaintCommentService.count(session);
		
		// then
		assertEquals(countAfter - 1, countBefore);
	}

}
