package telephony.core.service;

import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.data.TestData;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.service.bean.Session;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;


/**
 * asd.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/META-INF/context.xml" })
//@TestExecutionListeners({
//	DependencyInjectionTestExecutionListener.class,
//    FlywayDBUnitTestExecutionListener.class 
//})
//@FlywayTest
public class ComplaintServicest extends BaseCoreTest {
	
	@Inject
	private ComplaintService<ProductComplaint> complaintService;

//	@Test
//	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testCount() {
		
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long count = complaintService.count(session);
		
		assertTrue(count == 8);		
	}
}