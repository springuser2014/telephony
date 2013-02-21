package telephony.core.dao.implementations;

import telephony.core.dao.interfaces.UserRolesDao;
import telephony.core.entity.Role;

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
