package telephony.ws.resource;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import telephony.core.service.dto.SignInBean;
import telephony.ws.resource.session.SessionInitializationResource;
import telephony.ws.test.TestsConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.http.ContentType;

@RunWith(JUnit4ClassRunner.class)
public class RestAssuredTest {
	


	private static final String TESTING_APP = TestsConfig.TESTING_HOST
			+ TestsConfig.ARCHIVE_NAME + TestsConfig.REST_ADDR;

	@Test
	public void test() throws Exception {
			
		String res = get(TESTING_APP + HelloWorldResource.URL).asString();
		
		assertEquals(res, "hello world");
	}
	
	@Test
	public void testSessionInitialization() throws Exception {
		
		SignInBean auth = SignInBean.create()
				.password("rfaysdhaiufsiuf")
				.username("user1@gmail.com");		
		
		Gson gson = new GsonBuilder().create();
		
		String json = gson.toJson(auth);
			
		String res = 
					given()
						.contentType(ContentType.JSON)
						.body(json)
					.when()
						.post(TESTING_APP + SessionInitializationResource.URL)
					.asString();
		
		assertTrue( from(res).get("username").equals("user1@gmail.com") );		
	}

}
