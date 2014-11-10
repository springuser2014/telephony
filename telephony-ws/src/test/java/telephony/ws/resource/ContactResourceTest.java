package telephony.ws.resource;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.arquillian.junit.Arquillian;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;

import telephony.core.service.dto.ContactDto;
import telephony.core.service.dto.SessionBean;
import telephony.core.service.dto.request.ContactAddRequestDto;
import telephony.core.service.dto.request.DeleteContactRequestDto;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.bean.UserBean;
import telephony.ws.resource.contact.ContactsAddResource;
import telephony.ws.resource.contact.ContactsDeleteResource;
import telephony.ws.resource.contact.ContactsFetchResource;
import telephony.ws.resource.session.SessionInitializationResource;

/**
 * asd.
 */
// TODO : refactor using rest-assured
@RunWith(JUnit4ClassRunner.class)
public class ContactResourceTest extends BaseWSTest {
	
	// session resources
	
	static final String SESSION_INITIALIZATION_RESOURCE_URL = 
    		TESTING_APP + SessionInitializationResource.URL;
   
	// contacts resources
    		
	static final String CONTACT_FETCHING_RESOURCE_URL =
			TESTING_APP + ContactsFetchResource.URL;
	
	static final String CONTACT_ADDING_RESOURCE_URL =
			TESTING_APP + ContactsAddResource.URL;
	
	static final String CONTACT_DELETING_RESOURCE_URL =
			TESTING_APP + ContactsDeleteResource.URL;
	
	private SessionInitializationResource session;
	
	private ContactsFetchResource contactsFetchResource;
	
	private ContactsAddResource contactsAddResource;
	
	private ContactsDeleteResource contactsDeleteResource;
	
	private ClientResource clientSession;
	
	private ClientResource clientContactFetching;

	private ClientResource clientContactAdding;
	
	private ClientResource clientContactDeleting;
	
	@Before
	public void init() throws MalformedURLException {
		
		URL sessionUrl = new URL(SESSION_INITIALIZATION_RESOURCE_URL);
        this.clientSession = 
        		new ClientResource(sessionUrl.toExternalForm());        
        this.session = 
        		this.clientSession.wrap(SessionInitializationResource.class);
       
	        
        URL contactFetchUrl = new URL(CONTACT_FETCHING_RESOURCE_URL);
        this.clientContactFetching =
        		new ClientResource(contactFetchUrl.toExternalForm());
        
        this.contactsFetchResource  =
        		this.clientContactFetching.wrap(ContactsFetchResource.class);
        
        URL contactAddUrl = new URL(CONTACT_ADDING_RESOURCE_URL);
        this.clientContactAdding = 
        		new ClientResource(contactAddUrl.toExternalForm());
        
        this.contactsAddResource = 
        		this.clientContactAdding.wrap(ContactsAddResource.class);
        
        URL contactDeleteUrl = new URL(CONTACT_DELETING_RESOURCE_URL);
        
        this.clientContactDeleting = 
        		new ClientResource(contactDeleteUrl.toExternalForm());
        
        this.contactsDeleteResource =
        		this.clientContactDeleting.wrap(ContactsDeleteResource.class);
        	
	}
	
	@Test
	public void testFetchingContactsList() 
			throws JSONException, IOException, SessionServiceException, ContactServiceException {
		
		// TODO : refactor to constatnts
    	final String username = "user1@gmail.com";
    	final String password = "rfaysdhaiufsiuf";
        
    	JsonRepresentation sessionInitializationParam = new JsonRepresentation(
    			new UserBean(username, password)
    	);

    	JsonRepresentation responseRep = session.initialize(sessionInitializationParam);

    	assertTrue("response status is 200", clientSession
    										.getResponse()
    										.getStatus().equals(Status.SUCCESS_OK));
    	
    	
    	JSONObject reponseObj = responseRep.getJsonObject();
    	String sessionId = reponseObj.getString("sessionId");
    	
    	JsonRepresentation contactFetchParam = new JsonRepresentation(
    			new SessionBean(username, sessionId)
    	);
    	
    	JsonRepresentation contacts = contactsFetchResource.fetch(contactFetchParam);
    	
    	assertTrue("should return 200", clientContactFetching
    									.getStatus()
    									.isSuccess());
    	
    	JSONObject jsonObj = contacts.getJsonObject();
    	JSONArray jsonArr = jsonObj.getJSONArray("contacts");
    	
    	assertTrue("should contains at least 3 contacts", jsonArr != null && jsonArr.length() >= 3);
	}
	
	@Test
	public void testAddingNewContact() 
			throws JSONException, IOException, SessionServiceException, ContactServiceException {

		// TODO : refactor to constatnts
    	final String username = "user1@gmail.com";
    	final String password = "rfaysdhaiufsiuf";
    	
    	JsonRepresentation sessionInitializationParam = new JsonRepresentation(
    			new UserBean(username, password)
    	);
    	
    	JsonRepresentation responseRep = session.initialize(sessionInitializationParam);
		
    	assertTrue("response stats is 200", clientSession
    										.getResponse()
    										.getStatus().equals(Status.SUCCESS_OK));
    	
    	JSONObject responseObj = responseRep.getJsonObject();
    	String sessionId = responseObj.getString("sessionId");
    	
    	// TODO : refactor to constans
    	ContactDto newContact = new ContactDto();
    	newContact.setDetails("somedetails");
    	newContact.setLabel("someLabel");
    	
    	JsonRepresentation contactAddParam = new JsonRepresentation(
    			new ContactAddRequestDto(username, sessionId, newContact)
    	);
    	
    	JsonRepresentation addResponse = contactsAddResource.add(contactAddParam);
    	
    	assertTrue("should return 200", clientContactAdding
    									.getStatus()
    									.isSuccess());
    	JSONObject jsonObj = addResponse.getJsonObject();
    	
    	Boolean isSuccess = jsonObj.getBoolean("success");
    	
    	assertTrue("should return success", isSuccess);    	
	}
	
	
	@Test
	public void testDeletingContact() 
			throws JSONException, IOException, SessionServiceException, ContactServiceException {

		// TODO : refactor to constatnts
    	final String username = "user1@gmail.com";
    	final String password = "rfaysdhaiufsiuf";
    	
		
    	JsonRepresentation sessionInitializationParams = new JsonRepresentation(
    			new UserBean(username, password)
    	);
    	
    	JsonRepresentation responseRep = session.initialize(sessionInitializationParams);
		
    	assertTrue("response stats is 200", clientSession
    										.getResponse()
    										.getStatus().equals(Status.SUCCESS_OK));
    	
    	JSONObject responseObj = responseRep.getJsonObject();
    	String sessionId = responseObj.getString("sessionId");
    	
    	JsonRepresentation contactFetchParam = new JsonRepresentation(
    			new SessionBean(username, sessionId)
    	);
    	
    	JsonRepresentation contacts = contactsFetchResource.fetch(contactFetchParam);
    	
    	assertTrue("should return 200", clientContactFetching
    									.getStatus()
    									.isSuccess());
    	
    	JSONObject jsonObj = contacts.getJsonObject();
    	JSONArray jsonArr = jsonObj.getJSONArray("contacts");
    	    	
    	JSONObject contactToDelete = (JSONObject) jsonArr.get(0);
    	Long contactToDeleteId = contactToDelete.getLong("id");
    	
    	JsonRepresentation contactDeleteParam = new JsonRepresentation(
    			new DeleteContactRequestDto(username, sessionId, contactToDeleteId)
    	);
    	
    	JsonRepresentation deleteResponse = contactsDeleteResource.delete(contactDeleteParam);
    	
    	assertTrue("response status is 200", clientContactDeleting
							    			.getResponse()
							    			.getStatus().equals(Status.SUCCESS_OK));
    	
    	JSONObject deleteRes = deleteResponse.getJsonObject();
    	Boolean isSucess = deleteRes.getBoolean("success");
    	
    	assertTrue("deleted successfuly", isSucess);
    	
	}
}
