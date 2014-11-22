package telephony.ws.resource.contact;

import telephony.core.service.dto.request.ContactsEditRequest;
import telephony.core.service.dto.response.ContactEditResponse;

public interface ContactsEditResource {

	String URL = "/contacts/edit";
	
	ContactEditResponse edit(ContactsEditRequest request);
}
