package telephony.test.core.service;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.core.query.filter.SaleFilterCriteria;
import telephony.core.query.filter.SaleFilterCriteriaBuilder;
import telephony.core.service.dto.SaleAddDto;
import telephony.core.service.dto.SaleEditDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.test.BaseCoreTest;
import telephony.core.service.*;
import telephony.core.service.exception.SaleServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.core.data.TestData;
import telephony.core.service.dto.SessionDto;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

import static org.junit.Assert.*;

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
	public void fetchingAllSales() throws SessionServiceException, SaleServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		SaleFilterCriteria filters = SaleFilterCriteriaBuilder.saleFilterCriteria()
				.build();

		SalesFetchRequest fetchRequest = new SalesFetchRequest(session);
		fetchRequest.setFilters(filters);

		// when
		SalesFetchResponse fetchResponse = saleService.findSales(fetchRequest);

		// then
		assertNotNull(fetchResponse);
		assertTrue(fetchResponse.isSuccess());
		assertEquals(fetchResponse.getSales().size() , 2);
	}


	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingSalesWithLabel() throws SessionServiceException, SaleServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		SaleFilterCriteria filters = SaleFilterCriteriaBuilder.saleFilterCriteria()
				.withLabel(TestData.SALE_CIESZYN_LABEL)
				.build();

		SalesFetchRequest fetchRequest = new SalesFetchRequest(session);
		fetchRequest.setFilters(filters);

		// when
		SalesFetchResponse fetchResponse = saleService.findSales(fetchRequest);

		// then
		assertNotNull(fetchResponse);
		assertTrue(fetchResponse.isSuccess());
		assertEquals(fetchResponse.getSales().size() , 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingSalesWithLimits() throws SessionServiceException, SaleServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		SaleFilterCriteria filters = SaleFilterCriteriaBuilder.saleFilterCriteria()
				.withPerPage(1)
				.withPage(1)
				.build();

		SalesFetchRequest fetchRequest = new SalesFetchRequest(session);
		fetchRequest.setFilters(filters);

		// when
		SalesFetchResponse fetchResponse = saleService.findSales(fetchRequest);

		// then
		assertNotNull(fetchResponse);
		assertTrue(fetchResponse.isSuccess());
		assertEquals(fetchResponse.getSales().size() , 1);
		assertEquals(fetchResponse.getSales().get(0).getId(), new Long(1));
	}


	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingSalesWithMinMaxNumberOfProducts() throws SessionServiceException, SaleServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		SaleFilterCriteria filters = SaleFilterCriteriaBuilder.saleFilterCriteria()
				.withMinNumberOfProducts(5)
				.withMaxNumberOfProducts(7)
				.build();

		SalesFetchRequest fetchRequest = new SalesFetchRequest(session);
		fetchRequest.setFilters(filters);

		// when
		SalesFetchResponse fetchResponse = saleService.findSales(fetchRequest);

		// then
		assertNotNull(fetchResponse);
		assertTrue(fetchResponse.isSuccess());
		assertEquals(fetchResponse.getSales().size() , 1);
		assertEquals(fetchResponse.getSales().get(0).getId(), TestData.SALE2_ID);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingSaleDetails() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		SaleDetailsRequest detailsRequest = new SaleDetailsRequest(session);
		detailsRequest.setSaleId(TestData.SALE1_ID);

		// when
		SaleDetailsResponse detailsResponse = saleService.fetchDetails(detailsRequest);

		// then
		assertNotNull(detailsResponse);
		assertTrue(detailsResponse.isSuccess());
		assertEquals(detailsResponse.getDetailedSale().getId(), TestData.SALE1_ID);
		assertEquals(detailsResponse.getDetailedSale().getContact().getLabel(), TestData.CONTACT1_LABEL);
		assertEquals(detailsResponse.getDetailedSale().getStore().getLabel(), TestData.STORE_CIESZYN_LABEL);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void addingSale1() throws SaleServiceException, SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		SaleAddRequest addRequest = new SaleAddRequest(session);
		SaleAddDto addDto = new SaleAddDto();
		addDto.setContactId(TestData.CONTACT1_ID);
		addDto.setStoreId(TestData.STORE1_ID);
		addDto.setDateOut(new Date());
		addDto.addProductId(TestData.PRODUCT2_ID);
		addDto.addProductId(TestData.PRODUCT3_ID);
		addDto.addProductId(TestData.PRODUCT4_ID);
		addDto.setLabel("nowe zamowienie");
		addRequest.setSaleDto(addDto);

		// when
		SaleAddResponse resp = saleService.add(addRequest);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
	}

	@Test()
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void addingSale2() throws SaleServiceException, SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		SaleAddRequest addRequest = new SaleAddRequest(session);
		SaleAddDto addDto = new SaleAddDto();
		addDto.setContactId(TestData.CONTACT1_ID);
		addDto.setStoreId(TestData.STORE1_ID);
		addDto.setDateOut(new Date());
		addDto.addProductId(TestData.PRODUCT1_ID);
		addDto.addProductId(TestData.PRODUCT2_ID);
		addDto.addProductId(TestData.PRODUCT3_ID);
		addDto.setLabel("nowe zamowienie");
		addRequest.setSaleDto(addDto);

		// when
		SaleAddResponse resp = saleService.add(addRequest);

		// then
		assertNotNull(resp);
		assertFalse(resp.isSuccess());
		assertEquals(resp.getErrors().get(0).getFieldId(), "productsIds.1");
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void editingSale1() throws SaleServiceException, SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		SaleEditRequest editRequest = new SaleEditRequest(session);
		SaleEditDto editDto = new SaleEditDto();
		editDto.setSaleId(TestData.SALE1_ID);
		editDto.setLabel("zmieniony label");
		editDto.setDateOut(new DateTime().withDate(2010,10,10).toDate());
		editDto.addProductToAdd(TestData.PRODUCT2_ID);
		editDto.addProductToAdd(TestData.PRODUCT3_ID);
		editDto.addProductToRemove(TestData.PRODUCT29_ID);
		editRequest.setSaleEdit(editDto);

		// when
		SaleEditResponse resp = saleService.edit(editRequest);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void editingSale2() throws SaleServiceException, SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		SaleEditRequest editRequest = new SaleEditRequest(session);
		SaleEditDto editDto = new SaleEditDto();
		editDto.setSaleId(TestData.SALE1_ID);
		editDto.setLabel("zmieniony label");
		editDto.setDateOut(new DateTime().withDate(2010,10,10).toDate());
		editDto.addProductToAdd(TestData.PRODUCT2_ID);
		editDto.addProductToAdd(TestData.PRODUCT3_ID);
		editDto.addProductToRemove(TestData.PRODUCT1_ID);
		editRequest.setSaleEdit(editDto);

		// when
		SaleEditResponse resp = saleService.edit(editRequest);

		// then
		assertNotNull(resp);
		assertFalse(resp.isSuccess());
		assertEquals(resp.getErrors().get(0).getFieldId(), "productsToRemove.1");
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void deleteSale() throws SaleServiceException, SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		SaleDeleteRequest deleteRequest = new SaleDeleteRequest(session);
		deleteRequest.setSaleId(TestData.SALE1_ID);

		// when
		SaleDeleteResponse resp = saleService.delete(deleteRequest);

		// then
		assertNotNull(resp);
		assertTrue(resp.isSuccess());
	}

}
