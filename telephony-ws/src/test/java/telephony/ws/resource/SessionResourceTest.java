package telephony.ws.resource;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
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

import telephony.ws.resource.impl.SessionResourceImpl;

/**
 * foo bar.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@RunWith(Arquillian.class)
public class SessionResourceTest extends BaseWSTest {

    /**
     * foo bar.
     * @throws Exception foo bar.
     */
    @SuppressWarnings("deprecation")
	@Test
    public void checksUserAuthenticationForValidUser() throws Exception {
    	
    	// TODO : refactor to constatnts
    	final String username = "admin@gmail.com";
    	final String password = "rfaysdhaiufsiuf";

        URL baseURL = new URL(TESTING_APP + SessionResourceImpl.URL);
        ClientResource clientResource = new ClientResource(baseURL.toExternalForm());
        
        SessionResource resource = clientResource.wrap(SessionResource.class);
        
        JsonRepresentation requestParam = new JsonRepresentation(
        		new SessionBean(username, password)
        );
        
        JsonRepresentation responseRep = resource.start(requestParam);        
        
        assertTrue("response status is 200", 
        		clientResource
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
     * @throws Exception foo bar.
     */
    @SuppressWarnings("deprecation")
	@Test
    public void checksUserAuthenticationForInvalidUser() throws JSONException, IOException {
    	
    	// TODO : refactor to constatnts
    	final String username = "invaliduser@gmail.com";
    	final String password = "rfaysdhaiufsiuf";

        URL baseURL = new URL(TESTING_APP + SessionResourceImpl.URL);
        ClientResource clientResource = new ClientResource(baseURL.toExternalForm());
        
        SessionResource resource = clientResource.wrap(SessionResource.class);
        
        JsonRepresentation requestParam = new JsonRepresentation(
        		new SessionBean(username, password)
        );
        
        JsonRepresentation responseRep = null;
        try {
			responseRep = resource.start(requestParam);
		} catch (org.restlet.resource.ResourceException e) {	
			
			assertTrue("should return 400 in exception content", e.toString().contains("400"));
		}
        
        assertTrue("response status is 400", 
        		clientResource
        		.getResponse()
        		.getStatus().equals(Status.CLIENT_ERROR_BAD_REQUEST));
	}
}


