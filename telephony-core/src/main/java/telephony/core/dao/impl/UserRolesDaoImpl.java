package telephony.core.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UserRolesDao;
import telephony.core.entity.jpa.Role;

/**
 * UserRoles management DAO.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UserRolesDaoImpl extends GenericDaoImpl<Role> implements UserRolesDao {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
    public UserRolesDaoImpl() {
        super(Role.class);
    }
}
