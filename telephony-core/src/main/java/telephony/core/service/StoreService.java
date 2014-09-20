package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.StoreFilterCriteria;
import telephony.core.service.dto.SessionDto;
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
    List<Store> find(SessionDto session, StoreFilterCriteria filters) 
    		throws SessionServiceException;

    /**
     * asd.
     * @param session TODO
     * @param store aasd.
     * @throws SessionServiceException 
     */
	void add(SessionDto session, Store store) 
			throws SessionServiceException;

	/**
	 * Looks for the store with given label.
	 * @param session TODO
	 * @param storelabel asd.
	 * @throws SessionServiceException asd.
	 * @return asd.
	 */
	Store findByLabel(SessionDto session, String storelabel) 
			throws SessionServiceException;

	/**
	 * asd.
	 * @param session TODO
	 * @param storeToEdit asd.
	 * @throws SessionServiceException asd.
	 */
	void update(SessionDto session, Store storeToEdit) 
			throws SessionServiceException;

	/**
	 * asd.
	 * @param session TODO
	 * @param storeToDelete asd.
	 * @throws SessionServiceException 
	 */
	void remove(SessionDto session, Store storeToDelete) 
			throws SessionServiceException;

	/**
	 * asd.
	 * @param session TODO
	 * @param moveToStoreId asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 */
	Store findById(SessionDto session, long moveToStoreId) 
			throws SessionServiceException;
}
