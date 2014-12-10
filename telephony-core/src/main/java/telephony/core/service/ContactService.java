package telephony.core.service;

import java.util.List;

import telephony.core.entity.jpa.Contact;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.ContactAddResponse;
import telephony.core.service.dto.response.ContactDeleteResponse;
import telephony.core.service.dto.response.ContactFetchResponse;
import telephony.core.service.dto.response.ContactEditResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.exception.ContactServiceException;

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
	ContactFetchResponse fetch(ContactFetchRequest request)
		throws SessionServiceException, ContactServiceException;

	/**
	 * sd.
	 * @param request a.
	 * @return d.
	 * @throws SessionServiceException a.
	 * @throws ContactServiceException a.
	 * @return a .
	 */
	ContactAddResponse add(ContactAddRequest request)
			throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param contact asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 * @return b.
	 */
	ContactEditResponse edit(ContactEditRequest contact)
			throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param req asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 * @return asd.
	 */
	ContactDeleteResponse delete(ContactDeleteRequest req)
			throws SessionServiceException, ContactServiceException;
}
