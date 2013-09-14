package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface RolesDao extends GenericDao<Role> {

	/**
	 * asd.
	 * @param store asd.
	 * @return asd.
	 */
	List<Role> findStoreRequiredRoles(Store store);
}
