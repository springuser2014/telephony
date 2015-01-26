package telephony.test.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.core.query.filter.ModelFilterCriteria;
import telephony.core.query.filter.ModelFilterCriteriaBuilder;
import telephony.core.service.dto.*;
import telephony.test.BaseCoreTest;
import telephony.core.service.*;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.core.data.TestData;
import telephony.core.query.filter.DeliveryFilterCriteria;
import telephony.core.query.filter.DeliveryFilterCriteriaBuilder;
import telephony.core.service.exception.DeliveryServiceException;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;

import javax.persistence.PersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class DeliveryServiceTest extends BaseCoreTest {
	
	@Inject
	private DeliveryService deliveryService;
	
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
	
	@Inject
	private ModelService modelService;

	public ModelDto getNokia3310() throws SessionServiceException {

		SessionDto sessionDto = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		ModelFilterCriteria filters = ModelFilterCriteriaBuilder.modelFilterCriteria()
				.withLabel("3310")
				.build();

		ModelFetchRequest request = new ModelFetchRequest(sessionDto);
		request.setFilters(filters);

		ModelFetchResponse resp = modelService.fetch(request);

		ModelDto model = resp.getModels().get(0);

		return model;
	}

	public ModelDto getIphone4S() throws SessionServiceException {

		SessionDto sessionDto = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		ModelFilterCriteria filters = ModelFilterCriteriaBuilder.modelFilterCriteria()
				.withLabel("iphone 4s")
				.build();

		ModelFetchRequest request = new ModelFetchRequest(sessionDto);
		request.setFilters(filters);

		ModelFetchResponse resp = modelService.fetch(request);

		ModelDto model = resp.getModels().get(0);

		return model;
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void addNewDelivery1() throws SessionServiceException, DeliveryServiceException, ParseException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		long deliveriesBefore = deliveryService.count(session);
		long productsBefore = productService.count(session);
		
		DeliveryAddRequest requestDto = new DeliveryAddRequest(session);

		DeliveryAddDto deliveryDto = new DeliveryAddDto();
		
		Date priceTo = new DateTime().withDate(2015, 12, 31).withTime(06, 30, 0, 0).toDate();

		PricingDto pricing = new PricingDto();
		pricing.setFrom(new Date());
		pricing.setTo(priceTo);
		pricing.setRate(290.0d);

		ProductTaxDto tax = new ProductTaxDto();
		tax.setTaxId(TestData.TAX7_ID);
		tax.setFrom(new Date());
		tax.setTo(priceTo);
		
		List<ProductAddDto> products = new ArrayList<ProductAddDto>();
		ProductAddDto p1 = new ProductAddDto();
		p1.setProducer("nokia");
		p1.setModel("3310");
		p1.setColor("black");
		p1.setImei("123456789000050");
		p1.setCurrentPrice(pricing);
		p1.setPriceIn(200.0d);
		p1.setProductTax(tax);
		p1.setProductTax(tax);
		
		products.add(p1);
		
		ProductAddDto p2 = new ProductAddDto();
		p2.setProducer("nokia");
		p2.setModel("3310");
		p2.setColor("black");
		p2.setImei("123456789000051");
		p2.setCurrentPrice(pricing);
		p2.setPriceIn(200.0d);
		p2.setProductTax(tax);

		products.add(p2);

		deliveryDto.setProducts(products);
		
		deliveryDto.setDateIn(new Date());
		deliveryDto.setStoreId(TestData.STORE1_ID);
		deliveryDto.setContactId(TestData.CONTACT1_ID);
		deliveryDto.setLabel("rrrr");

		requestDto.setDeliveryDto(deliveryDto);
		
		// when
		DeliveryAddResponse resp = deliveryService.add(requestDto);
		
		long deliveriesAfter = deliveryService.count(session);
		long productsAfter = productService.count(session);

		// then
		assertTrue( resp.isSuccess() );
		assertEquals( deliveriesAfter - deliveriesBefore, 1);
		assertEquals( productsAfter - productsBefore, 2);		
	}

	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void addNewDelivery2() throws SessionServiceException, DeliveryServiceException, ParseException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		long deliveriesBefore = deliveryService.count(session);
		long productsBefore = productService.count(session);

		DeliveryAddRequest addRequest = new DeliveryAddRequest(session);

		DeliveryAddDto addDto = new DeliveryAddDto();

		Date dateTo = new DateTime().withDate(2015, 12, 31).withTime(06, 30, 0, 0).toDate();

		PricingDto pricing = new PricingDto();
		pricing.setFrom(new Date());
		pricing.setTo(dateTo);
		pricing.setRate(190.0d);

		ProductTaxDto productTax = new ProductTaxDto ();
		productTax.setTaxId(TestData.TAX7_ID);
		productTax.setFrom(new Date());
		productTax.setTo(dateTo);

		List<ProductAddDto> products = new ArrayList<ProductAddDto>();
		ProductAddDto p1 = new ProductAddDto();
		p1.setProducer("nokia");
		p1.setModel("SX99");
		p1.setColor("black");
		p1.setImei("123456789000050");
		p1.setCurrentPrice(pricing);
		p1.setPriceIn(200.0d);
		p1.setProductTax(productTax);

		products.add(p1);

		ProductAddDto p2 = new ProductAddDto();
		p2.setProducer("sony");
		p2.setModel("firee");
		p2.setColor("black");
		p2.setImei("123456789000051");
		p2.setCurrentPrice(pricing);
		p2.setPriceIn(200.0d);
		p2.setProductTax(productTax);

		products.add(p2);

		addDto.setDateIn(new Date());
		addDto.setStoreId(TestData.STORE1_ID);
		addDto.setContactId(TestData.CONTACT1_ID);
		addDto.setLabel("rrrr");
		addDto.setProducts(products);

		addRequest.setDeliveryDto(addDto);

		// when
		DeliveryAddResponse resp = deliveryService.add(addRequest);

		long deliveriesAfter = deliveryService.count(session);
		long productsAfter = productService.count(session);

		// then
		assertTrue( resp.isSuccess() );
		assertEquals( deliveriesAfter - deliveriesBefore, 1);
		assertEquals( productsAfter - productsBefore, 2);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDetailsById() throws SessionServiceException, DeliveryServiceException, ParseException {
		
		// given
		long deliveryId = 1;
		
		// when 
		DeliveryDetailsRequest req = new DeliveryDetailsRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setDeliveryId(deliveryId);
		
		DeliveryDetailsResponse resp = deliveryService.fetchDetails(req);
		
		// then
		assertEquals(resp.getDelivery().getLabel(), "nowy rok cieszyn 1");
		assertEquals(resp.getDelivery().getProducts().size(), 8);		
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDeliveries1() throws SessionServiceException, DeliveryServiceException {
		
		// given
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
				.withMinNumberOfProducts(1)
				.withMaxNumberOfProducts(10)
				.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setFilters(filters);		
		
		// when
		DeliveriesFetchResponse lst = deliveryService.findDeliveries(req);
		
		// then
		assertEquals(lst.getDeliveries().size(), 5);
		assertEquals(5L, lst.getCountTotal().longValue());
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDeliveries2() throws SessionServiceException, DeliveryServiceException {
		
		// given
		Date deliveryDateStart = new DateTime()
			.withDate(2010, 1, 1)
			.withTime(0, 0, 0, 0).toDate();

		Date deliveryDateEnd = new DateTime()
			.withDate(2013, 12, 31)
			.withTime(0, 0, 0, 0).toDate();
		
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
				.withDeliveryDateStart(deliveryDateStart)
				.withDeliveryDateEnd(deliveryDateEnd)
				.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setFilters(filters);		
		
		// when
		DeliveriesFetchResponse lst = deliveryService.findDeliveries(req);
		
		// then
		assertEquals(lst.getDeliveries().size(), 3);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDeliveries3() throws SessionServiceException, DeliveryServiceException {
		
		// given
		String label = "nowy rok cieszyn 1";
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
				.withLabel(label)
				.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setFilters(filters);		
		
		// when
		DeliveriesFetchResponse lst = deliveryService.findDeliveries(req);
		
		// then
		assertEquals(lst.getDeliveries().size(), 1);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDeliveries4() throws SessionServiceException, DeliveryServiceException {
		
		// given
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
				.withMinNumberOfProducts(7)
				.withMaxNumberOfProducts(9)
				.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setFilters(filters);		
		
		// when
		DeliveriesFetchResponse lst = deliveryService.findDeliveries(req);
		
		// then
		assertEquals(lst.getDeliveries().size(), 4);		
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDeliveries5() throws SessionServiceException, DeliveryServiceException {
		
		// given
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
				.withSumFrom(500.0d)
				.withSumTo(900.0d)
				.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setFilters(filters);		
		
		// when
		DeliveriesFetchResponse lst = deliveryService.findDeliveries(req);
		
		// then
		assertEquals(lst.getDeliveries().size(), 4);		
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void findDeliveries6() throws SessionServiceException, DeliveryServiceException {
		
		// given
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
				.withSumFrom(500.0d)
				.withSumTo(900.0d)
				.withMinNumberOfProducts(7)
				.withMaxNumberOfProducts(9)
				.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(TestData.USER1_SESSIONID);
		req.setUsername(TestData.USER1_NAME);
		req.setFilters(filters);
		
		// when
		DeliveriesFetchResponse lst = deliveryService.findDeliveries(req);
		
		// then
		assertEquals(lst.getDeliveries().size(), 4);		
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void editDelivery0() throws SessionServiceException, DeliveryServiceException, ParseException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME,TestData.USER1_SESSIONID);
		DeliveryEditRequest editRequest = new DeliveryEditRequest(session);

		DeliveryEditDto editDto = new DeliveryEditDto();

		editDto.setId(TestData.DELIVERY2_ID);
		editDto.setContactId(TestData.CONTACT2_ID);
		editDto.setStoreId(TestData.STORE2_ID);
		editDto.setLabel("nowy label");

		Date dateTo = new DateTime().withDate(2016,12,31).withTime(0,0,0,0).toDate();

		PricingAddDto pricing1 = new PricingAddDto();
		pricing1.setFrom(new Date());
		pricing1.setRate(195.0d);

		ProductTaxAddDto productTax1 = new ProductTaxAddDto();
		productTax1.setTaxId(TestData.TAX6_ID);
		productTax1.setFrom(new Date());

		ProductAddDto productAdd = new ProductAddDto();
		productAdd.setColor("green");
		productAdd.setImei("123456789000099");
		productAdd.setModel("3310");
		productAdd.setProducer("nokia");
		productAdd.setPriceIn(200.0d);
		productAdd.setCurrentPrice(pricing1);
		productAdd.setProductTax(productTax1);

		editDto.addProductToAdd(productAdd);

		editDto.addProductToDelete(TestData.PRODUCT7_ID);

		PricingAddDto pricing2 = new PricingAddDto();
		pricing2.setFrom(new Date());
		pricing2.setRate(195.0d);

		ProductTaxAddDto productTax2 = new ProductTaxAddDto();
		productTax2.setTaxId(TestData.TAX6_ID);
		productTax2.setFrom(new Date());

		ProductEditDto productToEdit = new ProductEditDto();
		productToEdit.setId(TestData.PRODUCT8_ID);
		productToEdit.setModel("3310");
		productToEdit.setProducer("nokia");
		productToEdit.setImei("123456789000077");
		productToEdit.setCurrentPrice(pricing2);
		productToEdit.setProductTax(productTax2);
		productToEdit.setColor("white");
		productToEdit.setPriceIn(150.0d);

		editDto.addProductToEdit(productToEdit);

		editRequest.setDeliveryDto(editDto);

		// when
		DeliveryEditResponse response = deliveryService.edit(editRequest);

		// then
		assertTrue(response.isSuccess());
	}

	@Test(expected = PersistenceException.class)
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void editDelivery1() throws SessionServiceException, DeliveryServiceException, ParseException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME,TestData.USER1_SESSIONID);
		DeliveryEditRequest editRequest = new DeliveryEditRequest(session);

		DeliveryEditDto editDto = new DeliveryEditDto();

		editDto.setId(TestData.DELIVERY1_ID);
		editDto.setContactId(TestData.CONTACT1_ID);
		editDto.setStoreId(TestData.STORE2_ID);
		editDto.setLabel("nowy label");

		Date fromDay = new Date();

		PricingAddDto pricing1 = new PricingAddDto();
		pricing1.setFrom(fromDay);
		pricing1.setRate(200.d);

		ProductTaxAddDto tax1 = new ProductTaxAddDto();
		tax1.setFrom(fromDay);
		tax1.setTaxId(TestData.TAX6_ID);

		ProductAddDto productAdd = new ProductAddDto();
		productAdd.setColor("green");
		productAdd.setImei("123456789000099");
		productAdd.setModel("3310");
		productAdd.setProducer("nokia");
		productAdd.setPriceIn(200.0d);
		productAdd.setCurrentPrice(pricing1);
		productAdd.setProductTax(tax1);

		editDto.addProductToAdd(productAdd);

		editDto.addProductToDelete(TestData.PRODUCT3_ID);

		PricingAddDto pricing2 = new PricingAddDto();
		pricing2.setFrom(fromDay);
		pricing2.setRate(250.d);

		ProductTaxAddDto tax2 = new ProductTaxAddDto();
		tax2.setFrom(fromDay);
		tax2.setTaxId(TestData.TAX6_ID);

		ProductEditDto productToEdit = new ProductEditDto();
		productToEdit.setId(TestData.PRODUCT2_ID);
		productToEdit.setModel("3310");
		productToEdit.setProducer("nokia");
		productToEdit.setCurrentPrice(pricing2);
		productToEdit.setPriceIn(110.0d);
		productToEdit.setProductTax(tax2);

		editDto.addProductToEdit(productToEdit);

		editRequest.setDeliveryDto(editDto);

		// when

		DeliveryEditResponse response = deliveryService.edit(editRequest);

		// then exception should arise
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void editDelivery2() throws SessionServiceException, DeliveryServiceException, ParseException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		DeliveryEditRequest editRequest = new DeliveryEditRequest(session);

		DeliveryEditDto editDto = new DeliveryEditDto();

		editDto.setId(TestData.DELIVERY2_ID);
		editDto.setContactId(TestData.CONTACT2_ID);
		editDto.setStoreId(TestData.STORE2_ID);
		editDto.setLabel("nowy label");

		Date fromDate = new Date();

		PricingAddDto pricing1 = new PricingAddDto();
		pricing1.setFrom(fromDate);
		pricing1.setRate(200.0d);

		ProductTaxAddDto tax1 = new ProductTaxAddDto();
		tax1.setFrom(fromDate);
		tax1.setTaxId(TestData.TAX6_ID);

		ProductAddDto productAdd1 = new ProductAddDto();
		productAdd1.setColor("green");
		productAdd1.setImei("123456789000099");
		productAdd1.setModel("2000");
		productAdd1.setProducer("nokia");
		productAdd1.setPriceIn(200.0d);
		productAdd1.setCurrentPrice(pricing1);
		productAdd1.setProductTax(tax1);

		editDto.addProductToAdd(productAdd1);

		PricingAddDto pricing2 = new PricingAddDto();
		pricing2.setFrom(fromDate);
		pricing2.setRate(200.0d);

		ProductTaxAddDto tax2 = new ProductTaxAddDto();
		tax2.setFrom(fromDate);
		tax2.setTaxId(TestData.TAX6_ID);

		ProductAddDto productAdd2 = new ProductAddDto();
		productAdd2.setColor("green");
		productAdd2.setImei("123456789000100");
		productAdd2.setModel("xyz");
		productAdd2.setProducer("sony");
		productAdd2.setPriceIn(200.0d);
		productAdd2.setCurrentPrice(pricing2);
		productAdd2.setProductTax(tax2);

		editDto.addProductToAdd(productAdd2);

		// TODO : uncomment to check optimstic lock problem
//		editRequest.addProductToDelete(TestData.PRODUCT7_ID);

		editDto.addProductToDelete(TestData.PRODUCT12_ID);

		PricingAddDto pricing3 = new PricingAddDto();
		pricing3.setFrom(fromDate);
		pricing3.setRate(200.0d);

		ProductTaxAddDto tax3 = new ProductTaxAddDto();
		tax3.setFrom(fromDate);
		tax3.setTaxId(TestData.TAX6_ID);

		ProductEditDto productToEdit1 = new ProductEditDto();
		productToEdit1.setId(TestData.PRODUCT7_ID);
		productToEdit1.setModel("desire y");
		productToEdit1.setProducer("htc");
		productToEdit1.setCurrentPrice(pricing3);
		productToEdit1.setPriceIn(110.0d);
		productToEdit1.setProductTax(tax3);

		editDto.addProductToEdit(productToEdit1);

		PricingAddDto pricing4 = new PricingAddDto();
		pricing4.setFrom(fromDate);
		pricing4.setRate(200.0d);

		ProductTaxAddDto tax4 = new ProductTaxAddDto();
		tax4.setFrom(fromDate);
		tax4.setTaxId(TestData.TAX6_ID);

		ProductEditDto productToEdit2 = new ProductEditDto();
		productToEdit2.setId(TestData.PRODUCT8_ID);
		productToEdit2.setModel("q10");
		productToEdit2.setProducer("blackberry");
		productToEdit2.setCurrentPrice(pricing4);
		productToEdit2.setPriceIn(160.0d);
		productToEdit2.setProductTax(tax4);

		editDto.addProductToEdit(productToEdit2);

		editRequest.setDeliveryDto(editDto);

		// when
		DeliveryEditResponse response = deliveryService.edit(editRequest);

		// then
		assertTrue(response.isSuccess());
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void deleteDelivery1() throws SessionServiceException, DeliveryServiceException, ParseException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME,TestData.USER1_SESSIONID);
		long countBefore = deliveryService.count(session);
		
		DeliveryDeleteRequest req = new DeliveryDeleteRequest(session);
		req.setDeliveryId(TestData.DELIVERY2_ID);

		// when
		DeliveryDeleteResponse response = deliveryService.delete(req);
		long countAfter = deliveryService.count(session);
		
		// then
		assertTrue(response.isSuccess());
		assertEquals( countBefore - countAfter, 1);
	}
	
}
