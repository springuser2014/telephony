package telephony.core.service;

import javax.persistence.EntityManager;

import telephony.core.entity.jpa.BaseEntity;
import telephony.core.service.bean.Session;

/**
 * Basic business services API.
 * @param <T> Basic entity class.
 */
public interface BasicService<T extends BaseEntity> {

    /**
     * Returns entityManager object.
     * @return entities manager.
     */
    EntityManager getEntityManager();
    
    /**
	 * Counts number of entities in persistence layer.
     * @param session TODO
	 * @return number of stored entities.
	 */
	long count(Session session);
	
	// TODO : add basic methods, for example findById
}
