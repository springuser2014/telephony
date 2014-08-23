package telephony.ws.resource;


import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import telephony.ws.ArchivesBuilder;
import telephony.ws.pre.TestsConfig;

@RunWith(Arquillian.class)
public class RestAssuredTest {
	

	private static final String TESTING_APP = TestsConfig.TESTING_HOST
			+ ArchivesBuilder.ARCHIVE_NAME + TestsConfig.REST_ADDR;

	@Test
	public void test() throws Exception {
			
		String res = get(TESTING_APP + HelloWorldResource.URL).asString();
		
		assertEquals(res, "hello world");
	}


}
