package war.server.core.dao.implementations;

import war.server.core.dao.interfaces.UserRolesDao;
import war.server.core.entity.UserRole;

public class UserRolesDaoImpl extends GenericDaoImpl<UserRole> implements UserRolesDao {

    public UserRolesDaoImpl() {
        super(UserRole.class);
    }
}
