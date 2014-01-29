package telephony.core.service;

import java.util.List;

import telephony.core.entity.jpa.*;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface ContactService extends BasicService<Contact> {
	
	/**
	 * asd .
	 * @param username asd.
	 * @param sessionId asd.
	 * @return asd.
	 * @throws SessionServiceException asd. 
	 * @throws ContactServiceException asd.
	 */
	List<Contact> fetchAll(String username, String sessionId)
		throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd. 
	 * @param newContact asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 * 
	 */
	void addNewContact(String username, String sessionId, Contact newContact)
			throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd. 
	 * @param contactToUpdate asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 */
	void updateContact(String username, String sessionId, Contact contactToUpdate)
			throws SessionServiceException, ContactServiceException;
	

	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd. 
	 * @param contactToDelete asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 */
	void deleteContact(String username, String sessionId, Contact contactToDelete)
			throws SessionServiceException, ContactServiceException;
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param label asd.
	 * @return asd.
	 * @throws SessionServiceException asd.
	 * @throws ContactServiceException asd.
	 */
	Contact findByLabel(String username, String sessionId, String label)
		throws SessionServiceException, ContactServiceException;

	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param contactToDeleteId asd.
	 * @return  as.
	 * @throws SessionServiceException 
	 */
	Contact findById(String username, String sessionId, Long contactToDeleteId) 
			throws SessionServiceException;
}
