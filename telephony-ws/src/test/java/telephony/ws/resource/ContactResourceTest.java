package telephony.ws.resource;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.restlet.resource.ClientResource;

import telephony.core.entity.jpa.Contact;
import telephony.ws.resource.contact.ContactsAddResource;
import telephony.ws.resource.contact.ContactsDeleteResource;
import telephony.ws.resource.contact.ContactsFetchResource;
import telephony.ws.resource.session.SessionInitializationResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@RunWith(Arquillian.class)
public class ContactResourceTest extends BaseWSTest {
	
	static final String SESSION_INITIALIZATION_RESOURCE_URL = 
    		TESTING_APP + SessionInitializationResource.URL;
   
	// contacts
    		
	static final String CONTACT_FETCHING_RESOURCE_URL =
			TESTING_APP + ContactsFetchResource.URL;
	
	static final String CONTACT_ADDING_RESOURCE_URL =
			TESTING_APP + ContactsAddResource.URL;
	
	static final String CONTACT_DELETING_RESOURCE_URL =
			TESTING_APP + ContactsDeleteResource.URL;
	
	SessionInitializationResource session;
	
	ContactsFetchResource contactsFetchResource;
	
	ContactsAddResource contactsAddResource;
	
	ContactsDeleteResource contactsDeleteResource;
	
	ClientResource clientSession;
	
	ClientResource clientContactFetching;
	
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
	}
	
	// TODO : implement
	
	@Test
	public void asd() {
		assertTrue(true);
	}
	
	
	
}
