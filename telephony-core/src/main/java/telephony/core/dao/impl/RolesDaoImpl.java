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
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class RolesDaoImpl extends GenericDaoImpl<Role> implements RolesDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public RolesDaoImpl() {
        super(Role.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findStoreRequiredRoles(Store store) {
		
		logger.debug("findStoreRequiredRoles starts");
		logger.debug("params : [ store: {}]", store);
		
		
		List<Store> whereIn = new ArrayList<Store>();
		whereIn.add(store);
		 
		List<Role> lst = (List<Role>) getEntityManager()
		.createQuery("select e from Role e join e.store s where s in (?1)")
		.setParameter(1, whereIn)
		.getResultList();
		
		List<Role> l = new ArrayList<Role>();
		l.addAll(store.getRequiredRoles());
		return l;	
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
