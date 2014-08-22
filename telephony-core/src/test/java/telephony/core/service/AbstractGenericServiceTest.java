package telephony.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.data.TestData;
import telephony.core.entity.jpa.Tax;
import telephony.core.entity.jpa.TestEntity;
import telephony.core.service.bean.Session;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class AbstractGenericServiceTest extends BaseCoreTest {
	
	@Inject
	private TestEntityService genericService;
	
	/*
	 * TODO 1 write more tests for AbstractGenericService
	 * TODO 2 prepare tests with new TestEntity (not the tax entity)
	 */

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testSave() {
		
		// given		
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long nbOfEntitiesBefore = genericService.count(session);
		TestEntity entity = new TestEntity();
		entity.setLabel("aaa");
		entity.setReportedDate(new Date());
		
		// when		
		genericService.save(entity);
		long nbOfEntitiesAfter = genericService.count(session);
		
		// then
		assertEquals(nbOfEntitiesAfter - nbOfEntitiesBefore, 1);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testFindById() {
		
		// given
		long id = 2;
		
		// when
		TestEntity tax = (TestEntity) genericService.findById(id);
		
		// then
		Date date = new DateTime()
					.withDate(2000, 1, 1)
					.withTime(0, 0, 0, 0)
					.toDate();
				
		assertNotNull(tax);
		assertTrue(tax.getId() == 2);
		assertEquals(tax.getLabel(), "drugi");
		assertEquals(tax.getReportedDate().getTime(), date.getTime());		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testFindByIds() {
		
		// given
		long id1 = 1, id2 = 2;
		Collection<Long> ids = Arrays.asList(id1, id2);
		Collection<String> expectedLabels = Arrays.asList("pierwszy", "drugi");
		
		// when
		Collection<TestEntity> coll = genericService.findByIds(ids);
		
		// then
		assertTrue(coll.size() == 2);
			
		for (TestEntity entity : coll) {			
			assertTrue(expectedLabels.contains(entity.getLabel()));
		}
	
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testCount() {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when		
		long taxesNumber = genericService.count(session);
		
		// then
		assertEquals(taxesNumber, 3);		
	}

	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testUpdate() {
		
		// given		
		long id = 1;
		String expectedLabel = "aaaa";
		TestEntity entity1 = genericService.findById(id);
		entity1.setLabel(expectedLabel);
		
		// when		
		genericService.update(entity1);
		TestEntity entity2 = genericService.findById(id);
		
		// then
		assertEquals(entity2.getLabel(), expectedLabel);

	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testBatchUpdate() {
		
		// given		
		long id1 = 1, id2 = 2;
		TestEntity entity1 = genericService.findById(id1);
		TestEntity entity2 = genericService.findById(id2);
		String expectedLabel1 = "aaaaa";
		String expectedLabel2 = "bbbbb";
		entity1.setLabel(expectedLabel1);
		entity2.setLabel(expectedLabel2);		
		Collection<TestEntity> coll = Arrays.asList(entity1, entity2);
		Collection<Long> ids = Arrays.asList(id1, id2);
		Collection<String> expectedLabels = Arrays.asList(expectedLabel1, expectedLabel2);
				
		// when		
		genericService.batchUpdate(coll);
		Collection<TestEntity> changedColl = genericService.findByIds(ids);
		
		// then
		assertTrue(changedColl.size() == 2);
		for (TestEntity e : changedColl) {
			assertTrue(expectedLabels.contains(e.getLabel()));
		}
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testRemove() {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id = 1;
		long countBefore = genericService.count(session);
		TestEntity entity = genericService.findById(id);
		
		// when	
		genericService.remove(entity);
		long countAfter = genericService.count(session);
		
		// then
		assertEquals(countBefore - countAfter, 1);	
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testBatchRemove() {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id1 = 1, id2 = 2;
		long countBefore = genericService.count(session);
		Collection<Long> ids = Arrays.asList(id1, id2);
		Collection<TestEntity> coll = genericService.findByIds(ids);
		
		// when	
		genericService.batchRemove(coll);
		long countAfter = genericService.count(session);
		
		// then
		assertEquals(countBefore - countAfter, 2);
	}	

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testRemovById() {
		
		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id = 1;
		long countBefore = genericService.count(session);
		
		// when	
		genericService.removeById(id);
		long countAfter = genericService.count(session);
		
		// then
		assertEquals(countBefore - countAfter, 1);	
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testBatchRemoveByIds() {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id1 = 1, id2 = 2;
		long countBefore = genericService.count(session);
		Collection<Long> ids = Arrays.asList(id1, id2);
		
		// when	
		genericService.batchRemoveByIds(ids);
		long countAfter = genericService.count(session);
		
		// then
		assertEquals(countBefore - countAfter, 2);
	}	
	
}
