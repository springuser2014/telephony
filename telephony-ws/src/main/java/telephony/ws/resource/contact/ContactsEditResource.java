package telephony.ws.resource.contact;

import telephony.core.service.dto.request.ContactsEditRequestDto;
import telephony.core.service.dto.response.ContactsEditResponseDto;

public interface ContactsEditResource {

	String URL = "/contacts/edit";
	
	ContactsEditResponseDto edit(ContactsEditRequestDto request);
}
