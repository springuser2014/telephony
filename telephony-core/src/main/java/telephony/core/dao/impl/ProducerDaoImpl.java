package telephony.core.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ProducerDao;
import telephony.core.entity.jpa.Producer;

/**
 * asd.
 */
public class ProducerDaoImpl extends  GenericDaoImpl<Producer> implements ProducerDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * asd.
	 */
	public ProducerDaoImpl() {
		super(Producer.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Producer findByLabel(String label) {
		
		logger.debug("findByLabel starts");
        logger.debug("params : [ label : {} ]", label);

        List<Producer> res = (List<Producer>)        
        	getEntityManager()
        		.createQuery("select p from Producer p where p.label LIKE ?1")
                .setParameter(1, "%" + label + "%")                
                .getResultList();

        if (res.size() > 0) {
        	return res.get(0);
        } else {
        	return null;
        }
	}
}
