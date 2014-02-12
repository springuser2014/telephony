package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;

/**
 * asd.
 */
public interface RolesDao extends GenericDao<Role> {

	/**
	 * asd.
	 * @param store asd.
	 * @return asd.
	 */
	List<Role> findStoreRequiredRoles(Store store);

	/**
	 * asd.
	 * @param label asd.
	 * @return asd.
	 */
	Role findByLabel(String label);
}
