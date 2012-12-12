package telephony.core.dao.implementations;

import telephony.core.dao.interfaces.RolesDao;
import telephony.core.entity.Role;

public class RolesDaoImpl extends GenericDaoImpl<Role> implements RolesDao {

    public RolesDaoImpl() {
        super(Role.class);
    }
}
