package telephony.core.service;

import telephony.core.entity.jpa.Contact;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;

public interface ContactService extends BasicService<Contact> {

	ContactFetchResponse fetch(ContactFetchRequest fetchRequest)
		throws SessionServiceException, ContactServiceException;

	ContactAddResponse add(ContactAddRequest addRequest)
			throws SessionServiceException, ContactServiceException;
	
	ContactEditResponse edit(ContactEditRequest editRequest)
			throws SessionServiceException, ContactServiceException;
	
	ContactDeleteResponse delete(ContactDeleteRequest deleteRequest)
			throws SessionServiceException, ContactServiceException;

	ContactDetailsResponse fetchDetails(ContactDetailsRequest request)
			throws SessionServiceException;
}
