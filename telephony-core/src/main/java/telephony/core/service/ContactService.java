package telephony.core.service;

import telephony.core.entity.jpa.Contact;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public interface ContactService extends BasicService<Contact> {

	/**
	 * asd .
	 * @param request sd.
	 * @return asd.
	 * @throws telephony.core.service.exception.SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 * @return d .
	 */
	ContactFetchResponse fetch(ContactFetchRequest fetchRequest)
		throws SessionServiceException, ContactServiceException;

	/**
	 * sd.
	 * @param request a.
	 * @return d.
	 * @throws SessionServiceException a.
	 * @throws ContactServiceException a.
	 * @return a .
	 */
	ContactAddResponse add(ContactAddRequest addRequest)
			throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param contact asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 * @return b.
	 */
	ContactEditResponse edit(ContactEditRequest editRequest)
			throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param req asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 * @return asd.
	 */
	ContactDeleteResponse delete(ContactDeleteRequest deleteRequest)
			throws SessionServiceException, ContactServiceException;

	/**
	 * asd.
	 * @param request a.
	 * @return d.
	 */
	ContactDetailsResponse fetchDetails(ContactDetailsRequest request) throws SessionServiceException;
}
