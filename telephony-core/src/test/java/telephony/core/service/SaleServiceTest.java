package telephony.core.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.BaseCoreTest;
import telephony.core.data.TestData;
import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.service.dto.Session;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SaleServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class SaleServiceTest extends BaseCoreTest {
	
	@Inject
	private SaleService saleService;
	
	@Inject
	private SessionService sessionService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private ContactService contactService;
	
	@Inject
	private StoreService storeService;
	
	@Inject
	private ProductService productService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllSale() throws SessionServiceException, SaleServiceException {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long count = saleService.count(session);
		
		// when
		List<Sale> lst = saleService.find(session);
		
		// then
		assertTrue("Should return exact number of sales", 
				count == 2 && lst.size() == count);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void deletingSale() throws SessionServiceException, SaleServiceException {

		// given
		Session session = Session.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		long countBefore = saleService.count(session);
		long saleId = 1L;
		Sale saleToCancel = saleService.findById(session, saleId);
		
		// when
		saleService.delete(null, saleToCancel);
		
		// then
		long countAfter = saleService.count(session);
		assertTrue("should decreased number of sales", countBefore - countAfter == 1);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void addingNewSale() 
			throws SessionServiceException, ContactServiceException, SaleServiceException {

		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		
		Store store = storeService.findByLabel(null, TestData.STORE1_LABEL);
		Contact contact = contactService.findByLabel(null, TestData.CONTACT1_LABEL);
		List<String> imeis = new ArrayList<String>();
		imeis.add("123456789000002");
		imeis.add("123456789000005");
		imeis.add("123456789000006");
		
		List<Product> products =  productService.findByIMEIs(null, imeis);
		
		Sale sale = new Sale();
		sale.setLabel("nowa dostawa");
		sale.setDateOut(new Date());
		
		// when
		saleService.add(null, sale, products, store.getId(), contact.getId());
		
		Sale addedSale = saleService.findByLabel(null, "nowa dostawa");
		
		// then
		assertTrue("should return new sale", addedSale != null);
		assertTrue("should sold 3 products", addedSale.getProducts().size() == 3);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void editingExistingSale() throws SessionServiceException, SaleServiceException {

		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Sale saleToUpdate = saleService.findByLabel(null, TestData.SALE1_LABEL);
		saleToUpdate.setDateOut(new Date(10000));
		// when
		saleService.update(null, saleToUpdate);
		Sale sale = saleService.findByLabel(null, TestData.SALE1_LABEL);
		
		// then
		assertTrue("should return changed ", sale.getDateOut().getTime() == 10000);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findById() throws SessionServiceException, SaleServiceException {
		
		// given
		String username = TestData.USER1_NAME;
		String sessionId = TestData.USER1_SESSIONID;
		Long saleId = 1L;
		
		// when
		Sale sale = saleService.findById(null, saleId);
		
		// then
		assertTrue("should not be null ", sale != null);
		assertTrue("should have appropriate label", sale.getLabel().contains("nowy rok cieszyn"));
	}

}
