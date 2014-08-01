package telephony.core.dao.impl;

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

        Producer res = (Producer)
        getEntityManager()
        .createQuery("select p from Producer p where p.label LIKE ?1")
                .setParameter(1, "%" + label + "%")                
                .getSingleResult();

        return res; 
	}

}
