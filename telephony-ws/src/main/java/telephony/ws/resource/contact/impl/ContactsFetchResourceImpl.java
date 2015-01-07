package telephony.ws.resource.contact.impl;

import com.google.inject.Inject;
import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.entity.jpa.Contact;
import telephony.core.service.ContactService;
import telephony.core.service.dto.ContactDto;
import telephony.core.service.dto.request.ContactFetchRequest;
import telephony.core.service.dto.response.ContactListResponse;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsFetchResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * asd.
 */
public class ContactsFetchResourceImpl 
extends TelephonyServerResource
implements ContactsFetchResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ContactService contactService;

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonRepresentation fetch(ContactFetchRequest dto)
			throws JSONException, IOException, SessionServiceException, ContactServiceException {
        
		logger.info("ContactsFetchResource.fetch starts");
		
//		List<Contact> contacts = contactService.find(dto);
//        List<ContactDto> contactsToJsonize = convertToBeans(contacts);
//
        ContactListResponse response = new ContactListResponse();
//        response.setContacts(contactsToJsonize);
        
        return new JsonRepresentation(response);        
    }


	private List<ContactDto> convertToBeans(List<Contact> contacts) {
		
		List<ContactDto> contactsToJsonize =  new ArrayList<ContactDto>();
        
        for (Contact c : contacts) {
//        	contactsToJsonize.add(create(c));
        }
        
		return contactsToJsonize;
	}

}
