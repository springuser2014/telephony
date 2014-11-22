package telephony.ws.resource.contact;

import org.restlet.ext.json.JsonRepresentation;

import telephony.core.service.dto.request.ContactDetailsRequest;

public interface ContactsDetailsResource {

	String URL = "/contacts/details";
	
	JsonRepresentation details(ContactDetailsRequest req);
}
