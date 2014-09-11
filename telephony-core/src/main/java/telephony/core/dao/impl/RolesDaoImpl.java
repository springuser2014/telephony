package telephony.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.RolesDao;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.RoleFilterCriteria;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> find(RoleFilterCriteria filters) {
		
		logger.debug("findByLabel starts");
		logger.debug("params : [ filters: {}]", filters);
		
		StringBuilder sb = new StringBuilder();
		sb.append("select r from Role r ");
		sb.append(" where 1=1 ");
		
		if (filters.getLabel() != null) {
			sb.append(" r.label Like :label ");
		}
		
		Query query = getEntityManager() 
			.createQuery(sb.toString());
		
		if (filters.getPage() != null) {
			query.setFirstResult(filters.getPage());
		}
		
		if (filters.getPerPage() != null) {
			query.setMaxResults(filters.getPerPage());
		}
		
		if (filters.getLabel() != null) {
			query.setParameter("label", filters.getLabel());
		}
		
		List<Role> role = (List<Role>) query.getResultList();
		
		return role;
	}
	
}
