package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.RoleFilterCriteria;

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

	List<Role> find(RoleFilterCriteria filters);
}
