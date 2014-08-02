package telephony.core.dao.impl;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ModelDao;
import telephony.core.entity.jpa.Model;

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

	@Override
	public Model findByLabel(String label) {
		
        logger.debug("ModelDaoImpl.findByLabel starts");
        
		String queryStr = " select m from Model m  where m.label like ?1 ";
        
		logger.info("queryStr " + queryStr);
		
		Query q = getEntityManager().createQuery(queryStr);
		
		q.setParameter(1, "%" + label + "%");
		
		Model result = (Model) q.getSingleResult();
		
        logger.info(" found {} element", result);

		return result;
	}
}
