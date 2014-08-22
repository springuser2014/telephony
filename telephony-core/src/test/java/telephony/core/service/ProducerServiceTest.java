package telephony.core.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.data.TestData;
import telephony.core.entity.jpa.Producer;
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
public class ProducerServiceTest extends BaseCoreTest {
	
	@Inject
	private ProducerService producerService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void counting() {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		long count = producerService.count(session);
		
		// then
		assertEquals(count, 4);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingProducerByLabel() {
		
		// given
		String label = "nokia";
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		Producer producer = producerService.findByLabel(session, label);
		
		// then
		assertNotNull(producer);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingById() {
		
		// given
		long id = 1;
		String expected = "nokia";
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		Producer producer = producerService.findById(session, id);
		
		// then
		assertNotNull(producer);
		assertEquals(producer.getLabel(), expected);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingByIds() {
		
		// given
		long id1 = 1, id2 = 2;
		Collection<Long> ids = Arrays.asList(id1, id2);
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		Collection<Producer> producers = producerService.findById(session, ids);
		
		// then
		assertEquals(producers.size(), 2);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void update() {
	
		// given
		long id = 1;
		String newLabel = "newlabel";
		Producer changedProducer = null;
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		Producer producer = producerService.findById(session, id);
		producer.setLabel(newLabel);
		producerService.update(session, producer);
		changedProducer = producerService.findByLabel(session, newLabel);
		
		// then
		assertNotNull(changedProducer);
		assertTrue(changedProducer.getId() == id);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void updateCollection() {
	
		// given
		long id1 = 1, id2 = 2;
		String newLabel1 = "newlabel1";
		String newLabel2 = "newlabel2";
		Producer changedProducer1 = null;
		Producer changedProducer2 = null;
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		Producer producer1 = producerService.findById(session, id1);
		Producer producer2 = producerService.findById(session, id2);
		
		producer1.setLabel(newLabel1);
		producer2.setLabel(newLabel2);
		Collection<Producer> coll = Arrays.asList(producer1, producer2);
		producerService.update(session, coll);
		
		changedProducer1 = producerService.findByLabel(session, newLabel1);
		changedProducer2 = producerService.findByLabel(session, newLabel2);
		
		// then
		assertNotNull(changedProducer1);
		assertTrue(changedProducer1.getId() == id1);
		assertNotNull(changedProducer2);
		assertTrue(changedProducer2.getId() == id2);
	
	}

	@Test(expected = PersistenceException.class) 
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeById_expectConstraint() {
		
		// given
		long id = 1;
		
		// when
		producerService.removeById(null, id);
		
		// then
		// exception should arise
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeCollectionById() {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id3 = 3, id4 = 4;
		long countBefore = producerService.count(session);
		Collection<Long> ids = Arrays.asList(id3, id4);
		
		// when
		producerService.removeById(session, ids);
		long countAfter = producerService.count(session); 
		
		// then
		assertEquals(countBefore - countAfter, 2);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeById() {
	
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id = 3;
		long countBefore = producerService.count(session);
		
		// when
		producerService.removeById(session, id);
		long countAfter = producerService.count(session); 
		
		// then
		assertEquals(countBefore - countAfter, 1);
	}
	
	@Test(expected = PersistenceException.class)
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeCollectionById_expectConstraint() {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id1 = 1, id2 = 2;
		Collection<Long> ids = Arrays.asList(id1, id2);
		
		// when
		producerService.removeById(session, ids);
		
		// then
		// exception should arise	
	}

}
