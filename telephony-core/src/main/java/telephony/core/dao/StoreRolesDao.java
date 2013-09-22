package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.StoreRole;

public interface StoreRolesDao extends GenericDao<StoreRole> {

	/**
	 * asd.
	 * @param store asd.
	 */
	void removeStoreRolesByStore(Store store);

	/**
	 * asd.
	 * @param storeToDelete asd.
	 * @return asd.
	 */
	List<StoreRole> findByStore(Store storeToDelete);

}
