package telephony.ws.resource;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import telephony.core.entity.jpa.ProductStatus;
import telephony.core.query.filter.ProductFilterCriteria;
import telephony.core.service.dto.*;
import telephony.ws.resource.delivery.DeliveriesDetailsResource;
import telephony.ws.resource.products.ProductsFetchResource;
import telephony.ws.resource.session.SessionInitializationResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.http.ContentType;


@RunWith(JUnit4ClassRunner.class)
public class ProductResourceTest extends BaseWSTest {
	
	private String authAndGetSessionId() {
		SignInBean auth = SignInBean.create()
				.password("rfaysdhaiufsiuf")
				.username("user1@gmail.com");		
		
		Gson gson = new GsonBuilder().create();
		
		String json = gson.toJson(auth);
			
		com.jayway.restassured.response.Response res1 = 
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
	public void findProducts() {
		
		String sessionId = authAndGetSessionId();
		
		ProductFilterCriteria filters = ProductFilterCriteria.create()
				.setStatus(ProductStatus.SOLD);
		
		ProductFetchRequest req = new ProductFetchRequest();
		req.setSessionId(sessionId);
		req.setUsername("user1@gmail.com");
		req.setFiltersCriteria(filters);
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		String json = gson.toJson(req);
		
		com.jayway.restassured.response.Response res1 = 
				given()
					.contentType(ContentType.JSON)
					.body(json)
				.when()
				.post(TESTING_APP + ProductsFetchResource.URL);
		
		ProductFetchResponse resp = gson.fromJson(res1.asString(), ProductFetchResponse.class);
		
		assertEquals(resp.getProducts().size(), 10);		
	}
	
	

}
