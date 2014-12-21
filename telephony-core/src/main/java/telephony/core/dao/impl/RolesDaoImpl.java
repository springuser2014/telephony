package telephony.core.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.RolesDao;
import telephony.core.entity.jpa.Role;
import telephony.core.query.filter.RoleFilterCriteria;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static telephony.core.assertion.CommonAssertions.isNotNull;

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

		if (isNotNull(filters.getLabel())) {
			sb.append(" and r.label = :label ");
		}

		if (isNotNull(filters.getLabelLike())) {
			sb.append(" and r.label like :labelLike ");
		}
		
		Query query = getEntityManager() 
			.createQuery(sb.toString());
		
		if (isNotNull(filters.getPage())) {
			query.setFirstResult(filters.getPage());
		}
		
		if (isNotNull(filters.getPerPage())) {
			query.setMaxResults(filters.getPerPage());
		}
		
		if (isNotNull(filters.getLabel())) {
			query.setParameter("label", filters.getLabel());
		}

		if (isNotNull(filters.getLabelLike())) {
			query.setParameter("labelLike", filters.getLabelLike());
		}
		
		List<Role> roles = (List<Role>) query.getResultList();
		
		return roles;
	}
	
}
