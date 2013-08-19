package telephony.core.service;

import javax.persistence.EntityManager;

import telephony.core.entity.jpa.BaseEntity;

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
	 * @return number of stored entities.
	 */
	long count();
}
