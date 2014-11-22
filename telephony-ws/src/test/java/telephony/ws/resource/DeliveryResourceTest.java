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
import telephony.core.service.dto.ProductDto;
import telephony.core.service.dto.ProductEditDto;
import telephony.core.service.dto.SignInDto;
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
		
		DeliveryDeleteRequest req = new DeliveryDeleteRequest();
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

		DeliveryEditRequest req = new DeliveryEditRequest();
		req.setSessionId(sessionId);
		req.setUsername("user1@gmail.com");
		
		req.setId(1L);
		req.setContactId(2L);
		req.setStoreId(2L);
		
		req.setLabel("nowy label");
		
		Date d = new Date();
		
		ProductDto productAdd = new ProductDto();
		productAdd.setColor("green");
		productAdd.setImei("123456789000099");
		productAdd.setModel("3310");
		productAdd.setProducer("nokia");
		productAdd.setPriceIn(200.0d);
		productAdd.setPriceFrom(d);
		productAdd.setPriceTo(null);
		productAdd.setTaxId(6L);
		productAdd.setTaxFrom(d);
		productAdd.setTaxTo(null);
		
		req.addProductToAdd(productAdd);
		
		req.addProductToDelete(3L);
		
		ProductEditDto productToEdit = new ProductEditDto();
		productToEdit.setId(2L);
		productToEdit.setModel("3310");
		productToEdit.setProducer("nokia");
		productToEdit.setPrice(300.0d);
		productToEdit.setPriceIn(110.0d);
		
		productToEdit.setTaxId(6L);
		
		req.addProductToEdit(productToEdit);
		
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
		
		DeliveryFilterCriteria filters = DeliveryFilterCriteria.create()
			.sumFrom(500.0d)
			.sumTo(900.0d)
			.minNumberOfProducts(7)
			.maxNumberOfProducts(9);
		
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
		
		List<ProductDto> products = new ArrayList<ProductDto>();
		Date priceTo = new DateTime().withDate(2015, 12, 31).withTime(06, 30, 0, 0).toDate();
		
		// TODO : refactor to TestDataBuilder
		ProductDto p1 = new ProductDto();
		p1.setProducer("Nokia");
		p1.setModel("SX99");
		p1.setColor("black");
		p1.setImei("123456789000050");
		p1.setPriceFrom(new Date());
		p1.setPriceTo(priceTo);
		p1.setPriceIn(200.0d);
		p1.setTaxFrom(new Date());
		p1.setTaxTo(priceTo);
		p1.setTaxId(7L);
		
		ProductDto p2 = new ProductDto();
		p2.setProducer("Audi");
		p2.setModel("X50");
		p2.setColor("white");
		p2.setImei("123456789000051");
		p2.setPriceFrom(new Date());
		p2.setPriceTo(priceTo);
		p2.setPriceIn(300.0d);
		p2.setTaxFrom(new Date());
		p2.setTaxTo(priceTo);
		p2.setTaxId(7L);
		
		products.add(p1);
		products.add(p2);
		
		DeliveryAddRequest req = new DeliveryAddRequest();
		req.setContactId(1l);
		req.setLabel("aaa");
		req.setDateIn(new Date());
		req.setSessionId(sessionId);
		req.setUsername("user1@gmail.com");
		req.setStoreId(1l);
		req.setProducts(products);
		
		Response res2 = 
				given()
					.contentType(ContentType.JSON)
					.body(gson.toJson(req))
				.when()
					.post(TESTING_APP + DeliveriesAddResource.URL);
	
		Boolean resp2 = from(res2.asString()).get("success");
		
		assertTrue( resp2 );
	}
}
