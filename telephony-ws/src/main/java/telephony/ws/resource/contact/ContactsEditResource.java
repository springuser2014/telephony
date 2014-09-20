package telephony.ws.resource.contact;

import telephony.core.service.dto.ContactsEditRequestDto;
import telephony.core.service.dto.ContactsEditResponseDto;

public interface ContactsEditResource {

	String URL = "/contacts/edit";
	
	ContactsEditResponseDto edit(ContactsEditRequestDto request);
}
