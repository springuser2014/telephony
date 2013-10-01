package telephony.core.service;


import java.util.List;
import java.util.Set;

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
     * @throws SessionServiceException  asd.
     */
    List<Store> fetchAllStores(String username, String sessionId) 
    		throws SessionServiceException;

    /**
     * asd.
     * @param username asd. 
     * @param sessionId asd.
     * @param store aasd. 
     * @throws SessionServiceException 
     */
	void add(String username, String sessionId, Store store) 
			throws SessionServiceException;

	/**
	 * Looks for the store with given label.
	 * @param username TODO
	 * @param sessionId TODO
	 * @param storelabel asd.
	 * @throws SessionServiceException asd.
	 * @return asd.
	 */
	Store findByLabel(String username, String sessionId, String storelabel) 
			throws SessionServiceException;

	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param storeToEdit asd.
	 * @throws SessionServiceException asd.
	 */
	void edit(String username, String sessionId, Store storeToEdit) 
			throws SessionServiceException;

	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param storeToDelete asd.
	 * @throws SessionServiceException 
	 */
	void delete(String username, String sessionId, Store storeToDelete) 
			throws SessionServiceException;

	/**
	 * asd.
	 * @param store asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @return asd.
	 * @throws SessionServiceException  asd.
	 */
	List<Role> getRequestRoles(String username, String sessionId, Store store) 
			throws SessionServiceException;


	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param store asd.
	 * @param roles as.
	 * @throws SessionServiceException asd.
	 * @throws RoleServiceException asd.
	 */
	void setRequiredRoles(String username, String sessionId, Store store,
			List<Role> roles) throws SessionServiceException,
			RoleServiceException;

	/**
	 * asd.
	 * @param username TODO
	 * @param sessionId TODO
	 * @param moveToStoreId asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 */
	Store findById(String username, String sessionId, long moveToStoreId) throws SessionServiceException;

}
