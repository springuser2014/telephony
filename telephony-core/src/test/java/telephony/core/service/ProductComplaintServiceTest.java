package telephony.core.service;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.entity.jpa.Complaint;
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
public class ProductComplaintServiceTest extends BaseCoreTest {
	
	@Inject
	private ProductComplaintService complaintService;

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void report(Session session, ProductComplaint complaint) {
		// TODO Auto-generated method stub
		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public Complaint update(Session session, ProductComplaint complaint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public Collection<ProductComplaint> update(Session session,
			Collection<ProductComplaint> complaints) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void markAsInProgress(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void markAsAccepted(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void markAsRejected(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void markAsResolved(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeById(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeByIds(Session session, Collection<Long> complaintIds) {
		// TODO Auto-generated method stub
		
	}
}

