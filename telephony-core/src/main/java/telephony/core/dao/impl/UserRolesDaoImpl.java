package telephony.core.dao.impl;

import telephony.core.dao.UserRolesDao;
import telephony.core.entity.jpa.Role;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UserRolesDaoImpl extends GenericDaoImpl<Role> implements UserRolesDao {

    /**
     * asd.
     */
    public UserRolesDaoImpl() {
        super(Role.class);
    }
}
