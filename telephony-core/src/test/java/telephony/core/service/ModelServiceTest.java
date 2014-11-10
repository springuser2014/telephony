package telephony.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.data.TestData;
import telephony.core.entity.jpa.Model;
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
public class ModelServiceTest extends BaseCoreTest {
	
	@Inject
	private ModelService modelService;

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingModelByLabel() {
		
		// given
		String label = "iphone 4s";
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		Model model = modelService.findByLabel(session, label);		
		
		// then
		assertNotNull(model);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingById() {
		
		// given
		long id = 1;
		String expected = "6610s";
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		Model model = modelService.findById(session, id);
		
		// then
		assertNotNull(model);
		assertEquals(model.getLabel(), expected);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingByIds() {
		
		// given
		List<Long> ids = Arrays.asList(1L, 2L, 3L);
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		Collection<Model> models = modelService.findByIds(session, ids);
		
		// then
		assertNotNull(models);		
		assertEquals(models.size(), 3);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void counting() {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		long count = modelService.count(session);		
		
		// then
		assertEquals(count, 8);
	}
		
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void update() {
	
		// given
		long id = 1;
		String newLabel = "newlabel";
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		Model model = modelService.findById(session, id);
		Model changedModel = null;
		
		// when
		model.setLabel(newLabel);
		modelService.edit(session, model);
		changedModel = modelService.findByLabel(session, newLabel);
		
		// then
		assertNotNull(changedModel);
		assertTrue(changedModel.getId() == id);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void updateCollection() {
		
		// given
		long id1 = 1, id2 = 2;
		String newLabel1 = "newlabel1";
		String newLabel2 = "newlabel2";
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		Model model1 = modelService.findById(session, id1);
		Model model2 = modelService.findById(session, id2);
		
		List<Model> coll = Arrays.asList(model1, model2);
		
		Model changedModel1 = null;
		Model changedModel2 = null;
		
		// when
		model1.setLabel(newLabel1);
		model2.setLabel(newLabel2);
		modelService.update(session, coll);
		changedModel1 = modelService.findByLabel(session, newLabel1);
		changedModel2 = modelService.findByLabel(session, newLabel2);
		
		// then
		assertNotNull(changedModel1);
		assertNotNull(changedModel2);
		assertTrue(changedModel1.getId() == id1);
		assertTrue(changedModel2.getId() == id2);
		
	}
	
	@Test(expected = PersistenceException.class)
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeById_exceptConstraint() {
		
		// given
		long id1 = 1;
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		modelService.removeById(session, id1);
		
		// then
		// exception should arise
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void remove() {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		Model model = modelService.findByLabel(session, "iphone 6g");
		long countBefore = modelService.count(session);
		
		// when
		modelService.remove(session, model);
		long countAfter = modelService.count(session);
		
		// then
		assertEquals(countBefore  - countAfter, 1);
	}

	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeById() {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		Model model = modelService.findByLabel(session, "iphone 6g");
		long id = model.getId();
		long countBefore = modelService.count(session);
		
		// when
		modelService.removeById(session, id);
		long countAfter = modelService.count(session);
		
		// then
		assertEquals(countBefore  - countAfter, 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeCollectionById() {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id7 = 7, id8 = 8;
		long countBefore = modelService.count(session);
		List<Long> ids = Arrays.asList(id7, id8);
		
		// when
		modelService.removeById(session, ids);
		long countAfter = modelService.count(session);
		
		// then
		assertEquals(countBefore - countAfter, 2);
		
	}
	
	@Test(expected = PersistenceException.class)
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeCollectionById_expectConstraint() {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id1 = 1, id2 = 2;
		List<Long> ids = Arrays.asList(id1, id2);
		
		// when
		modelService.removeById(session, ids);
		
		// then
		// exception should arise
	}
}
