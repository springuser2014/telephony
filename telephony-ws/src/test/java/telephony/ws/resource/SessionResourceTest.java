package telephony.ws.resource;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.restlet.Client;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.http.ContentType;

import telephony.core.service.bean.Session;
import telephony.ws.bean.UserBean;
import telephony.ws.resource.bean.SessionBean;
import telephony.ws.resource.bean.SignInBean;
import telephony.ws.resource.session.SessionDestroyResource;
import telephony.ws.resource.session.SessionRefreshResource;
import telephony.ws.resource.session.SessionInitializationResource;
import telephony.ws.resource.session.SessionValidationResource;
import telephony.ws.resource.session.impl.SessionInitializationResourceImpl;

/**
 * foo bar.
 */
@RunWith(JUnit4ClassRunner.class)
public class SessionResourceTest extends BaseWSTest {

    private static final String SESSION_INITIALIZATION_RESOURCE_URL = 
    		TESTING_APP + SessionInitializationResource.URL;

    private static final String SESSION_VALIDATION_RESOURCE_URL = 
    		TESTING_APP + SessionValidationResource.URL;

    private static final String SESSION_REFRESH_RESOURCE_URL = 
    		TESTING_APP + SessionRefreshResource.URL;
    
    private static final String SESSION_DESTROY_RESOURCE_URL = 
    		TESTING_APP + SessionDestroyResource.URL;
    
    private SessionInitializationResource session;
    
    private SessionValidationResource sessionValidation;
    
    private SessionRefreshResource sessionRefresh;
    
    private SessionDestroyResource sessionDestroy;
    
    private ClientResource clientSession;
    
    private ClientResource clientSessionValidation;
    
    private ClientResource clientSessionRefresh;
    
    private ClientResource clientSessionDestroy;
    
    /**
     * asd.
     * @throws MalformedURLException asd.
     */
    @Before
    public void initVars() throws MalformedURLException {
    	URL sessionUrl = new URL(SESSION_INITIALIZATION_RESOURCE_URL);
        this.clientSession = 
        		new ClientResource(sessionUrl.toExternalForm());        
        this.session = 
        		this.clientSession.wrap(SessionInitializationResource.class);
        
        URL sessionValidationUrl = new URL(SESSION_VALIDATION_RESOURCE_URL);
        this.clientSessionValidation = 
        		new ClientResource(sessionValidationUrl.toExternalForm());        
        this.sessionValidation = 
        		this.clientSessionValidation.wrap(SessionValidationResource.class);
        
        URL sessionRefreshUrl = new URL(SESSION_REFRESH_RESOURCE_URL);
        this.clientSessionRefresh = 
        		new ClientResource(sessionRefreshUrl.toExternalForm());
        
        this.sessionRefresh = 
        		this.clientSessionRefresh.wrap(SessionRefreshResource.class);    
        
        URL sessionDestroyUrl = new URL(SESSION_DESTROY_RESOURCE_URL);
        this.clientSessionDestroy =
        		new ClientResource(sessionDestroyUrl.toExternalForm());
        
        this.sessionDestroy = 
        		this.clientSessionDestroy.wrap(SessionDestroyResource.class);
    }
    

	@Test
	public void testSessionInitialization() throws Exception {
		
		SignInBean auth = SignInBean.create()
				.password("rfaysdhaiufsiuf")
				.username("user1@gmail.com");		
		
		Gson gson = new GsonBuilder().create();
		
		String json = gson.toJson(auth);
			
		com.jayway.restassured.response.Response res = 
					given()
						.contentType(ContentType.JSON)
						.body(json)
					.when()
						.post(TESTING_APP + SessionInitializationResource.URL);
		
		String jsonResp = res.asString();
		
//		assertTrue( res.header("Content-type").equals("application/json") );
					
		assertTrue( from(jsonResp).get("username").equals("user1@gmail.com") );		
	}

	/**
     * foo bar.
     * @throws Exception foo bar.
     */
    @SuppressWarnings("deprecation")
	@Test
    public void checksUserAuthenticationForValidUser() throws Exception {
    	
    	// TODO : refactor to constatnts
    	final String username = "user1@gmail.com";
    	final String password = "rfaysdhaiufsiuf";

        JsonRepresentation requestParam = new JsonRepresentation(
        		new UserBean(username, password)
        );
        
        JsonRepresentation responseRep = session.initialize(requestParam);        
        
        assertTrue("response status is 200", 
        		clientSession
        		.getResponse()
        		.getStatus().equals(Status.SUCCESS_OK));
        
        assertTrue("response representation != null", responseRep != null);
	 
        
        JSONObject responseObj = responseRep.getJsonObject();
        String sessionId = responseObj.getString("sessionId");
        String uname = responseObj.getString("username");
        String validity = responseObj.getString("validity");
        
        assertTrue("contains all required data in the response",
        		sessionId != null 
        		&& uname.equals(username)
        		&& validity != null);
	}
    
    /**
     * foo bar.
     * @throws IOException 
     * @throws JSONException 
     */
    @SuppressWarnings("deprecation")
	@Test
    public void checksUserAuthenticationForInvalidUser() throws JSONException, IOException {
    	
    	// TODO : refactor to constatnts
    	final String username = "invaliduser@gmail.com";
    	final String password = "rfaysdhaiufsiuf";
        
        JsonRepresentation requestParam = new JsonRepresentation(
        		new UserBean(username, password)
        );
        
        JsonRepresentation responseRep = null;
        try {
			responseRep = session.initialize(requestParam);
		} catch (org.restlet.resource.ResourceException e) {	
			
			assertTrue("should return 400 in exception content", e.toString().contains("400"));
		}
        
        assertTrue("response status is 400", 
        		this.clientSession
        		.getResponse()
        		.getStatus().equals(Status.CLIENT_ERROR_BAD_REQUEST));
	}
    

    @SuppressWarnings("deprecation")
	@Test
    public void checksValidSessionVerification() throws JSONException, IOException {
    	// TODO : refactor to constatnts
    	final String username = "user1@gmail.com";
    	final String password = "rfaysdhaiufsiuf";
        
        JsonRepresentation startRequestParam = new JsonRepresentation(
        		new UserBean(username, password)
        );
        
        JsonRepresentation responseRep = session.initialize(startRequestParam);        
        
        assertTrue("response status is 200", 
        		clientSession
        		.getResponse()
        		.getStatus().equals(Status.SUCCESS_OK));
        
        assertTrue("response representation != null", responseRep != null);
        
        JSONObject respObj = responseRep.getJsonObject();
        String sessionId = respObj.getString("sessionId");
        String userToValid = respObj.getString("username");
        
        assertTrue(sessionId != null && userToValid != null);
        
        JsonRepresentation validateRequestParam = new JsonRepresentation(
        		new SessionBean(userToValid, sessionId)
        );
        
        JsonRepresentation validateResponseRep = sessionValidation.validate(validateRequestParam);
	    
        assertTrue("response should contains true", validateResponseRep.getText().contains("true"));
        assertTrue("should return 200", clientSessionValidation.getStatus().isSuccess());      
    }
    

    @SuppressWarnings("deprecation")
	@Test
    public void checksInvalidSessionVerification() throws JSONException, IOException {
    	
    	// TODO : refactor to constatnts
    	final String username = "user1@gmail.com";
    	final String password = "rfaysdhaiufsiuf";
    	       
        JsonRepresentation startRequestParam = new JsonRepresentation(
        		new UserBean(username, password)
        );
        
        JsonRepresentation responseRep = session.initialize(startRequestParam);        
        
        JSONObject respObj = responseRep.getJsonObject();
        String sessionId = respObj.getString("sessionId");
        String userToFinishSession = respObj.getString("username");
        
        SessionBean session = new SessionBean(userToFinishSession, sessionId);
        JsonRepresentation endingRequestParam = new JsonRepresentation(session);
        
        JsonRepresentation endingResponseRep = sessionDestroy.destroy(endingRequestParam);
        
        JsonRepresentation validatingParams = new JsonRepresentation(session);
               
        JsonRepresentation validatingResponseRep = sessionValidation.validate(validatingParams);
        
        assertTrue("response should contains false", 
        		validatingResponseRep.getText().contains("false"));
        assertTrue("should return 200", this.clientSessionValidation.getStatus().isSuccess());      
    }
    

    @SuppressWarnings("deprecation")
	@Test
    public void checksSessionRefreshingVerification() throws JSONException, IOException {
    	
    	// TODO : refactor to constatnts
    	final String username = "user1@gmail.com";
    	final String password = "rfaysdhaiufsiuf";
    	
       
        JsonRepresentation startRequestParam = new JsonRepresentation(
        		new UserBean(username, password)
        );
        
        JsonRepresentation responseRep = session.initialize(startRequestParam);        
        
        JSONObject respObj = responseRep.getJsonObject();
        String sessionId = respObj.getString("sessionId");
        String userToFinishSession = respObj.getString("username");
        
        SessionBean session = new SessionBean(userToFinishSession, sessionId);
        JsonRepresentation endingRequestParam = new JsonRepresentation(session);
        
        JsonRepresentation endingResponseRep = sessionRefresh.refresh(endingRequestParam);
        
        JsonRepresentation validatingParams = new JsonRepresentation(session);
        
        JsonRepresentation validatingResponse = sessionValidation.validate(validatingParams);
        
        assertTrue("response should contains true", 
        		validatingResponse.getText().contains("true"));
        assertTrue("should return 200", clientSessionValidation.getStatus().isSuccess());   	
    }
}


