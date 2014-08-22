package telephony.core.service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.data.TestData;
import telephony.core.entity.jpa.Pricing;
import telephony.core.service.bean.Session;

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
public class PricingServiceTest extends BaseCoreTest {
	
	@Inject
	private PricingService pricingService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void counting() {
		
		// given
		
		// when 
		long pricingsOfNumber = pricingService.count();
		
		// then
		assertEquals(pricingsOfNumber, 112);		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findByDateRange1() {
		
		// given
		Date from = new DateTime()
					.withDate(2000, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		Date to = new DateTime()
					.withDate(2008, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
			
		// when		
		Collection<Pricing> coll = pricingService.findByDateRange(session, from, to);
		
		// then
		assertEquals(coll.size(), 8);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findByDateRange2() {
		
		// given
		Date from = null;
		
		Date to = new DateTime()
					.withDate(2008, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
			
		// when	
		Collection<Pricing> coll = pricingService.findByDateRange(session, from, to);
		
		// then
		assertEquals(coll.size(), 8);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findByDateRange3() {
		
		// given
		Date from = new DateTime()
					.withDate(2000, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		Date to = null;
		
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
			
		// when	
		Collection<Pricing> coll = pricingService.findByDateRange(session, from, to);
		
		// then
		assertEquals(coll.size(), 112);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findByDateRange4() {
		
		// given
		Date from = null;		
		Date to = null;
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when		
		Collection<Pricing> coll = pricingService.findByDateRange(session, from, to);
		
		// then
		assertEquals(coll.size(), 112);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findByDateRange5() {
		
		// given
		Date from = new DateTime()
					.withDate(1998, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		Date to = new DateTime()
					.withDate(2007, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
			
		// when		
		Collection<Pricing> coll = pricingService.findByDateRange(session, from, to);
		
		// then
		assertEquals(coll.size(), 0);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findByDateRange6() {
		
		// given
		Date from = new DateTime()
					.withDate(2003, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		Date to = new DateTime()
					.withDate(2012, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
			
		// when		
		Collection<Pricing> coll = pricingService.findByDateRange(session, from, to);
		
		// then
		assertEquals(coll.size(), 24);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findByDateRange7() {
		
		// given
		Date from = new DateTime()
					.withDate(2005, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		Date to = new DateTime()
					.withDate(2014, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
			
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when		
		Collection<Pricing> coll = pricingService.findByDateRange(session, from, to);
		
		// then
		assertEquals(coll.size(), 48);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findByDateRange8() {
		
		// given
		Date from = new DateTime()
					.withDate(2009, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		Date to = new DateTime()
					.withDate(2014, 01, 01)
					.withTime(0, 0, 0, 0)
					.toDate();
		
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
			
		// when		
		Collection<Pricing> coll = pricingService.findByDateRange(session, from, to);
		
		// then
		assertEquals(coll.size(), 32);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingById() {
		
		// given
		long id = 1;
		Date expectedFrom = new DateTime()
							.withDate(2005, 01, 01)
							.withTime(0, 0, 0, 0)
							.toDate();
		
		Date expectedTo = new DateTime()
							.withDate(2008, 01, 01)
							.withTime(0, 0, 0, 0)
							.toDate();
		
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		Pricing p = pricingService.findById(session, id);
		
		// then
		assertNotNull(p);
		assertEquals(expectedFrom, p.getFrom());		
		assertEquals(expectedTo, p.getTo());
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingByIds() {

		// given
		long id1 = 1, id2 = 2;
		Collection<Long> ids = Arrays.asList(id1, id2);
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		Collection<Pricing> coll = pricingService.findByIds(session, ids);
		
		// then
		assertNotNull(coll);
		assertEquals(2, coll.size());
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void update() {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id = 1;
		Pricing pricing1 = pricingService.findById(session, id);
		double rateBefore = pricing1.getRate(); 
				
		// when
		pricing1.setRate(200.0);
		pricingService.update(session, pricing1);
		Pricing pricing2 = pricingService.findById(session, id);
		double rateAfter = pricing2.getRate();

		// then
		assertTrue(rateAfter == 200.0d);
		assertTrue(rateBefore == 120.0d);		

	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void updateCollection() {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id1 = 1, id2 = 2;
		double expectedPrice = 200.0d;
		Collection<Long> ids = Arrays.asList(id1, id2);
		Collection<Pricing> coll1 = pricingService.findByIds(session, ids);
		
		// when
		for (Pricing p : coll1) {
			p.setRate(expectedPrice);
		}
		
		pricingService.update(session, coll1);
		
		// then
		Collection<Pricing> coll2 = pricingService.findByIds(session, ids);
		for (Pricing p : coll2) {
			assertTrue(p.getRate() == expectedPrice);
		}
		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void remove() {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id = 1;
		long countBefore = pricingService.count();
		Pricing p = pricingService.findById(session, id);
		
		// when
		pricingService.remove(session, p);
		
		// then
		long countAfter = pricingService.count();		
		assertTrue(countBefore - countAfter == 1);		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeCollection() {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id1 = 1, id2 = 2;
		Collection<Long> ids = Arrays.asList(id1, id2);
		long countBefore = pricingService.count();
		Collection<Pricing> pricings = pricingService.findByIds(session, ids);
		
		// when
		pricingService.remove(session, pricings);
		
		// then
		long countAfter = pricingService.count();		
		assertTrue(countBefore - countAfter == 2);		
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeById() {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id = 1;
		long countBefore = pricingService.count();
		
		// when
		pricingService.removeById(session, id);
		
		// then
		long countAfter = pricingService.count();		
		assertTrue(countBefore - countAfter == 1);		
	}
		
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void removeCollectionById() {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long id1 = 1, id2 = 2;
		Collection<Long> ids = Arrays.asList(id1, id2);
		long countBefore = pricingService.count();
		
		// when
		pricingService.removeByIds(session, ids);
		
		// then
		long countAfter = pricingService.count();		
		assertTrue(countBefore - countAfter == 2);	
	}
	
}