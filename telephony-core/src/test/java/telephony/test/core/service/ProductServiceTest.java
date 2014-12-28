package telephony.test.core.service;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.ProducerDto;
import telephony.test.BaseCoreTest;
import telephony.core.service.ProductService;
import telephony.core.service.StoreService;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.ProductFetchRequest;
import telephony.core.service.dto.response.ProductFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.test.core.data.TestData;
import telephony.core.entity.jpa.ProductStatus;
import telephony.core.query.filter.ProductFilterCriteria;
import telephony.core.query.filter.ProductFilterCriteriaBuilder;

import com.google.inject.Inject;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.dbunit.FlywayDBUnitTestExecutionListener;
import telephony.test.core.data.TestDataBuilder;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/context.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
    FlywayDBUnitTestExecutionListener.class 
})
@FlywayTest
public class ProductServiceTest extends BaseCoreTest {
	
	@Inject
	private ProductService productService;
	
	@Inject
	private StoreService storeService;
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingProductsByStore() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria()
				.withStoreId(TestData.STORE1_ID)
				.build();
		ProductFetchRequest fetchRequest = new ProductFetchRequest(session);
		fetchRequest.setFilters(filters);

		// when
		ProductFetchResponse fetchResponse = productService.fetch(fetchRequest);
		
		// then
		assertTrue("should found 24 items", fetchResponse.getProducts().size() == 24);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllProducts() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria()
				.withStatus(ProductStatus.IN_STORE)
				.withStoreId(TestData.STORE1_ID)
				.build();

		ProductFetchRequest fetchRequest = new ProductFetchRequest(session);
		fetchRequest.setFilters(filters);

		// when
		ProductFetchResponse fetchResponse =  productService.fetch(fetchRequest);
		
		// then
		assertTrue(fetchResponse.isSuccess());
		assertEquals("there should be 18 products in the given store", fetchResponse.getProducts().size(), 18);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchingAllProducersInUse() throws SessionServiceException {

		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		// when
		List<ProducerDto> lst = productService.fetchAllProducersInUse(session);

		List<String> expected = new ArrayList<String>();
		expected.add(TestData.PRODUCER_NOKIA_LABEL);
		expected.add(TestData.PRODUCER_APPLE_LABEL);


		// then
		assertEquals("should contains 2 ", new Integer(2), new Integer(lst.size()));
		assertTrue("should contains 2 producers in use", lst.size() == expected.size());

		for (ProducerDto dto : lst) {
			assertTrue("exactly those 2 producers in use", expected.contains(dto.getLabel()));
		}
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchingAllModelsInUse() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		List<ModelDto> lst = productService.fetchAllModelsInUse(session);
		
		List<String> expected = new ArrayList<String>();
		expected.add(TestData.MODEL1_LABEL);
		expected.add(TestData.MODEL2_LABEL);
		expected.add(TestData.MODEL3_LABEL);
		expected.add(TestData.MODEL4_LABEL);
		expected.add(TestData.MODEL5_LABEL);
		expected.add(TestData.MODEL6_LABEL);
		
		// then
		assertEquals("should return 6 different products models", 6, lst.size());
		for (ModelDto m : lst) {
			assertTrue("exactly those 6 products", expected.contains(m.getLabel()));
		}
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchingAllColors() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		List<String> lst = productService.fetchAllColors(session);
		List<String> expected = new ArrayList<String>();
		expected.add(TestData.COLOR_BLACK);
		expected.add(TestData.COLOR_WHITE);
		expected.add(TestData.COLOR_RED);
		expected.add(TestData.COLOR_BLUE);
		
		// then
		assertTrue("should return 4 different colors", lst.size() == 4);
		assertTrue("exactly those 4 colors ", lst.containsAll(expected));
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchingAllIMEIsInUse() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		
		// when
		List<String> lst = productService.fetchAllImeiInUse(session);

		// then
		assertEquals(lst.size(), 42);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchingProductByImeiAndStore() 
			throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria()
				.withImei(TestData.PRODUCT2_IMEI)
				.withStoreId(TestData.STORE1_ID)
				.build();

		ProductFetchRequest fetchRequest = new ProductFetchRequest(session);
		fetchRequest.setFilters(filters);

		// when
		ProductFetchResponse fetchResponse = productService.fetch(fetchRequest);
		
		// then
		assertEquals(fetchResponse.getProducts().get(0).getId(), TestData.PRODUCT2_ID);
		assertEquals(fetchResponse.getProducts().get(0).getImei(), TestData.PRODUCT2_IMEI);
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/migration", "db/data" })
	public void fetchingProductByImei() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria()
			.withImei("123456789000001")
			.build();

		ProductFetchRequest fetchRequest = new ProductFetchRequest(session);
		fetchRequest.setFilters(filters);
		
		// when
		ProductFetchResponse fetchResponse = productService.fetch(fetchRequest);
		
		// then
		assertTrue("should return one product", fetchResponse.getProducts().size() == 1);
	}	
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingById() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria()
				.withProductId(TestData.PRODUCT1_ID).build();
		ProductFetchRequest fetchRequest = new ProductFetchRequest(session);

		fetchRequest.setFilters(filters);
		
		// when
		ProductFetchResponse fetchResponse = productService.fetch(fetchRequest);
				
		// then
		assertNotNull(fetchRequest);
		assertEquals(fetchResponse.getProducts().get(0).getImei(), TestData.PRODUCT1_IMEI);
		assertEquals(fetchResponse.getProducts().get(0).getColor(), TestData.PRODUCT1_COLOR);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findingByIds() throws SessionServiceException {
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria()
				.withProductId(TestData.PRODUCT1_ID)
				.withProductId(TestData.PRODUCT2_ID)
				.build();
		ProductFetchRequest fetchRequest = new ProductFetchRequest(session);
		fetchRequest.setFilters(filters);
		
		// when
		ProductFetchResponse fetchResponse = productService.fetch(fetchRequest);
				
		// then
		assertNotNull(fetchResponse);
		assertEquals(fetchResponse.getProducts().size(), 2);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findByIMEI() throws SessionServiceException {
		
		// given
		SessionDto session = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria()
				.withImei(TestData.PRODUCT48_IMEI)
				.build();

		ProductFetchRequest fetchRequest = new ProductFetchRequest(session);
		fetchRequest.setFilters(filters);
		
		// when
		ProductFetchResponse fetchResponse = productService.fetch(fetchRequest);
		
		// then
		assertNotNull(fetchResponse);
		assertTrue(fetchResponse.isSuccess());
		assertEquals(fetchResponse.getProducts().get(0).getColor(), TestData.PRODUCT48_COLOR);
	}


	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchProducts1() throws SessionServiceException {

		// given
		SessionDto sessionDto = SessionDto.create(TestData.USER1_NAME, TestData.USER1_SESSIONID);

		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria()
				.withColor("white")
				.withProducer("nokia")
				.build();
		
		ProductFetchRequest request = new ProductFetchRequest(sessionDto);
		request.setFilters(filters);
		
		// when
		ProductFetchResponse resp = productService.fetch(request);
		
		// then
		assertEquals(resp.getProducts().size(), 4);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void fetchProducts2() throws SessionServiceException {

		// given
		Date deliveryDateStart = TestDataBuilder.getDate(2012, 1, 1);
		Date deliveryDateEnd = TestDataBuilder.getDate(2012, 12, 31);
		
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria()
				.withDeliveryDateStart(deliveryDateStart)
				.withDeliveryDateEnd(deliveryDateEnd)
				.build();
				
		ProductFetchRequest request = new ProductFetchRequest();
		request.setSessionId(TestData.USER1_SESSIONID);
		request.setUsername(TestData.USER1_NAME);
		request.setFilters(filters);
		
		// when
		ProductFetchResponse resp = productService.fetch(request);
		
		// then
		assertEquals(resp.getProducts().size(), 8);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findProducts3() throws SessionServiceException {

		// given
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria()
				.withColor("black")
				.withProducer("nokia")
				.build();				
		
		ProductFetchRequest request = new ProductFetchRequest();
		request.setSessionId(TestData.USER1_SESSIONID);
		request.setUsername(TestData.USER1_NAME);
		request.setFilters(filters);
		
		// when
		ProductFetchResponse resp = productService.fetch(request);
		
		// then
		assertEquals(resp.getProducts().size(), 12);
	}
	
	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findProducts4() throws SessionServiceException {

		// given
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria()
				.withImei("123456789000004")
				.build();
				
		
		ProductFetchRequest request = new ProductFetchRequest();
		request.setSessionId(TestData.USER1_SESSIONID);
		request.setUsername(TestData.USER1_NAME);
		request.setFilters(filters);
		
		// when
		ProductFetchResponse resp = productService.fetch(request);
		
		// then
		assertEquals(resp.getProducts().size(), 1);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "db/migration", "db/data" })
	public void findProducts5() throws SessionServiceException {

		// given
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria()
				.withStatus(ProductStatus.SOLD)
				.build();
				
		ProductFetchRequest request = new ProductFetchRequest();
		request.setSessionId(TestData.USER1_SESSIONID);
		request.setUsername(TestData.USER1_NAME);
		request.setFilters(filters);
		
		// when
		ProductFetchResponse resp = productService.fetch(request);
		
		// then
		assertEquals(resp.getProducts().size(), 10);
	}

}
