package telephony.core.service;

import java.util.List;

import telephony.core.entity.jpa.Contact;
import telephony.core.service.dto.request.*;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.exception.ContactServiceException;

/**
 * asd.
 */
public interface ContactService extends BasicService<Contact> {
	
	/**
	 * asd .
	 * @param session TODO
	 * @param filters TODO
	 * @return asd.
	 * @throws telephony.core.service.exception.SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 */
	List<Contact> fetch(ContactFetchRequest req)
		throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param newContact asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 * 
	 */
	void add(ContactAddRequestDto req)
			throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param contact TODO
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 */
	void edit(ContactEditRequest contact)
			throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param req TODO
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 */
	void delete(ContactDeleteRequest req)
			throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param label asd.
	 * @return asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 */
	// TODO : replace by the fetch() method and remove
	@Deprecated
	Contact findByLabel(SessionDto session, String label)
		throws SessionServiceException, ContactServiceException;

	/**
	 * asd.
	 * @param session TODO
	 * @param contactId asd.
	 * @return  as.
	 * @throws SessionServiceException 
	 */
	// TODO : replace by the fetch() method and remove
	@Deprecated
	Contact findById(SessionDto session, Long contactId)
			throws SessionServiceException;

	List<Contact> find(ContactFetchRequestDto dto);
}
