package telephony.core.dao.impl;

import telephony.core.dao.RolesDao;
import telephony.core.entity.jpa.Role;

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
