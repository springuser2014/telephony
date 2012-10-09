package telephony.server.core.dao.implementations;

import telephony.server.core.dao.interfaces.UserRolesDao;
import telephony.server.core.entity.UserRole;

public class UserRolesDaoImpl extends GenericDaoImpl<UserRole> implements UserRolesDao {

    public UserRolesDaoImpl() {
        super(UserRole.class);
    }
}
