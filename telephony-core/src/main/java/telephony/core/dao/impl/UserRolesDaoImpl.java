package telephony.core.dao.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UserRolesDao;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.User;
import telephony.core.entity.jpa.UserRole;

/**
 * UserRoles management DAO.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UserRolesDaoImpl extends GenericDaoImpl<UserRole>
	implements UserRolesDao {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
    public UserRolesDaoImpl() {
        super(UserRole.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> findByUserAndRole(User user, Set<Role> rolesToDelete) {
		
		logger.info("findByUserAndRole starts");
		logger.info("param : [ user : {}, rolesToDelete : {} ]", user, rolesToDelete);
	
		
		List<UserRole> lst = (List<UserRole>) getEntityManager()
		.createQuery("select e from UserRole e where e.user = ?1 and e.role in (?2)")
		.setParameter(1, user)
		.setParameter(2, rolesToDelete)
		.getResultList();
		
		return lst;		
	}
}
