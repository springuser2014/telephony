package telephony.core.service;

import java.util.List;

import telephony.core.entity.jpa.*;
import telephony.core.query.filter.ContactFilterCriteria;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public interface ContactService extends BasicService<Contact> {
	
	/**
	 * asd .
	 * @param session TODO
	 * @param filters TODO
	 * @return asd.
	 * @throws SessionServiceException asd. 
	 * @throws ContactServiceException asd.
	 */
	List<Contact> find(SessionDto session, ContactFilterCriteria filters)
		throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param newContact asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 * 
	 */
	void add(SessionDto session, Contact newContact)
			throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param contactToUpdate asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 */
	void updateContact(SessionDto session, Contact contactToUpdate)
			throws SessionServiceException, ContactServiceException;
	

	/**
	 * asd.
	 * @param session TODO
	 * @param contactToDelete asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 */
	void deleteContact(SessionDto session, Contact contactToDelete)
			throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param label asd.
	 * @return asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 */
	Contact findByLabel(SessionDto session, String label)
		throws SessionServiceException, ContactServiceException;

	/**
	 * asd.
	 * @param session TODO
	 * @param contactId asd.
	 * @return  as.
	 * @throws SessionServiceException 
	 */
	Contact findById(SessionDto session, Long contactId)
			throws SessionServiceException;
}
