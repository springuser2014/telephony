package telephony.ws.resource;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;

import telephony.core.service.dto.request.DeleteRoleRequest;
import telephony.core.service.dto.request.RoleAddRequest;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.bean.UserBean;
import telephony.ws.resource.role.RolesAddResource;
import telephony.ws.resource.role.RolesDeleteResource;
import telephony.ws.resource.session.SessionInitializationResource;

@RunWith(JUnit4ClassRunner.class)
public class RoleResourceTest extends BaseWSTest {
	
	static final String SESSION_INITIALIZATION_RESOURCE_URL = 
    		TESTING_APP + SessionInitializationResource.URL;

	static final String ROLE_ADDING_RESOURCE_URL = 
			TESTING_APP + RolesAddResource.URL;
	
	static final String ROLE_DELETING_RESOURCE_URL =
			TESTING_APP + RolesDeleteResource.URL;
	
	private SessionInitializationResource session;
	
	private RolesAddResource rolesAdd;
	
	private RolesDeleteResource rolesDelete;
	
	private ClientResource clientSession;
	
	private ClientResource clientRoleAddding;
	
	private ClientResource clientRoleDeleting;
	
	@Before
	public void init() throws MalformedURLException {
		
		URL sessionUrl = new URL(SESSION_INITIALIZATION_RESOURCE_URL);
        this.clientSession = 
        		new ClientResource(sessionUrl.toExternalForm());        
        this.session = 
        		this.clientSession.wrap(SessionInitializationResource.class);
        
        URL rolesAddUrl = new URL(ROLE_ADDING_RESOURCE_URL);
        this.clientRoleAddding =
        		new ClientResource(rolesAddUrl.toExternalForm());
        this.rolesAdd =
        		this.clientRoleAddding.wrap(RolesAddResource.class);
        
        URL rolesDeleteUrl = new URL(ROLE_DELETING_RESOURCE_URL);
        this.clientRoleDeleting = 
        		new ClientResource(rolesDeleteUrl.toExternalForm());
        this.rolesDelete = 
        		this.clientRoleDeleting.wrap(RolesDeleteResource.class);
    }
	
	@Test
	public void testAddingRole() 
			throws JSONException, IOException, SessionServiceException, ContactServiceException {
		
		// TODO : refactor to constatnts
    	final String username = "user1@gmail.com";
    	final String password = "rfaysdhaiufsiuf";
        final String label = "jakasrola";
        
    	JsonRepresentation sessionInitializationParam = new JsonRepresentation(
    			new UserBean(username, password)
    	);

    	JsonRepresentation responseRep = session.initialize(sessionInitializationParam);

    	assertTrue("response status is 200", clientSession
    										.getResponse()
    										.getStatus().equals(Status.SUCCESS_OK));
    	
    	JSONObject reponseObj = responseRep.getJsonObject();
    	String sessionId = reponseObj.getString("sessionId");
    	
    	JsonRepresentation roleAddingParam = new JsonRepresentation(
    			new RoleAddRequest(username, sessionId, label)
    	);
    	
    	JsonRepresentation addresponse = rolesAdd.add(roleAddingParam);
    	
    	JSONObject jsonResponse = addresponse.getJsonObject();
    	Boolean isSuccess = jsonResponse.getBoolean("success");
    	
    	assertTrue("should return true", isSuccess);
	}
	
	@Test
	public void testDeletingRole() 
			throws JSONException, IOException, SessionServiceException, ContactServiceException {
		
		// TODO : refactor to constatnts
    	final String username = "user1@gmail.com";
    	final String password = "rfaysdhaiufsiuf";
        final String label = "jakasrola";
        
    	JsonRepresentation sessionInitializationParam = new JsonRepresentation(
    			new UserBean(username, password)
    	);

    	JsonRepresentation responseRep = session.initialize(sessionInitializationParam);

    	assertTrue("response status is 200", clientSession
    										.getResponse()
    										.getStatus().equals(Status.SUCCESS_OK));
    	
    	JSONObject reponseObj = responseRep.getJsonObject();
    	String sessionId = reponseObj.getString("sessionId");
    	
    	JsonRepresentation roleDeletingParam = new JsonRepresentation(
    		new DeleteRoleRequest(username, sessionId)
    	);
    	
    	JsonRepresentation deleteResponse = rolesDelete.delete(roleDeletingParam);
    	
    	assertTrue("response status is 200", clientRoleDeleting
    											.getResponse()
    											.getStatus().equals(Status.SUCCESS_OK));
    	
    	JSONObject jsonResponse = deleteResponse.getJsonObject();
    	Boolean isSuccess = jsonResponse.getBoolean("success");
    	
    	assertTrue("should return true", isSuccess);
	}
}
