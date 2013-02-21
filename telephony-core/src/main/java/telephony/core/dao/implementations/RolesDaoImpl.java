package telephony.core.dao.implementations;

import telephony.core.dao.interfaces.RolesDao;
import telephony.core.entity.Role;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class RolesDaoImpl extends GenericDaoImpl<Role> implements RolesDao {

    /**
     * asd.
     */
    public RolesDaoImpl() {
        super(Role.class);
    }
}
