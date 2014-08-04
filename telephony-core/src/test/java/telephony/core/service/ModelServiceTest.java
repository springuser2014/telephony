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
import telephony.core.entity.jpa.Model;

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
		
		// when
		Model model = modelService.findByLabel(label);		
		
		// then
		assertNotNull(model);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingById() {
		
		// given
		long id = 1;
		String expected = "6610s";
		
		// when
		Model model = modelService.findById(id);
		
		// then
		assertNotNull(model);
		assertEquals(model.getLabel(), expected);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingByIds() {
		
		// given
		List<Long> ids = Arrays.asList(1L, 2L, 3L);
		
		// when
		Collection<Model> models = modelService.findByIds(ids);
		
		// then
		assertNotNull(models);		
		assertEquals(models.size(), 3);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void counting() {
		
		// given
		
		// when
		long count = modelService.count();		
		
		// then
		assertEquals(count, 8);
	}
		
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void update() {
	
		// given
		long id = 1;
		String newLabel = "newlabel";
		Model model = modelService.findById(id);
		Model changedModel = null;
		
		// when
		model.setLabel(newLabel);
		modelService.update(model);
		changedModel = modelService.findByLabel(newLabel);
		
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
		Model model1 = modelService.findById(id1);
		Model model2 = modelService.findById(id2);
		
		List<Model> coll = Arrays.asList(model1, model2);
		
		Model changedModel1 = null;
		Model changedModel2 = null;
		
		// when
		model1.setLabel(newLabel1);
		model2.setLabel(newLabel2);
		modelService.update(coll);
		changedModel1 = modelService.findByLabel(newLabel1);
		changedModel2 = modelService.findByLabel(newLabel2);
		
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
		
		// when
		modelService.removeById(id1);
		
		// then
		// exception should arise
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeById() {
		
		// given
		Model model = modelService.findByLabel("iphone 6g");
		long id = model.getId();
		long countBefore = modelService.count();
		
		// when
		modelService.removeById(id);
		long countAfter = modelService.count();
		
		// then
		assertEquals(countBefore  - countAfter, 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeCollectionById() {
		
		// given
		long id7 = 7, id8 = 8;
		long countBefore = modelService.count();
		List<Long> ids = Arrays.asList(id7, id8);
		
		// when
		modelService.removeById(ids);
		long countAfter = modelService.count();
		
		// then
		assertEquals(countBefore - countAfter, 2);
		
	}
	
	@Test(expected = PersistenceException.class)
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeCollectionById_expectConstraint() {
		
		// given
		long id1 = 1, id2 = 2;
		List<Long> ids = Arrays.asList(id1, id2);
		
		// when
		modelService.removeById(ids);
		
		// then
		// exception should arise
	}
}
