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
	 * @param label asd.
	 * @return asd.
	 */
	Role findByLabel(String label);
}
