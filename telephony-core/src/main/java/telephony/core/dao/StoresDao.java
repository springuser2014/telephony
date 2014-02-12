package telephony.core.dao;

import telephony.core.entity.jpa.Store;

/**
 * asd.
 */
public interface StoresDao extends GenericDao<Store> {

	/**
	 * Looks for the store with given label.
	 * @param storelabel asd.
	 * @return asd.
	 */
	Store findByLabel(String storelabel);
}
