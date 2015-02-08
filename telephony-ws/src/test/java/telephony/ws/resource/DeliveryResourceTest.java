package telephony.ws.resource;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import telephony.core.query.filter.DeliveryFilterCriteria;
import telephony.core.query.filter.DeliveryFilterCriteriaBuilder;
import telephony.core.service.dto.*;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.DeliveriesFetchResponse;
import telephony.core.service.dto.response.DeliveryDeleteResponse;
import telephony.core.service.dto.response.DeliveryDetailsResponse;
import telephony.core.service.dto.response.DeliveryEditResponse;
import telephony.ws.resource.delivery.*;
import telephony.ws.resource.session.SessionInitializationResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;


@RunWith(JUnit4ClassRunner.class)
public class DeliveryResourceTest extends BaseWSTest {
	
	@Test
	public void deleteDelivery() {

		// given
		String sessionId = authAndGetSessionId();

		SessionDto session = SessionDto.create("user1@gmail.com", sessionId);

		DeliveryDeleteRequest req = new DeliveryDeleteRequest(session);
		req.setSessionId(sessionId);
		req.setUsername("user1@gmail.com");
		req.setDeliveryId(1L);
		
		// when
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.create();
	
		String json = gson.toJson(req);
		
		Response res1 = 
			given()
				.contentType(ContentType.JSON)
				.body(json)
			.when()
			.delete(TESTING_APP + DeliveriesDeleteResource.URL);
		
		// then
		DeliveryDeleteResponse resp = gson.fromJson(res1.asString(), DeliveryDeleteResponse.class);
		
		assertTrue( resp.isSuccess() );	
	}
	
	@Test
	public void updateDelivery() {

		// given
		String sessionId = authAndGetSessionId();

		SessionDto session = SessionDto.create("user1@gmail.com", sessionId);

		DeliveryEditRequest req = new DeliveryEditRequest(session);
		req.setSessionId(sessionId);
		req.setUsername("user1@gmail.com");

		DeliveryEditDto editDto = new DeliveryEditDto();

		editDto.setId(1L);
		editDto.setContactId(2L);
		editDto.setStoreId(2L);
		editDto.setLabel("nowy label");
		
		Date d = new Date();

//		PricingAddDto pricing1 = new PricingAddDto();
//		pricing1.setFrom(new Date());
//		pricing1.setRate(200.0d);
//
//		ProductTaxAddDto taxDto1 = new ProductTaxAddDto();
//		taxDto1.setFrom(new Date());
//		taxDto1.setTaxId(6L);
//
//		ProductAddDto productAdd = new ProductAddDto();
//		productAdd.setColor("green");
//		productAdd.setImei("123456789000099");
//		productAdd.setModel("3310");
//		productAdd.setProducer("nokia");
//		productAdd.setPriceIn(200.0d);
//		productAdd.setPriceFrom(d);
//		productAdd.setPriceTo(null);
//		productAdd.setProductTax(6L);
//		productAdd.setTaxFrom(d);
//		productAdd.setTaxTo(null);
//
//		editDto.addProductToAdd(productAdd);
//
//		editDto.addProductToDelete(3L);
//
//		ProductEditDto productToEdit = new ProductEditDto();
//		productToEdit.setId(2L);
//		productToEdit.setModel("3310");
//		productToEdit.setProducer("nokia");
//		productToEdit.setPrice(300.0d);
//		productToEdit.setPriceIn(110.0d);
//
//		productToEdit.setTaxId(6L);

//		editDto.addProductToEdit(productToEdit);
		
		// when
		Gson gson = new GsonBuilder()
		.serializeNulls()
		.create();
	
		String json = gson.toJson(req);
		
		Response res1 = 
			given()
				.contentType(ContentType.JSON)
				.body(json)
			.when()
			.put(TESTING_APP + DeliveriesEditResource.URL);
		
		// then
		DeliveryEditResponse resp = gson.fromJson(res1.asString(), DeliveryEditResponse.class);
		
		assertTrue( resp.isSuccess() );		
	}
	
	@Test
	public void fetchDeliveries() {
		
		String sessionId = authAndGetSessionId();
		
		DeliveryFilterCriteria filters = DeliveryFilterCriteriaBuilder.deliveryFilterCriteria()
			.withSumFrom(500.0d)
			.withSumTo(900.0d)
			.withMinNumberOfProducts(7)
			.withMaxNumberOfProducts(9)
			.build();
		
		DeliveriesFetchRequest req = new DeliveriesFetchRequest();
		req.setSessionId(sessionId);
		req.setUsername("user1@gmail.com");
		req.setFilters(filters);
		
		Gson gson = new GsonBuilder()
			.serializeNulls()
			.create();
		
		String json = gson.toJson(req);
		
		Response res1 = 
				given()
					.contentType(ContentType.JSON)
					.body(json)
				.when()
				.post(TESTING_APP + DeliveriesFetchResource.URL);
		
		DeliveriesFetchResponse resp = gson.fromJson(res1.asString(), DeliveriesFetchResponse.class);
		
		assertEquals( resp.getDeliveries().size(), 5);		
	}
	
	@Test
	public void findDetails() {
		
		String sessionId = authAndGetSessionId();
		
		DeliveryDetailsRequest req = new DeliveryDetailsRequest();
		req.setSessionId(sessionId);
		req.setUsername("user1@gmail.com");
		req.setDeliveryId(1L);
		
		Gson gson = new GsonBuilder().create();
		
		String json = gson.toJson(req);
		
		Response res1 = 
				given()
					.contentType(ContentType.JSON)
					.body(json)
				.when()
				.post(TESTING_APP + DeliveriesDetailsResource.URL);
		
		DeliveryDetailsResponse resp = gson.fromJson(res1.asString(), DeliveryDetailsResponse.class);
		
		assertEquals("nowy rok cieszyn 1", resp.getDelivery().getLabel());
		
	}

	private String authAndGetSessionId() {
		SignInDto auth = SignInDto.create()
				.password("rfaysdhaiufsiuf")
				.username("user1@gmail.com");		
		
		Gson gson = new GsonBuilder().create();
		
		String json = gson.toJson(auth);
			
		Response res1 = 
					given()
						.contentType(ContentType.JSON)
						.body(json)
					.when()
						.post(TESTING_APP + SessionInitializationResource.URL);
		
		String jsonResp = res1.asString();
		
		String sessionId = from(jsonResp).get("sessionId");
		return sessionId;
	}

	@Test
	public void addDelivery() {
		
		SignInDto auth = SignInDto.create()
				.password("rfaysdhaiufsiuf")
				.username("user1@gmail.com");		
		
		Gson gson = new GsonBuilder().create();
		
		String json = gson.toJson(auth);
			
		Response res1 = 
					given()
						.contentType(ContentType.JSON)
						.body(json)
					.when()
						.post(TESTING_APP + SessionInitializationResource.URL);
		
		String jsonResp = res1.asString();
		
		String sessionId = from(jsonResp).get("sessionId");

		SessionDto session = SessionDto.create("user1@gmail.com", sessionId);
		
		List<ProductAddDto> products = new ArrayList<>();
		Date priceTo = new DateTime().withDate(2015, 12, 31).withTime(06, 30, 0, 0).toDate();

		PricingAddDto pricing1 = new PricingAddDto();
		pricing1.setFrom(new Date());
		pricing1.setRate(200.0d);

		ProductTaxAddDto taxDto1 = new ProductTaxAddDto();
		taxDto1.setFrom(new Date());
		taxDto1.setTaxId(6L);
		
		// TODO : refactor to TestDataBuilder
		ProductAddDto p1 = new ProductAddDto();
		p1.setProducer("Nokia");
		p1.setModel("SX99");
		p1.setColor("black");
		p1.setImei("123456789000050");
		p1.setCurrentPrice(pricing1);
		p1.setPriceIn(200.0d);
		p1.setProductTax(taxDto1);

		PricingAddDto pricing2 = new PricingAddDto();
		pricing2.setFrom(new Date());
		pricing2.setRate(300.0d);

		ProductTaxAddDto taxDto2 = new ProductTaxAddDto();
		taxDto2.setFrom(new Date());
		taxDto2.setTaxId(7L);
		
		ProductAddDto p2 = new ProductAddDto();
		p2.setProducer("Audi");
		p2.setModel("X50");
		p2.setColor("white");
		p2.setImei("123456789000051");
		p2.setCurrentPrice(pricing2);
		p2.setPriceIn(300.0d);
		p2.setProductTax(taxDto2);

		products.add(p1);
		products.add(p2);
		
		DeliveryAddRequest req = new DeliveryAddRequest(session);

		req.setSessionId(sessionId);
		req.setUsername("user1@gmail.com");

		DeliveryAddDto addDto = new DeliveryAddDto();

		addDto.setContactId(1l);
		addDto.setLabel("aaa");
		addDto.setDateIn(new Date());
		addDto.setStoreId(1l);
		addDto.setProducts(products);

		req.setDeliveryDto(addDto);
		
		Response res2 = 
				given()
					.contentType(ContentType.JSON)
					.body(gson.toJson(req))
				.when()
					.post(TESTING_APP + DeliveryAddResource.URL);
	
		Boolean resp2 = from(res2.asString()).get("success");
		
		assertTrue( resp2 );
	}
}
