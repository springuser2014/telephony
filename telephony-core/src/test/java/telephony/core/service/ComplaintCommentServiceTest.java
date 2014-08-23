package telephony.core.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.data.TestData;
import telephony.core.entity.jpa.ComplaintComment;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.service.bean.Session;

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
		
		Session session = new Session();
		session.sessionId(sessionId);
		session.username(username);
		
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
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
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
