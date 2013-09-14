package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface StoresDao extends GenericDao<Store> {

	/**
	 * Looks for the store with given label.
	 * @param storelabel asd.
	 * @return asd.
	 */
	Store findByLabel(String storelabel);
}
