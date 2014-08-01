package telephony.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import telephony.core.entity.jpa.Tax;
import telephony.core.entity.jpa.TestEntity;

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
	private GenericService<TestEntity> genericService;
	
	/*
	 * TODO 1 write more tests for AbstractGenericService
	 * TODO 2 prepare tests with new TestEntity (not the tax entity)
	 */

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testSave() {
		
		// given
		
		long nbOfEntitiesBefore = genericService.count();
		TestEntity entity = new TestEntity();
		entity.setLabel("aaa");
		entity.setReportedDate(new Date());
		
		// when		
		genericService.save(entity);
		long nbOfEntitiesAfter = genericService.count();
		
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
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testCount() {
		
		// given
		
		// when		
		long taxesNumber = genericService.count();
		
		// then
		assertEquals(taxesNumber, 3);		
	}

	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testUpdate() {
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testBatchUpdate() {
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testRemove() {
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testBatchRemove() {
		
	}	

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testRemovById() {
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void testBatchRemoveByIds() {
		
	}
	
}
