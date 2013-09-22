package telephony.core.dao;

import java.util.List;
import java.util.Set;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.User;
import telephony.core.entity.jpa.UserRole;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface UserRolesDao extends GenericDao<UserRole> {

	/**
	 * asd.
	 * @param user asd.
	 * @param rolesToDelete asd.
	 * @return asd.
	 */
	List<UserRole> findByUserAndRole(User user, Set<Role> rolesToDelete);

}
