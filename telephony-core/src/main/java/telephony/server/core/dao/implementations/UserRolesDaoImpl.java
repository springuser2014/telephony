package telephony.server.core.dao.implementations;

import telephony.server.core.dao.interfaces.UserRolesDao;
import telephony.server.core.entity.Role;

public class UserRolesDaoImpl extends GenericDaoImpl<Role> implements UserRolesDao {

    public UserRolesDaoImpl() {
        super(Role.class);
    }
}
