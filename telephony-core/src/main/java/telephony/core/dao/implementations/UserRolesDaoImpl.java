package telephony.core.dao.implementations;

import telephony.core.dao.interfaces.UserRolesDao;
import telephony.core.entity.Role;

public class UserRolesDaoImpl extends GenericDaoImpl<Role> implements UserRolesDao {

    public UserRolesDaoImpl() {
        super(Role.class);
    }
}
