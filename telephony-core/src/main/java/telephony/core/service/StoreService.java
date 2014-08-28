package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.StoreFilterCriteria;
import telephony.core.service.dto.Session;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public interface StoreService extends BasicService<Store> {

    /**
     * asd.
     * @param session TODO
     * @param filters TODO
     * @return asd.
     * @throws SessionServiceException  asd.
     */
    List<Store> find(Session session, StoreFilterCriteria filters) 
    		throws SessionServiceException;

    /**
     * asd.
     * @param session TODO
     * @param store aasd.
     * @throws SessionServiceException 
     */
	void add(Session session, Store store) 
			throws SessionServiceException;

	/**
	 * Looks for the store with given label.
	 * @param session TODO
	 * @param storelabel asd.
	 * @throws SessionServiceException asd.
	 * @return asd.
	 */
	Store findByLabel(Session session, String storelabel) 
			throws SessionServiceException;

	/**
	 * asd.
	 * @param session TODO
	 * @param storeToEdit asd.
	 * @throws SessionServiceException asd.
	 */
	void update(Session session, Store storeToEdit) 
			throws SessionServiceException;

	/**
	 * asd.
	 * @param session TODO
	 * @param storeToDelete asd.
	 * @throws SessionServiceException 
	 */
	void remove(Session session, Store storeToDelete) 
			throws SessionServiceException;

	/**
	 * asd.
	 * @param session TODO
	 * @param moveToStoreId asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 */
	Store findById(Session session, long moveToStoreId) 
			throws SessionServiceException;
}
