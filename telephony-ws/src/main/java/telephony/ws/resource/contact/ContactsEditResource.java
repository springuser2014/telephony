package telephony.ws.resource.contact;

import telephony.core.service.dto.ContactsEditRequest;
import telephony.core.service.dto.ContactsEditResponse;

public interface ContactsEditResource {

	String URL = "/contacts/edit";
	
	ContactsEditResponse edit(ContactsEditRequest request);
}
