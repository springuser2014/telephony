package telephony.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.RolesDao;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;

/**
 * Roles management DAO.
 */
public class RolesDaoImpl extends GenericDaoImpl<Role> implements RolesDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * asd.
	 */
	public RolesDaoImpl() {
        super(Role.class);
    }

	
	@Override
	public Role findByLabel(String label) {
		
		logger.debug("findByLabel starts");
		logger.debug("params : [ label: {}]", label);
		
		Role role = (Role) getEntityManager()
		.createQuery("select e from Role e where e.name = ?1")
		.setParameter(1, label)
		.getSingleResult();
		
		return role;
		
	}    
	
	
}
