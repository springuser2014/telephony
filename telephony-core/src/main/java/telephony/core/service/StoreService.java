package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface StoreService extends BasicService<Store> {

    /**
     * asd.
     * @param username ad.
     * @param sessionId asd.
     * @return asd.
     * @throws SessionServiceException 
     */
    List<Store> fetchAllStores(String username, String sessionId) throws SessionServiceException;

    /**
     * asd.
     * @param username asd. 
     * @param sessionId asd.
     * @param store aasd. 
     * @return asd.
     * @throws SessionServiceException 
     */
	Store add(String username, String sessionId, Store store) throws SessionServiceException;

	/**
	 * Looks for the store with given label.
	 * @param username TODO
	 * @param sessionId TODO
	 * @param storelabel asd.
	 * @throws SessionServiceException
	 * @return asd.
	 */
	Store findByLabel(String username, String sessionId, String storelabel) throws SessionServiceException;

	/**
	 * asd.
	 * @param username
	 * @param sessionId
	 * @param storeToEdit
	 * @return
	 * @throws SessionServiceException 
	 */
	Store edit(String username, String sessionId, Store storeToEdit) throws SessionServiceException;

	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param storeToDelete asd.
	 * @throws SessionServiceException 
	 */
	void delete(String username, String sessionId, Store storeToDelete) throws SessionServiceException;

	/**
	 * asd.
	 * @param store asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 */
	List<Role> getRequestRoles(String username, String sessionId, Store store) throws SessionServiceException;


	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param store asd.
	 * @param roles as.
	 * @throws SessionServiceException
	 * @throws RoleServiceException
	 */
	void setRequiredRoles(String username, String sessionId, Store store,
			List<Role> roles) throws SessionServiceException,
			RoleServiceException;
}
