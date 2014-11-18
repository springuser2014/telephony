package telephony.test.core.service;

import static org.junit.Assert.assertTrue;

import telephony.test.BaseCoreTest;
import telephony.core.service.ComplaintService;
import telephony.test.core.data.TestData;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.service.dto.SessionDto;

import com.google.inject.Inject;


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
		
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long count = complaintService.count(session);
		
		assertTrue(count == 8);		
	}
}