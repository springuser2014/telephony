package telephony.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ModelDao;
import telephony.core.entity.jpa.Model;
import telephony.core.query.filter.DeliveryFilterCriteria;

/**
 * asd.
 */
public class ModelDaoImpl 
extends GenericDaoImpl<Model> 
implements ModelDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * asd.
	 */
	public ModelDaoImpl() {
		super(Model.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Model findByLabel(String label) {
		
        logger.debug("ModelDaoImpl.findByLabel starts");
        
		String queryStr = " select m from Model m where m.label like ?1 ";
        
		logger.debug("queryStr " + queryStr);
		
		Query q = getEntityManager().createQuery(queryStr);
		
		q.setParameter(1, "%" + label + "%");
		
		List<Model> lst = (List<Model>) q.getResultList();
		
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}
}
