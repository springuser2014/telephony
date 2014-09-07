package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.StoreFilterCriteria;

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

	List<Store> find(StoreFilterCriteria filters);
}
