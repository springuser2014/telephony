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

import telephony.core.service.dto.DeliveryAddRequest;
import telephony.core.service.dto.ProductBean;
import telephony.core.service.dto.SignInBean;
import telephony.ws.resource.delivery.DeliveriesAddResource;
import telephony.ws.resource.session.SessionInitializationResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.http.ContentType;


@RunWith(JUnit4ClassRunner.class)
public class DeliveryResourceTest extends BaseWSTest {

	@Test
	public void addDelivery() {
		
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
		
		List<ProductBean> products = new ArrayList<ProductBean>();
		Date priceTo = new DateTime().withDate(2015, 12, 31).withTime(06, 30, 0, 0).toDate();
		
		// TODO : refactor to TestDataBuilder
		ProductBean p1 = new ProductBean();
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
		
		ProductBean p2 = new ProductBean();
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
		
		com.jayway.restassured.response.Response res2 = 
				given()
					.contentType(ContentType.JSON)
					.body(gson.toJson(req))
				.when()
					.post(TESTING_APP + DeliveriesAddResource.URL);
	
		Boolean resp2 = from(res2.asString()).get("success");
		
		assertTrue( resp2 );
	}
}
